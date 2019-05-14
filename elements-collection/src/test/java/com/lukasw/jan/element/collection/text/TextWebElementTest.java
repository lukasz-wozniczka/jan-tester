package com.lukasw.jan.element.collection.text;

import com.lukasw.jan.element.collection.BaseCollectionTest;
import com.lukasw.jan.element.core.context.PageContext;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TextWebElementTest extends BaseCollectionTest {

    private TextWebElement sut;

    @Override
    protected void doBefore(final WebElement webElement, final PageContext pageContext) {
        this.sut = new JanTextWebElement(webElement, pageContext);
    }

    @Test
    public void subReference_whenCall_returnSelfReference() {
        //when
        final TextWebElement subReference = ((JanTextWebElement) this.sut).subReference();

        //then
        assertThat(subReference, sameInstance(this.sut));
    }

    @Test
    public void getText_elementHasText_passFromWebElement() {
        //given
        when(this.sut.getText()).thenReturn("test_text");

        //when
        final String text = this.sut.getText();

        //then
        verify(this.webElement).getText();
        assertThat(text, equalTo("test_text"));
    }

}