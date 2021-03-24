package com.example.designpatternspider.selenium.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Until {
    public static <T> T getUntil(GetUntilAction<T> action, long second, Class<T> cla) {
        long t1 = System.currentTimeMillis();
        T result = null;
        while (true) {
            T t = null;
            try {
                t = action.exec();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                continue;
            }
            if (t != null || System.currentTimeMillis() - t1 >= second * 1000) {
                result = t;
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public interface GetUntilAction<T> {
        T exec() throws Exception;
    }
}
