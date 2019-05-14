package com.lukasw.jan.element.collection.text;

import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

class JanTextWebElement extends AbstractTextWebElement<TextWebElement> implements TextWebElement {

    JanTextWebElement(final WebElement webElement, final PageContext pageContext) {
        super(webElement, pageContext);
    }

    @Nonnull
    @Override
    protected TextWebElement subReference() {
        return this;
    }
}