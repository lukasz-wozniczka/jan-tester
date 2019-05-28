package com.lukasw.jan.element.core.bind;

import com.lukasw.jan.element.core.JanElementException;
import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TooManyElementsException extends JanElementException {

    public TooManyElementsException(final List<WebElement> elements, final By by, final PageContext pageContext) {
        super(createMessage(elements, by, pageContext));
    }

    private static String createMessage(final List<WebElement> elements, final By by, final PageContext pageContext) {

        final StringBuilder sb = new StringBuilder(elements.size() * 80);
        int i = 1;
        for (final WebElement element : elements) {
            if (i <= elements.size()) {
                sb.append(System.lineSeparator());
            }
            sb.append(i).append(") ").append(element);
            i++;
        }

        return String.format("Exception while trying to find element by locator: '%s', on a page '%s'.%nExpected that one element is present but found: %s", by, pageContext.getName(), sb.toString());
    }
}
