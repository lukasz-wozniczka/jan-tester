package com.lukasw.jan.element.collection.text;

import com.lukasw.jan.element.core.bind.AbstractBy;
import com.lukasw.jan.element.core.bind.FinderInterceptor;
import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import java.util.List;

public final class TextBy extends AbstractBy<TextWebElement, TextBy> {

    public TextBy(final List<By> listOfLocators, final FinderInterceptor interceptor) {
        super(listOfLocators, interceptor);
    }

    public TextBy(final List<By> listOfLocators) {
        super(listOfLocators);
    }

    public TextBy(final By byLocator) {
        super(byLocator);
    }

    public static TextBy id(final String id) {
        return new TextBy(By.id(id));
    }

    public static TextBy xpath(final String xpath) {
        return new TextBy(By.xpath(xpath));
    }

    public static TextBy name(final String name) {
        return new TextBy(By.name(name));
    }

    public static TextBy linkText(final String linkText) {
        return new TextBy(By.linkText(linkText));
    }

    public static TextBy partialLinkText(final String partialLinkText) {
        return new TextBy(By.partialLinkText(partialLinkText));
    }

    public static TextBy className(final String className) {
        return new TextBy(By.className(className));
    }

    public static TextBy cssSelector(final String cssSelector) {
        return new TextBy(By.cssSelector(cssSelector));
    }

    public static TextBy tagName(final String tagName) {
        return new TextBy(By.tagName(tagName));

    }

    @Override
    protected TextBy subReference() {
        return this;
    }

    @Nonnull
    @Override
    public Class<TextWebElement> getElementType() {
        return TextWebElement.class;
    }

    @Override
    public TextWebElement bind(final WebElement webElement, final PageContext pageContext) {
        return new JanTextWebElement(webElement, pageContext);
    }


}
