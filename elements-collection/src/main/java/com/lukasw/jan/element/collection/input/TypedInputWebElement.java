package com.lukasw.jan.element.collection.input;

import com.lukasw.jan.element.core.JanElement;
import com.lukasw.jan.element.core.support.ValueAs;

/**
 * Extendable {@link InputWebElement} interface allows you chain method invocation with super type methods.
 *
 * @param <T> actual input web element type that extend {@link TypedInputWebElement}
 * @see InputWebElement
 */
public interface TypedInputWebElement<T extends TypedInputWebElement<T>> extends JanElement<T> {

    /**
     * Append the value to the input field.
     *
     * @param value to append
     * @return sub type reference
     */
    T appendValue(Object value);

    /**
     * Clear input field.
     *
     * @return sub type reference
     */
    T clear();

    /**
     * Get value of the input field as text.
     *
     * @return input value
     */
    String getValue();

    /**
     * Set input field with the specific value. The field 'value' will be provided by calling {@link Object#toString()} on the object value.
     * Equivalent to run in order {@link TypedInputWebElement#clear()} with {@link TypedInputWebElement#appendValue(Object)}}
     *
     * @param value to set
     * @return sub type reference
     */
    T setValue(Object value);

    /**
     * Get {@link ValueAs} container that allows you convert string value to custom types.
     *
     * @return value as container
     */
    ValueAs getValueAs();

    /**
     * Is the element currently enabled or not? This will generally return true for everything but
     * disabled input elements.
     *
     * @return True if the element is enabled, false otherwise.
     */
    boolean isEnabled();
}
