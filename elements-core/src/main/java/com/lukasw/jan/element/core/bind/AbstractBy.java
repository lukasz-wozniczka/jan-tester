package com.lukasw.jan.element.core.bind;

import com.lukasw.jan.element.core.JanElement;
import org.openqa.selenium.By;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base implementation for {@link Bindable} object.
 *
 * @param <T> specific type of web element
 * @param <U> subtype of this {@link AbstractBy}
 */
public abstract class AbstractBy<T extends JanElement<T>, U extends AbstractBy<T, U>> implements Bindable<T> {

    private static final int BY_LOCATORS_MAJORITY_SIZE = 1;

    private final List<By> byLocators = new ArrayList<>(BY_LOCATORS_MAJORITY_SIZE);
    private FinderInterceptor interceptor;

    public AbstractBy(final List<By> listOfLocators, final FinderInterceptor interceptor) {
        this.byLocators.addAll(listOfLocators);
        this.interceptor = interceptor;
    }

    public AbstractBy(final List<By> listOfLocators) {
        this(listOfLocators, Bindable.DEFAULT_FINDER_INTERCEPTOR);
    }

    public AbstractBy(final By byLocator) {
        this(Collections.singletonList(byLocator), Bindable.DEFAULT_FINDER_INTERCEPTOR);
    }

    /**
     * Provide sub type reference of this type to chain method invocation.
     *
     * @return sub reference of {@link AbstractBy} type
     */
    protected abstract U subReference();


    /**
     * Add the XPath locator to the end of the by locators list.
     *
     * @param xpath expression to search element by
     * @return subtype reference
     */
    public U orByXpath(final String xpath) {
        return addAnotherLocator(By.xpath(xpath));
    }


    /**
     * Add the Id locator to the end of the by locators list.
     *
     * @param id attribute to search element by
     * @return subtype reference
     */
    public U orById(final String id) {
        return addAnotherLocator(By.id(id));
    }

    /**
     * Add the Name locator to the end of the by locators list.
     *
     * @param name attribute to search element by
     * @return subtype reference
     */
    public U orByName(final String name) {
        return addAnotherLocator(By.name(name));
    }

    /**
     * @param linkText The exact text to match against.
     * @return subtype reference
     */
    public U orByLinkText(final String linkText) {
        return addAnotherLocator(By.linkText(linkText));
    }

    /**
     * @param partialLinkText The partial text to match against
     * @return subtype reference
     */
    public U orByPartialLinkText(final String partialLinkText) {
        return addAnotherLocator(By.partialLinkText(partialLinkText));
    }

    public U orByTagName(final String tagName) {
        return addAnotherLocator(By.tagName(tagName));
    }

    public U orByCssSelector(final String cssSelector) {
        return addAnotherLocator(By.cssSelector(cssSelector));
    }

    public U orByClassName(final String className) {
        return addAnotherLocator(By.className(className));
    }


    /**
     * Add {@link FinderInterceptor} to be used to intercept search {@link org.openqa.selenium.WebElement} operation.
     *
     * @param interceptor to be used
     * @return subtype reference
     * @see WebElementFinder
     */
    public U withInterceptor(final FinderInterceptor interceptor) {
        this.interceptor = interceptor;
        return subReference();
    }

    private U addAnotherLocator(final By by) {
        this.byLocators.add(by);
        return subReference();
    }


    @Nonnull
    @Override
    public List<By> getByLocators() {
        return Collections.unmodifiableList(this.byLocators);
    }

    @Nonnull
    @Override
    public FinderInterceptor getFinderInterceptor() {
        return this.interceptor;
    }
}