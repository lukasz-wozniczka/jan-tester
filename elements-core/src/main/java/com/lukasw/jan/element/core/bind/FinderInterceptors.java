package com.lukasw.jan.element.core.bind;

import java.time.Duration;

/**
 * This class consists exclusively of static methods that return different types of {@link FinderInterceptor}s.
 */
public final class FinderInterceptors {

    /**
     * Factory method to create {@link WaitFinderInterceptor} to wait for an element to be visible with default settings.
     *
     * @return new {@link WaitFinderInterceptor}
     */
    public static WaitFinderInterceptor exist() {
        return new WaitFinderInterceptor();
    }

    /**
     * Factory methods to create {@link WaitFinderInterceptor} to wait for an element to be visible with specific timeout and other default settings.
     *
     * @param timeout to wait for element
     * @return new {@link WaitFinderInterceptor}
     */
    public static WaitFinderInterceptor exist(final Duration timeout) {
        return new WaitFinderInterceptor(timeout);
    }
}
