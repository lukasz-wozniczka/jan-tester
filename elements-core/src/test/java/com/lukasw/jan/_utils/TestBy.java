package com.lukasw.jan._utils;

import com.lukasw.jan.element.core.bind.AbstractBy;
import com.lukasw.jan.element.core.bind.FinderInterceptor;
import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import java.util.List;

public class TestBy extends AbstractBy<TestWebElement, TestBy> {

    public TestBy(final List<By> listOfLocators, final FinderInterceptor interceptor) {
        super(listOfLocators, interceptor);
    }

    public TestBy(final List<By> listOfLocators) {
        super(listOfLocators);
    }

    public TestBy(final By byLocator) {
        super(byLocator);
    }

    public static TestBy id(final String id) {
        return new TestBy(By.id(id));
    }


    @Override
    protected TestBy subReference() {
        return this;
    }

    @Nonnull
    @Override
    public Class<TestWebElement> getElementType() {
        return TestWebElement.class;
    }

    @Nonnull
    @Override
    public TestWebElement bind(final WebElement webElement, final PageContext pageContext) {
        return new SimpleTestWebElement(webElement, pageContext);
    }
}
