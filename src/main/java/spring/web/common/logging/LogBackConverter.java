package spring.web.common.logging;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.stereotype.Component;
import spring.web.common.context.ContextThread;

@Component
public class LogBackConverter extends MessageConverter {

    @Override
    public String convert(ILoggingEvent event) {
        return String.format(" %s : %s", ContextThread.traceId.get(), super.convert(event));
    }

}
