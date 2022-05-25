package ru.liga.application.layout;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

@Component
public class MaskingPatternLayout extends PatternLayout {
    private static final String PASSWORD_MASK_PATTERN = "password=([A-Za-z0-9]+)";
    private static final String SALARY_MASK_PATTERN = "salary=(\\d+)";
    private static final int FIRST_MATCHER_INDEX = 1;
    private final Pattern multilinePattern;

    public MaskingPatternLayout() {
        List<String> maskPatterns = List.of(
                PASSWORD_MASK_PATTERN,
                SALARY_MASK_PATTERN
        ); //todo можно убрать эти переносы
        multilinePattern = Pattern.compile(String.join("|", maskPatterns), Pattern.MULTILINE);
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        return maskMessage(super.doLayout(event));
    }

    private String maskMessage(String message) {
        if (multilinePattern == null) {
            return message;
        }
        StringBuilder maskBuilder = new StringBuilder(message);
        Matcher matcher = multilinePattern.matcher(maskBuilder);
        while (matcher.find()) {
            IntStream.rangeClosed(FIRST_MATCHER_INDEX, matcher.groupCount()).forEach(group -> {
                if (matcher.group(group) != null) { //todo в приватный метод
                    IntStream.range(matcher.start(group), matcher.end(group)).forEach(i -> maskBuilder.setCharAt(i, '*'));
                }
            });
        }
        return maskBuilder.toString();
    }
}
