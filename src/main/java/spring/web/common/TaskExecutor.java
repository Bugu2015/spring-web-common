package spring.web.common;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class TaskExecutor extends ThreadPoolTaskExecutor {

    public TaskExecutor() {
        super();
        setThreadNamePrefix("taskExecutor");
        setCorePoolSize(20);
        setMaxPoolSize(200);
        setQueueCapacity(50);
    }

}
