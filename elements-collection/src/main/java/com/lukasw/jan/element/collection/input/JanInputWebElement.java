package com.lukasw.jan.element.collection.input;

import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

class JanInputWebElement extends AbstractInputWebElement<InputWebElement> implements InputWebElement {

    JanInputWebElement(final WebElement webElement, final PageContext pageContext) {
        super(webElement, pageContext);
    }

    @Nonnull
    @Override
    protected InputWebElement subReference() {
        return this;
    }
}