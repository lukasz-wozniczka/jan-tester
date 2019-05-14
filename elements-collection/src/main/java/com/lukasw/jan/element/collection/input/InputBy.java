package com.lukasw.jan.element.collection.input;

import com.lukasw.jan.element.core.bind.AbstractBy;
import com.lukasw.jan.element.core.bind.FinderInterceptor;
import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import java.util.List;

public final class InputBy extends AbstractBy<InputWebElement, InputBy> {

    protected InputBy(final List<By> listOfLocators, final FinderInterceptor interceptor) {
        super(listOfLocators, interceptor);
    }

    protected InputBy(final List<By> listOfLocators) {
        super(listOfLocators);
    }

    public InputBy(final By byLocator) {
        super(byLocator);
    }

    public static InputBy id(final String id) {
        return new InputBy(By.id(id));
    }

    public static InputBy xpath(final String xpath) {
        return new InputBy(By.xpath(xpath));
    }

    public static InputBy name(final String name) {
        return new InputBy(By.name(name));
    }

    public static InputBy linkText(final String linkText) {
        return new InputBy(By.linkText(linkText));
    }

    public static InputBy partialLinkText(final String partialLinkText) {
        return new InputBy(By.partialLinkText(partialLinkText));
    }

    public static InputBy className(final String className) {
        return new InputBy(By.className(className));
    }

    public static InputBy cssSelector(final String cssSelector) {
        return new InputBy(By.cssSelector(cssSelector));
    }

    public static InputBy tagName(final String tagName) {
        return new InputBy(By.tagName(tagName));

    }

    @Override
    protected InputBy subReference() {
        return this;
    }

    @Nonnull
    @Override
    public Class<InputWebElement> getElementType() {
        return InputWebElement.class;
    }

    @Override
    public InputWebElement bind(final WebElement webElement, final PageContext pageContext) {
        return new JanInputWebElement(webElement, pageContext);
    }

}
