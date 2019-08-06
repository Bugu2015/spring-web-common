package spring.web.common;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class LogBackConverter extends MessageConverter {

    @Override
    public String convert(ILoggingEvent event) {
        return String.format(" %s : %s", ContextThread.traceId.get(), super.convert(event));
    }

}
