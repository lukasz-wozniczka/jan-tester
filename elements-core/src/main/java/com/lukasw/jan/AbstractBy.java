package com.lukasw.jan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

import static com.lukasw.jan.Arguments.argumentNotNull;


/**
 * Abstract locator to find an related element within a document.
 *
 * @param <T> related type fo the element specified by locator
 */
public abstract class AbstractBy<T extends AbstractElement> implements HasDescription {

    private final By by;

    protected AbstractBy(@Nonnull final By by) {
        this.by = argumentNotNull(by, "by", getClass());
    }

    protected abstract T createElement(WebElement webElement, BaseContext baseContext);

    public final By getWrapped() {
        return this.by;
    }
}
