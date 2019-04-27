package com.lukasw.jan.support;

import com.google.common.annotations.VisibleForTesting;
import com.lukasw.jan.AbstractBy;
import com.lukasw.jan.ElementContext;
import org.openqa.selenium.By;

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
    protected TestElement createElement(final ElementContext<TestElement> elementContext) {
        return new TestElement(elementContext);
    }
}
