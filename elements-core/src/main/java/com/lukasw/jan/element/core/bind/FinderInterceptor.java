package com.lukasw.jan.element.core.bind;

import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Interceptor of the element searching operation.
 * Can be used to perform some actions before or after element searching operation, like for example logging, wait conditions etc.
 *
 * @see PageContext
 */
@FunctionalInterface
public interface FinderInterceptor {

    /**
     * Main method that intercept element finding operation.
     *
     * @param toDecorate  element search operation to decorate
     * @param by          by locator
     * @param elementType that we want to bind
     * @param pageContext page context of this web element
     * @return instance of {@link WebElement}
     */
    WebElement intercept(WebElementFinder toDecorate, By by, Class<?> elementType, PageContext pageContext);

    /**
     * Default implementation of a {@link FinderInterceptor} which delegate operation to the 'original' {@link WebElementFinder}
     */
    enum Default implements FinderInterceptor {
        INSTANCE;

        @Override
        public WebElement intercept(final WebElementFinder toDecorate, final By by, final Class<?> elementType, final PageContext baseContext) {
            return toDecorate.findBy(by, baseContext);
        }
    }
}
