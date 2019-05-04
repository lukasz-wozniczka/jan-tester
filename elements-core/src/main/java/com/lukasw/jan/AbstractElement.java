package com.lukasw.jan;

import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

/**
 * Abstract element to wrap {@link WebElement} and define common actions for all types of element.
 */
public abstract class AbstractElement<T extends ElementActions<T>> implements HasDescription, ElementActions<T> {

    private final WebElement webElement;
    private final BaseContext baseContext;

    public AbstractElement(@Nonnull final WebElement webElement, @Nonnull final BaseContext baseContext) {
        this.webElement = Arguments.argumentNotNull(webElement, "webElement", getClass());
        this.baseContext = Arguments.argumentNotNull(baseContext, "baseContext", getClass());
    }

    /**
     * Perform an actions on this element.
     *
     * @param actions to be execute
     * @return the result of the actions execution
     */
    public final T perform(final ActionExecutor<T> actions) {
        return actions.execute(getElementActions());
    }

    /**
     * Get specific element actions.
     *
     * @return specific element actions
     */
    protected abstract T getElementActions();


    @Override
    public String getTagName() {
        return this.webElement.getTagName();
    }

    @Override
    public T click() {
        this.webElement.click();
        return getElementActions();
    }

    //TODO this should be used as get parameter
    public String getValue() {
        return this.webElement.getAttribute("value");
    }
}
