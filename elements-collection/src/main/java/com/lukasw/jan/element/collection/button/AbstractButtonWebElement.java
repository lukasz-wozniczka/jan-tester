package com.lukasw.jan.element.collection.button;

import com.lukasw.jan.element.core.AbstractJanElement;
import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.WebElement;

/**
 * Base implementation of {@link ButtonWebElement} to extend.
 *
 * @param <T> specific type of the {@link ButtonWebElement}
 */
public abstract class AbstractButtonWebElement<T extends TypedButtonWebElement<T>> extends AbstractJanElement<T> implements TypedButtonWebElement<T> {

    public AbstractButtonWebElement(final WebElement webElement, final PageContext pageContext) {
        super(webElement, pageContext);
    }


}
