package com.smirnoff.home.platform.logging;

import feign.Logger;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CustomFeignRequestLogging extends Logger {

    private final ThreadLocal<LogContainer> threadLocal = new ThreadLocal<>();

    @Override
    protected void log(String configKey, String format, Object... args) {
        LogContainer container = threadLocal.get();
        if (container == null) {
            container = new LogContainer();
            container.setMethodName(configKey);
        }

        StringBuilder messageBuilder = new StringBuilder();
        for (Object arg : args) {
            messageBuilder.append(arg).append(" ");
        }
        List<String> messages = container.getMessages();
        messages.add(messageBuilder.toString());
        if (format.contains(" END HTTP ")) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < messages.size() - 1; i++) {
                builder.append(messages.get(i));
                if(i != messages.size() - 2) {
                    builder.append("\n");
                }
            }
            log.error("{}\n{}", container.getMethodName(), builder);
            threadLocal.remove();
        } else {
            threadLocal.set(container);
        }
    }

    @Getter
    @Setter
    private static class LogContainer {
        private String methodName;
        private List<String> messages = new ArrayList<>();
    }
}
