package com.lukasw.jan.element.core.context;

import com.lukasw.jan.element.core.JanElement;
import com.lukasw.jan.element.core.JanElementException;
import com.lukasw.jan.element.core.bind.Bindable;
import org.openqa.selenium.By;

import java.util.List;

/**
 * An Exception thrown when cannot find web element on a specific page.
 */
public final class ElementNotFoundException extends JanElementException {

    private static final int BY_LOCATORS_MAJOR_SIZE = 1;

    ElementNotFoundException(final Bindable<? extends JanElement<?>> bindable, final PageContext pageContext) {
        super(createMessage(bindable, pageContext));
    }

    private static <T extends JanElement<T>> String createMessage(final Bindable<T> bindable, final PageContext pageContext) {
        final List<By> byLocators = bindable.getByLocators();
        final String message;
        if (byLocators.size() == BY_LOCATORS_MAJOR_SIZE) {
            message = messageSingleLocator(bindable, pageContext, byLocators.get(0));
        } else {
            message = messageManyLocators(bindable, pageContext, byLocators);
        }
        return message;
    }

    private static <T extends JanElement<T>> String messageSingleLocator(final Bindable<T> bindable, final PageContext pageContext, final By by) {
        return String.format("Exception while trying to find an element of type: '%s', on a page with name: '%s' by locator: '%s'", bindable.getElementType().getSimpleName(), pageContext.getName(), by);
    }

    private static <T extends JanElement<T>> String messageManyLocators(final Bindable<T> bindable, final PageContext pageContext, final List<By> byLocators) {
        final StringBuilder locatorsList = new StringBuilder(byLocators.size() * 100);
        int i = 1;
        for (final By by : byLocators) {
            if (i <= byLocators.size()) {
                locatorsList.append(System.lineSeparator());
            }
            locatorsList.append(i).append(") ").append(by);
            i++;
        }
        return String.format("Exception while trying to find an element of type: '%s', on a page with name: '%s'.%nNone of the provided by locators match: %s", bindable.getElementType().getSimpleName(), pageContext.getName(), locatorsList.toString());
    }
}
