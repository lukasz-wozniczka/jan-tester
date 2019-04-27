package com.lukasw.jan;

import org.openqa.selenium.WebDriver;

/**
 * Define the base context created when the new test instance is started.
 * {@link BaseContext} is propagated and available by all sub-element within a test.
 */
public interface BaseContext {

    /**
     * Hold {@link WebDriver} instance created when new test is started.
     *
     * @return selenium {@link WebDriver}
     */
    WebDriver webDriver();
}
