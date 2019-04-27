package com.lukasw.jan;

import org.openqa.selenium.WebElement;

public interface ElementContext<T extends AbstractElement<T>> extends BaseContext {

    /**
     * Hold {@link WebElement} by element context.
     *
     * @return web element of this context
     */
    WebElement webElement();
}
