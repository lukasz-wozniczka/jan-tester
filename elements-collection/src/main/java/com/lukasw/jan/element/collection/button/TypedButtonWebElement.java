package com.lukasw.jan.element.collection.button;

import com.lukasw.jan.element.core.JanElement;

/**
 * Extendable {@link ButtonWebElement} interface allows you chain method invocation with super type methods.
 *
 * @param <T> actual button web element type that extend {@link TypedButtonWebElement}
 * @see ButtonWebElement
 */
public interface TypedButtonWebElement<T extends TypedButtonWebElement<T>> extends JanElement<T> {


}
