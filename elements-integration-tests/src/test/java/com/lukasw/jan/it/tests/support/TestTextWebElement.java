package com.lukasw.jan.it.tests.support;

import com.lukasw.jan.element.collection.text.AbstractTextWebElement;
import com.lukasw.jan.element.collection.text.TextWebElement;
import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

public class TestTextWebElement extends AbstractTextWebElement<TextWebElement> implements TextWebElement {
    public TestTextWebElement(final WebElement webElement, final PageContext pageContext) {
        super(webElement, pageContext);
    }

    @Nonnull
    @Override
    protected TextWebElement subReference() {
        return this;
    }
}
