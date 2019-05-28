package com.lukasw.jan.element.collection.text;

import com.lukasw.jan.element.core.AbstractJanElement;
import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.WebElement;

/**
 * Base implementation of {@link TextWebElement} to extend.
 *
 * @param <T> specific type of the {@link TextWebElement}
 */
public abstract class AbstractTextWebElement<T extends TypedTextWebElement<T>> extends AbstractJanElement<T> implements TypedTextWebElement<T> {

    public AbstractTextWebElement(final WebElement webElement, final PageContext pageContext) {
        super(webElement, pageContext);
    }

    @Override
    public String getText() {
        return webElement().getText();
    }
}
