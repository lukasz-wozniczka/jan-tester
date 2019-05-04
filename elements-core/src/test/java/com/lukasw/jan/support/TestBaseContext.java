package com.lukasw.jan.support;

import com.google.common.annotations.VisibleForTesting;
import com.lukasw.jan.BaseContext;
import org.openqa.selenium.WebDriver;

public class TestBaseContext implements BaseContext {

    private final WebDriver webDriver;

    @VisibleForTesting
    public TestBaseContext(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public WebDriver webDriver() {
        return this.webDriver;
    }
}
