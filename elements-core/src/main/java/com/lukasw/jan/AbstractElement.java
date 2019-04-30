package com.lukasw.jan;

import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

/**
 * Abstract element class to wrap {@link WebElement} and define base methods for specific elements.
 */
public abstract class AbstractElement implements HasDescription {

    private final WebElement webElement;
    private final BaseContext baseContext;

    public AbstractElement(@Nonnull final WebElement webElement, @Nonnull final BaseContext baseContext) {
        this.webElement = Arguments.argumentNotNull(webElement, "webElement", getClass());
        this.baseContext = Arguments.argumentNotNull(baseContext, "baseContext", getClass());
    }

    /**
     * Hold underlying {@link WebElement}.
     *
     * @return WebElement
     */
    protected final WebElement webElement() {
        return this.webElement;
    }

    /**
     * Get the tag name of this element. <b>Not</b> the value of the name attribute: will return
     * <code>"input"</code> for the element <code>&lt;input name="foo" /&gt;</code>.
     *
     * @return The tag name of this element.
     */
    public String getTagName() {
        return this.webElement.getTagName();
    }
}
