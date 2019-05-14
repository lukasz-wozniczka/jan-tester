package com.lukasw.jan.it.tests.support;

import com.lukasw.jan.element.collection.input.AbstractInputWebElement;
import com.lukasw.jan.element.collection.input.InputWebElement;
import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

class MyInputWebElement extends AbstractInputWebElement<InputWebElement> implements InputWebElement {

    public MyInputWebElement(final WebElement webElement, final PageContext pageContext) {
        super(webElement, pageContext);
    }

    @Nonnull
    @Override
    protected InputWebElement subReference() {
        return this;
    }
}
