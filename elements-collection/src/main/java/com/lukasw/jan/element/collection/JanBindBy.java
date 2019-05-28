package com.lukasw.jan.element.collection;

import com.lukasw.jan.element.collection.button.ButtonBy;
import com.lukasw.jan.element.collection.button.ButtonWebElement;
import com.lukasw.jan.element.collection.input.InputBy;
import com.lukasw.jan.element.collection.input.InputWebElement;
import com.lukasw.jan.element.collection.text.TextBy;
import com.lukasw.jan.element.collection.text.TextWebElement;
import com.lukasw.jan.element.core.bind.AbstractBy;
import org.openqa.selenium.By;

public class JanBindBy {

    private final By by;

    protected JanBindBy(final By by) {
        this.by = by;
    }

    /**
     * Find elements based on the value of the "id" attribute.
     *
     * @param id The value of the "id" attribute to search for.
     * @return A {@link JanBindBy} with first {@link By} which locates elements by the value of the "id" attribute.
     */
    public static JanBindBy id(final String id) {
        return new JanBindBy(By.id(id));
    }

    /**
     * Find elements matched XPath expression.
     *
     * @param xpathExpression The XPath to use.
     * @return A {@link JanBindBy} with first {@link By} which locates elements via XPath.
     */
    public static JanBindBy xpath(final String xpathExpression) {
        return new JanBindBy(By.xpath(xpathExpression));
    }

    /**
     * Find elements based on the value of the "name" attribute.
     *
     * @param name The value of the "name" attribute to search for.
     * @return A {@link JanBindBy} with first {@link By} which locates elements by the value of the "name" attribute.
     */
    public static JanBindBy name(final String name) {
        return new JanBindBy(By.name(name));
    }


    public static JanBindBy linkText(final String linkText) {
        return new JanBindBy(By.linkText(linkText));
    }

    /**
     * @param partialLinkText The partial text to match against
     * @return A {@link JanBindBy} with first {@link By} which locates elements that contain the given link text.
     */
    public static JanBindBy partialLinkText(final String partialLinkText) {
        return new JanBindBy(By.partialLinkText(partialLinkText));
    }

    /**
     * Find elements based on the value of the "class" attribute. If an element has multiple classes, then
     * this will match against each of them. For example, if the value is "one two onone", then the
     * class names "one" and "two" will match.
     *
     * @param className The value of the "class" attribute to search for.
     * @return A {@link JanBindBy} with first {@link By} which locates elements by the value of the "class" attribute.
     */
    public static JanBindBy className(final String className) {
        return new JanBindBy(By.className(className));
    }

    /**
     * Find elements via the driver's underlying W3C Selector engine. If the browser does not
     * implement the Selector API, a best effort is made to emulate the API. In this case, we strive
     * for at least CSS2 support, but offer no guarantees.
     *
     * @param cssSelector CSS expression.
     * @return A {@link JanBindBy} with first {@link By} which locates elements by CSS.
     */
    public static JanBindBy cssSelector(final String cssSelector) {
        return new JanBindBy(By.cssSelector(cssSelector));
    }

    /**
     * Find elements based on the tag name.
     *
     * @param tagName The element's tag name.
     * @return A {@link JanBindBy} with first {@link By} which locates elements by their tag name.
     */
    public static JanBindBy tagName(final String tagName) {
        return new JanBindBy(By.tagName(tagName));
    }

    protected final By getBy() {
        return this.by;
    }

    public AbstractBy<InputWebElement, ?> asInput() {
        return new InputBy(this.by);
    }

    public AbstractBy<ButtonWebElement, ?> asButton() {
        return new ButtonBy(this.by);
    }

    public AbstractBy<TextWebElement, ?> asText() {
        return new TextBy(this.by);
    }
}
