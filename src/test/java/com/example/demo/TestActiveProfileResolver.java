package com.example.demo;

import org.springframework.test.context.ActiveProfilesResolver;

public class TestActiveProfileResolver implements ActiveProfilesResolver {
    @Override
    public String[] resolve(Class<?> testClass) {
        if (AbstractITTest.getIntegrationTestRuntimeEnvironment() == AbstractITTest.ITRuntime.CI) {
            System.setProperty("spring.cloud.config.profile", "testcommon,testci");
        } else {
            System.setProperty("spring.cloud.config.profile", "testcommon,testlocal");
        }
        return new String[] {"ems"};
    }
}
