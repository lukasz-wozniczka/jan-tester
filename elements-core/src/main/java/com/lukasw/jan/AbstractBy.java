package com.lukasw.jan;

import org.openqa.selenium.By;

import javax.annotation.Nonnull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

/**
 * Abstract locator to find an related element within a document.
 *
 * @param <T> related type fo the element specified by locator
 */
public abstract class AbstractBy<T extends AbstractElement<T>> {

    private final By by;

    protected AbstractBy(@Nonnull final By by) {
        this.by = requireNonNull(by, format("Exception while trying to create an instance of the %s, by argument cannot be null.", getClass().getSimpleName()));
    }

    protected abstract T createElement(ElementContext<T> elementContext);

    public String getDescription() {
        return getClass().getSimpleName();
    }

    public final By getWrapped() {
        return this.by;
    }
}
