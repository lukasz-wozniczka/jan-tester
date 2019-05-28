package com.lukasw.jan.it.tests.support;

import com.lukasw.jan.element.collection.input.InputWebElement;
import com.lukasw.jan.element.core.bind.AbstractBy;
import com.lukasw.jan.element.core.bind.FinderInterceptor;
import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import java.util.List;

public class MyInputBy extends AbstractBy<InputWebElement, MyInputBy> {


    public MyInputBy(final List<By> listOfLocators, final FinderInterceptor interceptor) {
        super(listOfLocators, interceptor);
    }

    public MyInputBy(final List<By> listOfLocators) {
        super(listOfLocators);
    }

    public MyInputBy(final By byLocator) {
        super(byLocator);
    }

    @Override
    protected MyInputBy subReference() {
        return this;
    }

    @Nonnull
    @Override
    public Class<InputWebElement> getElementType() {
        return InputWebElement.class;
    }

    @Override
    public InputWebElement bind(final WebElement webElement, final PageContext pageContext) {
        return new MyInputWebElement(webElement, pageContext);
    }
}
