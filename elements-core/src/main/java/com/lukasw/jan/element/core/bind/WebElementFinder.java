package com.lukasw.jan.element.core.bind;

import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Define element search operation.
 */
@FunctionalInterface
public interface WebElementFinder {

    @Nullable
    WebElement findBy(By by, PageContext pageContext);

    /**
     * Default implementation of a {@link WebElementFinder}.
     */
    enum Default implements WebElementFinder {
        INSTANCE;

        @Override
        public WebElement findBy(final By by, final PageContext pageContext) {
            final List<WebElement> elements = pageContext.selenium().findElements(by);
            if (elements.size() == 1) {
                return elements.get(0);
            } else if (elements.isEmpty()) {
                return null;
            } else {
                throw new TooManyElementsException(elements, by, pageContext);
            }

        }
    }
}
