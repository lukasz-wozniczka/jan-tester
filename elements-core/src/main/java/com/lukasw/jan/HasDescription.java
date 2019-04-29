package com.lukasw.jan;

/**
 * Force the type to provide description of the element that can be useful for documentation or log purpose.
 */
public interface HasDescription {

    /**
     * Get human readable type description.
     *
     * @return type description
     */
    String getDescription();
}
