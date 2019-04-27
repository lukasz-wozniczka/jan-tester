package com.lukasw.jan;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class DefaultElementContext<T extends AbstractElement<T>> implements ElementContext<T> {

    private final WebElement webElement;
    private final BaseContext baseContext;

    DefaultElementContext(final WebElement webElement, final BaseContext baseContext) {
        this.webElement = webElement;
        this.baseContext = baseContext;
    }

    @Override
    public WebElement webElement() {
        return this.webElement;
    }

    @Override
    public WebDriver webDriver() {
        return this.baseContext.webDriver();
    }
}
