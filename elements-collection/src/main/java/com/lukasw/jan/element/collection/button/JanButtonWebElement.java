package com.lukasw.jan.element.collection.button;

import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

class JanButtonWebElement extends AbstractButtonWebElement<ButtonWebElement> implements ButtonWebElement {

    JanButtonWebElement(final WebElement webElement, final PageContext pageContext) {
        super(webElement, pageContext);
    }

    @Nonnull
    @Override
    protected ButtonWebElement subReference() {
        return this;
    }
}