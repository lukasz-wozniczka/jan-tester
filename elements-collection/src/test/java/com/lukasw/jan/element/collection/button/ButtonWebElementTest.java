package com.lukasw.jan.element.collection.button;

import com.lukasw.jan.element.collection.BaseCollectionTest;
import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;

public class ButtonWebElementTest extends BaseCollectionTest {

    private ButtonWebElement sut;

    @Override
    protected void doBefore(final WebElement webElement, final PageContext pageContext) {
        this.sut = new JanButtonWebElement(webElement, pageContext);
    }

    @Test
    public void subReference_whenCall_returnSelfReference() {
        //when
        final ButtonWebElement subReference = ((JanButtonWebElement) this.sut).subReference();

        //then
        assertThat(subReference, sameInstance(this.sut));
    }
}