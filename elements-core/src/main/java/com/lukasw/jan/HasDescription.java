package com.lukasw.jan;

/**
 * Provide description of the element. Can be useful for documentation or log purpose.
 */
@FunctionalInterface
public interface HasDescription {

    /**
     * Get human readable element description.
     *
     * @return element description
     */
    String getDescription();
}
