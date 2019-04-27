package com.lukasw.jan;

/**
 * The factory to find elements on the document.
 */
interface ElementFactory {

    /**
     * Find the first element of type {@code T} by given locator.
     *
     * @param by  the locator of the element
     * @param <T> element type
     * @return the firs matching element of type {@code T}
     * @throws org.openqa.selenium.NoSuchElementException If no matching elements are found
     */
    <T extends AbstractElement<T>> T findBy(final AbstractBy<T> by);

}
