package com.lukasw.jan.element.core.context;

import com.lukasw.jan.element.core.JanElement;
import com.lukasw.jan.element.core.bind.Bindable;
import com.lukasw.jan.element.core.bind.FinderInterceptor;
import com.lukasw.jan.element.core.bind.WebElementFinder;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import java.util.Objects;

import static com.lukasw.jan.element.core.support.Arguments.argumentNotNull;
import static org.slf4j.LoggerFactory.getLogger;

@Immutable
public final class PageContext implements BaseContext {

    private static final Logger LOG = getLogger(PageContext.class);

    private final SeleniumContext seleniumContext;
    private final WebElementFinder webElementFinder;
    private final String name;
    private final String description;

    private PageContext(final Builder builder) {
        this.seleniumContext = argumentNotNull(builder.seleniumContext, "seleniumContext", getClass());
        this.webElementFinder = builder.webElementFinder == null ? WebElementFinder.Default.INSTANCE : builder.webElementFinder;
        this.name = argumentNotNull(builder.name, "name", getClass());
        this.description = argumentNotNull(builder.description, "description", getClass());
    }

    /**
     * Factory method to get {@link PageContext} builder.
     *
     * @return builder for PageContext
     */
    public static Builder builder(final SeleniumContext seleniumContext) {
        return new Builder(seleniumContext);
    }

    @Override
    public SeleniumContext selenium() {
        return this.seleniumContext;
    }

    /**
     * Find the first element of type {@code T} by given locator.
     *
     * @param locator the locator of the element
     * @param <T>     element type
     * @return the firs matching element of type {@code T}
     * @throws org.openqa.selenium.NoSuchElementException If no matching elements are found
     */
    public <T extends JanElement<T>> T findBy(final Bindable<T> locator) {
        final FinderInterceptor finderDecorator = locator.getFinderInterceptor();
        return findBy(locator, finderDecorator);
    }

    /**
     * Search the web page for a {@link WebElement} by provided locators defined in {@link Bindable} object and then wrap 'raw' {@link WebElement} to provide
     * specific web element type.
     *
     * @param binder            to define how to bind and provide list of locators
     * @param finderInterceptor interceptor to decorate finding operation
     * @param <T>               the type of the specific web element
     * @return new specific web element instance
     */
    public <T extends JanElement<T>> T findBy(final Bindable<T> binder, final FinderInterceptor finderInterceptor) {
        final WebElement webElement = binder.getByLocators()
                .stream()
                .map(by -> finderInterceptor.intercept(this.webElementFinder, by, binder.getElementType(), this))
                .filter(Objects::nonNull)
                .findAny()
                .orElseThrow(() -> new ElementNotFoundException(binder, this));

        return binder.bind(webElement, this);
    }


    public WebElementFinder getWebElementFinder() {
        return this.webElementFinder;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }


    public static class Builder {
        private final SeleniumContext seleniumContext;
        private WebElementFinder webElementFinder;
        private String name;
        private String description;

        public Builder(final SeleniumContext seleniumContext) {
            this.seleniumContext = seleniumContext;
        }

        public Builder webElementFinder(final WebElementFinder webElementFinder) {
            this.webElementFinder = webElementFinder;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        public PageContext build() {
            return new PageContext(this);
        }
    }
}