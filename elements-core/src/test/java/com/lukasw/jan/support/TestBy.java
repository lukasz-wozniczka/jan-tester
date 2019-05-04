package com.lukasw.jan.support;

import com.google.common.annotations.VisibleForTesting;
import com.lukasw.jan.AbstractBy;
import com.lukasw.jan.BaseContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

public class TestBy extends AbstractBy<TestElement> {

    @VisibleForTesting
    public TestBy(@Nonnull final By by) {
        super(by);
    }

    /**
     * Factory method to create {@link TestBy} locator by specific element id.
     *
     * @param id of the element to create locator
     * @return
     */
    public static TestBy id(final String id) {
        return new TestBy(By.id(id));
    }

    @Override
    protected TestElement createElement(final WebElement webElement, final BaseContext baseContext) {

        return new TestElement(webElement, baseContext);
    }

    @Override
    public String getDescription() {
        return "Test by";
    }
}
