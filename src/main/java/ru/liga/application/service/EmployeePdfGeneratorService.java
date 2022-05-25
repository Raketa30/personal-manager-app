package ru.liga.application.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Service;
import ru.liga.application.api.PdfGeneratorService;
import ru.liga.application.domain.entity.Employee;
import ru.liga.application.domain.entity.Task;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeePdfGeneratorService implements PdfGeneratorService<Employee> {
    private static final String EMPLOYEE_INFO_TEMPLATE = "pdf/employee_info_template.vm";
    private final VelocityEngine velocityEngine;

    @Override
    public byte[] generatePdf(Employee employee) {
        Template template = velocityEngine.getTemplate(EMPLOYEE_INFO_TEMPLATE);
        VelocityContext context = getVelocityContext(employee);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return generatePdf(writer.toString());
    }

    private byte[] generatePdf(String html) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            document.addCreationDate();
            document.addProducer();
            document.addTitle("HTML to PDF using itext");
            document.setPageSize(PageSize.A4);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
            document.open();
            XMLWorkerHelper xmlWorkerHelper = XMLWorkerHelper.getInstance();
            xmlWorkerHelper.getDefaultCssResolver(true);
            xmlWorkerHelper.parseXHtml(pdfWriter, document, new StringReader(html));
            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            log.error("EmployeePdfGeneratorService generatePdf() error", e);
        }
        return new byte[0];
    }

    private VelocityContext getVelocityContext(Employee employee) {
        VelocityContext context = new VelocityContext();
        context.put("uuid", employee.getUuid());
        context.put("firstname", employee.getFirstname());
        context.put("lastname", employee.getLastname());
        context.put("position", employee.getPosition().getTitle());
        context.put("department", employee.getPosition().getDepartment().getTitle());
        List<String> tasks = employee.getTasks()
                .stream()
                .map(Task::getDescription)
                .collect(Collectors.toList());
        context.put("tasks", tasks);
        return context;
    }
}
