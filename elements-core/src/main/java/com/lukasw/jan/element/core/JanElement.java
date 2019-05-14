package com.lukasw.jan.element.core;

import com.lukasw.jan.element.core.support.Wrapped;
import org.openqa.selenium.WebElement;

/**
 * Base element interface to define common methods for all web elements.
 *
 * @param <T> the specific subtype of {@link JanElement}
 */
public interface JanElement<T extends JanElement<T>> extends Wrapped<WebElement> {

    /**
     * Click this element.
     *
     * @return subtype reference of {@link JanElement}
     */
    T click();

    /**
     * Click this element specified times.
     *
     * @param times to click this element
     * @return subtype reference of {@link JanElement}
     */
    T clickTimes(int times);


    /**
     * Double click on this element.
     *
     * @return subtype reference of {@link JanElement}
     */
    T doubleClick();

    /**
     * Get HTML tag name.
     */
    String getTagName();


    /**
     * Is this element displayed or not? This method avoids the problem of having to parse an
     * element's "style" attribute.
     *
     * @return Whether or not the element is displayed
     */
    boolean isDisplayed();
}
