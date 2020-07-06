package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;

import java.util.function.Supplier;

public abstract class AbstractITTest {
    @Autowired
    protected TransactionTemplate transactionTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractITTest.class);

    private static final ITRuntime RUNTIME_ENV;

    static {
        RUNTIME_ENV = getIntegrationTestRuntimeEnvironment();
    }


    static ITRuntime getIntegrationTestRuntimeEnvironment() {
        // this env variable is set by the failsafe plugin
        String env = System.getProperty("adore.it.runtime.env");

        if (StringUtils.isEmpty(env)) {
            LOGGER.info("No integration test runtime environment provided, consider {}", ITRuntime.LOCAL.name());
            return ITRuntime.LOCAL;
        }
        return ITRuntime.valueOf(env.toUpperCase());
    }


    protected <T> T doInTransaction(Supplier<T> function) {
        return transactionTemplate.execute((txStatus) -> function.get());
    }

    protected <T> HttpEntity<T> newHttpEntity(T body) {
        return newHttpEntity(body, null);
    }

    protected <T> HttpEntity<T> newHttpEntity(T body, String token) {
        return newHttpEntity(body, token, null);
    }

    protected <T> HttpEntity<T> newHttpEntity(T body, String token, MediaType contentType) {
        HttpHeaders headers = new HttpHeaders();
        if (contentType != null) {
            headers.setContentType(contentType);
        }
        headers.add("Accept-Language", "en");
        if (StringUtils.isEmpty(token)) {
            headers.add("Authorization", token);
        }
        return new HttpEntity<>(body, headers);
    }


    public static enum ITRuntime {
        LOCAL,
        CI
    }
}
