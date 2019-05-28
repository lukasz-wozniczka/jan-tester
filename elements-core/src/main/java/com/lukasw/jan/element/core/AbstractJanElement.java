package com.lukasw.jan.element.core;

import com.lukasw.jan.element.core.context.JanSeleniumContext;
import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.annotation.Nonnull;

/**
 * Abstract class to create custom web element.
 *
 * @param <T> the type of the custom web element, it has to extends {@link JanElement}
 */
public abstract class AbstractJanElement<T extends JanElement<T>> implements JanElement<T> {

    private final WebElement webElement;
    private final PageContext pageContext;
    private final Actions actions;

    public AbstractJanElement(final WebElement webElement, final PageContext pageContext) {
        this.webElement = webElement;
        this.pageContext = pageContext;
        this.actions = pageContext.selenium().actions();
    }

    protected final WebElement webElement() {
        return this.webElement;
    }

    protected final JanSeleniumContext seleniumContext() {
        return this.seleniumContext();
    }

    /**
     * Provide subtype self reference that give an ability to chain method invocation.
     *
     * @return subtype reference
     */
    @Nonnull
    protected abstract T subReference();

    @Override
    public T click() {
        this.webElement.click();
        return subReference();
    }

    @Override
    public T clickTimes(final int times) {
        this.actions.moveToElement(this.webElement);
        for (int i = 0; i < times; i++) {
            this.actions.click();
        }
        this.actions.perform();
        return subReference();
    }

    @Override
    public T doubleClick() {
        this.actions.doubleClick(this.webElement)
                .perform();
        return subReference();
    }

    @Override
    public String getTagName() {
        return this.webElement.getTagName();
    }

    @Override
    public boolean isDisplayed() {
        return this.webElement.isDisplayed();
    }

    @Override
    public WebElement unwrap() {
        return this.webElement;
    }
}
