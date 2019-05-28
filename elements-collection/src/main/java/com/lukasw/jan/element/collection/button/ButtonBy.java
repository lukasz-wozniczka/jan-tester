package com.lukasw.jan.element.collection.button;

import com.lukasw.jan.element.core.bind.AbstractBy;
import com.lukasw.jan.element.core.bind.FinderInterceptor;
import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import java.util.List;

public final class ButtonBy extends AbstractBy<ButtonWebElement, ButtonBy> {


    public ButtonBy(final List<By> listOfLocators, final FinderInterceptor interceptor) {
        super(listOfLocators, interceptor);
    }

    public ButtonBy(final List<By> listOfLocators) {
        super(listOfLocators);
    }

    public ButtonBy(final By byLocator) {
        super(byLocator);
    }

    public static ButtonBy id(final String id) {
        return new ButtonBy(new By.ById(id));
    }

    public static ButtonBy xpath(final String xpath) {
        return new ButtonBy(By.xpath(xpath));
    }

    public static ButtonBy name(final String name) {
        return new ButtonBy(By.name(name));
    }

    public static ButtonBy linkText(final String linkText) {
        return new ButtonBy(By.linkText(linkText));
    }

    public static ButtonBy partialLinkText(final String partialLinkText) {
        return new ButtonBy(By.partialLinkText(partialLinkText));
    }

    public static ButtonBy className(final String className) {
        return new ButtonBy(By.className(className));
    }

    public static ButtonBy cssSelector(final String cssSelector) {
        return new ButtonBy(By.cssSelector(cssSelector));
    }

    public static ButtonBy tagName(final String tagName) {
        return new ButtonBy(By.tagName(tagName));

    }

    @Override
    protected ButtonBy subReference() {
        return this;
    }

    @Nonnull
    @Override
    public Class<ButtonWebElement> getElementType() {
        return ButtonWebElement.class;
    }

    @Override
    public ButtonWebElement bind(final WebElement webElement, final PageContext pageContext) {
        return new JanButtonWebElement(webElement, pageContext);
    }
}
