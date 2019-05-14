package com.lukasw.jan.element.core.bind;

import com.lukasw.jan.element.core.JanElement;
import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Define the process of how the {@link org.openqa.selenium.WebElement} should be found and binded to the specific web element type.
 * <p>
 *
 * @param <T> the type of element that this binder is pointing to
 */
public interface Bindable<T extends JanElement<T>> {

    /**
     * Default finder interceptor used when none of the customs is specified.
     */
    FinderInterceptor DEFAULT_FINDER_INTERCEPTOR = FinderInterceptor.Default.INSTANCE;

    /**
     * Get the list of {@link By} locators.
     * The {@link com.lukasw.jan.element.core.bind.WebElementFinder} will try to find the element by using those list of by locators.
     *
     * @return list of {@link By} locators
     */
    @Nonnull
    List<By> getByLocators();

    /**
     * Get element type that this locator is pointing to.
     *
     * @return element type
     */
    @Nonnull
    Class<T> getElementType();

    /**
     * Get element finder interceptor specified by this binder.
     *
     * @return element finder interceptor
     */
    @Nonnull
    FinderInterceptor getFinderInterceptor();


    /**
     * Define how to bind selenium 'raw' {@link WebElement} to create specific type of web element.
     *
     * @param webElement  to wrap
     * @param pageContext element page context
     * @return specified type of web element
     */
    @Nonnull
    T bind(WebElement webElement, PageContext pageContext);
}
