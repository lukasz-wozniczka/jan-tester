package com.lukasw.jan;

/**
 * Executes defines element actions.
 *
 * @param <T> element action interface
 */
@FunctionalInterface
public interface ActionExecutor<T extends ElementActions<T>> {

    /**
     * Executes actions
     *
     * @param actions to execute
     * @return action result
     */
    T execute(T actions);
}