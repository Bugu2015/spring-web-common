package spring.web.common.logging;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.core.pattern.PatternConverter;
import spring.web.common.context.ContextThread;

@ConverterKeys({"traceId"})
@Plugin(name = "logConverter", category = PatternConverter.CATEGORY)
public class LogConverter extends LogEventPatternConverter {

    private static LogConverter logConverter = new LogConverter();

    public static LogConverter newInstance(final String[] options){
        return logConverter;
    }

    private LogConverter() {
        super("id", "id");
    }

    @Override
    public void format(LogEvent logEvent, StringBuilder stringBuilder) {
        stringBuilder.append(ContextThread.traceId.get());
    }

}
