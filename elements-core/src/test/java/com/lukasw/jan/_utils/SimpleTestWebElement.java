package com.lukasw.jan._utils;

import com.lukasw.jan.element.core.AbstractJanElement;
import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

public class SimpleTestWebElement extends AbstractJanElement<TestWebElement> implements TestWebElement {

    public SimpleTestWebElement(final WebElement webElement, final PageContext pageContext) {
        super(webElement, pageContext);
    }

    @Nonnull
    @Override
    protected TestWebElement subReference() {
        return this;
    }
}
