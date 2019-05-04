package com.lukasw.jan;

import org.openqa.selenium.StaleElementReferenceException;

/**
 * Base interface to define common element actions that user can perform.
 */
public interface ElementActions<T extends ElementActions<T>> {

    /**
     * Get the tag name of this element. <b>Not</b> the value of the name attribute: will return
     * <code>"input"</code> for the element <code>&lt;input name="foo" /&gt;</code>.
     *
     * @return The tag name of this element.
     */
    String getTagName();

    /**
     * Click this element. If this causes a new page to load, you
     * should discard all references to this element and any further
     * operations performed on this element will throw a
     * StaleElementReferenceException.
     * <p>
     * Note that if click() is done by sending a native event (which is
     * the default on most browsers/platforms) then the method will
     * _not_ wait for the next page to load and the caller should verify
     * that themselves.
     * <p>
     * There are some preconditions for an element to be clicked. The
     * element must be visible and it must have a height and width
     * greater then 0.
     *
     * @return return specific element actions to chain invocation
     * @throws StaleElementReferenceException If the element no
     *                                        longer exists as initially defined
     */
    T click();

}
