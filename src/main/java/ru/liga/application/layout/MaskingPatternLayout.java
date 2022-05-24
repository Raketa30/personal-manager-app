package ru.liga.application.layout;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

@Component
public class MaskingPatternLayout extends PatternLayout {
    private static final int FIRST_SYMBOL_INDEX = 1;
    private final List<String> maskPatterns = new ArrayList<>();
    private Pattern multilinePattern;

    public void addMaskPattern(String maskPattern) {
        maskPatterns.add(maskPattern);
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
            IntStream.rangeClosed(FIRST_SYMBOL_INDEX, matcher.groupCount()).forEach(group -> {
                if (matcher.group(group) != null) {
                    IntStream.range(matcher.start(group), matcher.end(group)).forEach(i -> maskBuilder.setCharAt(i, '*'));
                }
            });
        }
        return maskBuilder.toString();
    }
}
