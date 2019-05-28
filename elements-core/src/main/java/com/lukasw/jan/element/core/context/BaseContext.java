package com.lukasw.jan.element.core.context;

/**
 * Define the base methods for all kind of contexts.
 */
public interface BaseContext {

    /**
     * Get test name.
     *
     * @return test name.
     */
    String getName();

    /**
     * Get Human readable test description.
     *
     * @return test description
     */
    String getDescription();


    /**
     * Return selenium context.
     *
     * @return selenium context
     */
    SeleniumContext selenium();

}
