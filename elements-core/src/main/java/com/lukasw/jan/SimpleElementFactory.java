package com.lukasw.jan;

import org.openqa.selenium.WebElement;

/**
 * Default implementation of {@link ElementFactory} that can perform all actions by using {@link BaseContext}.
 */
public class SimpleElementFactory implements ElementFactory {

    private final BaseContext baseContext;

    public SimpleElementFactory(final BaseContext baseContext) {
        this.baseContext = baseContext;
    }

    @Override
    public <T extends AbstractElement<T>> T findBy(final AbstractBy<T> by) {
        final WebElement webElement = this.baseContext.webDriver().findElement(by.getWrapped());
        final ElementContext<T> elementContext = new DefaultElementContext<>(webElement, this.baseContext);
        return by.createElement(elementContext);
    }
}
