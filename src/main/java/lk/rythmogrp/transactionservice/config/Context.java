package lk.rythmogrp.transactionservice.config;

import lk.rythmogrp.transactionservice.config.dto.ThreadData;
import org.springframework.stereotype.Component;

@Component
public class Context {

    private static ThreadLocal<ThreadData> threadData = new ThreadLocal<>();

    public static ThreadLocal<ThreadData> getThreadData() {
        return threadData;
    }

    public static void setThreadData(ThreadLocal<ThreadData> threadData) {
        Context.threadData = threadData;
    }
}
