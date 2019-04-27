package com.lukasw.jan;

import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

/**
 * Abstract element class to hold {@link ElementContext} and define base methods for specific elements.
 *
 * @param <T> the concrete sub type of {@link AbstractElement}
 */
public class AbstractElement<T extends AbstractElement<T>> {

    private final ElementContext<T> elementContext;

    protected AbstractElement(@Nonnull final ElementContext<T> elementContext) {
        this.elementContext = requireNonNull(elementContext, format("Exception while trying to create an instance of the %s, elementContext argument cannot be null.", getClass().getSimpleName()));
    }

    /**
     * Hold underlying {@link WebElement}.
     *
     * @return WebElement
     */
    protected final WebElement webElement() {
        return this.elementContext.webElement();
    }
}
