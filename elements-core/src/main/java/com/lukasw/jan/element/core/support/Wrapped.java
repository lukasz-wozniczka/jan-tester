package com.lukasw.jan.element.core.support;

import javax.annotation.Nonnull;

/**
 * Define object that is a wrapper of other object of type {@code T}.
 *
 * @param <T> wrapped object type
 */
@FunctionalInterface
public interface Wrapped<T> {

    /**
     * Gew wrapped value by this object.
     *
     * @return unwrapped value
     */
    @Nonnull
    T unwrap();
}
