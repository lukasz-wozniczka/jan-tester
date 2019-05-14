package com.lukasw.jan.element.collection.input;

import com.lukasw.jan.element.core.AbstractJanElement;
import com.lukasw.jan.element.core.context.PageContext;
import com.lukasw.jan.element.core.support.ValueAs;
import org.openqa.selenium.WebElement;

/**
 * Base implementation of {@link InputWebElement} to extend.
 *
 * @param <T> specific type of the {@link InputWebElement}
 */
public abstract class AbstractInputWebElement<T extends TypedInputWebElement<T>> extends AbstractJanElement<T> implements TypedInputWebElement<T> {

    public AbstractInputWebElement(final WebElement webElement, final PageContext pageContext) {
        super(webElement, pageContext);
    }


    @Override
    public T setValue(final Object text) {
        clear().appendValue(text);
        return subReference();
    }

    @Override
    public T appendValue(final Object text) {
        webElement().sendKeys(text.toString());
        return subReference();
    }

    @Override
    public T clear() {
        webElement().clear();
        return subReference();
    }

    @Override
    public String getValue() {
        return webElement().getAttribute("value");
    }

    @Override
    public ValueAs getValueAs() {
        return ValueAs.of(getValue());
    }

    @Override
    public boolean isEnabled() {
        return webElement().isEnabled();
    }
}
