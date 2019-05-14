package com.lukasw.jan.element.collection.text;

import com.lukasw.jan.element.core.JanElement;

/**
 * Extendable {@link TextWebElement} interface allows you chain method invocation with super type methods.
 *
 * @param <T> actual text web element type that extend {@link TypedTextWebElement}
 * @see TextWebElement
 */
public interface TypedTextWebElement<T extends TypedTextWebElement<T>> extends JanElement<T> {

    /**
     * Get the visible (i.e. not hidden by CSS) text of this element, including sub-elements.
     *
     * @return The visible text of this element.
     * @see <a href="https://w3c.github.io/webdriver/#get-element-text">"Get Element Text" section
     * in W3C WebDriver Specification</a>
     */
    String getText();
}
