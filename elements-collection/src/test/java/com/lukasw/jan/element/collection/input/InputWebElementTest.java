package com.lukasw.jan.element.collection.input;

import com.lukasw.jan.element.collection.BaseCollectionTest;
import com.lukasw.jan.element.core.context.PageContext;
import com.lukasw.jan.element.core.support.ValueAs;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class InputWebElementTest extends BaseCollectionTest {

    private InputWebElement sut;

    @Override
    protected void doBefore(final WebElement webElement, final PageContext pageContext) {
        this.sut = new JanInputWebElement(webElement, pageContext);
    }

    @Test
    public void subReference_whenCall_returnSelfReference() {
        //when
        final InputWebElement subReference = ((JanInputWebElement) this.sut).subReference();

        //then
        assertThat(subReference, sameInstance(this.sut));
    }

    @Test
    public void getValue_valueAttributePresent_getFromWebElement() {
        //given
        when(this.webElement.getAttribute("value")).thenReturn("my_value");

        //when
        final String value = this.sut.getValue();

        //then
        assertThat(value, equalTo("my_value"));
    }

    @Test
    public void getValueAs_valueAttributePresent_getFromWebElement() {
        //given
        when(this.webElement.getAttribute("value")).thenReturn("100");

        //when
        final ValueAs valueAs = this.sut.getValueAs();

        //then
        assertThat(valueAs, equalTo(ValueAs.of("100")));
    }

    @Test
    public void setText_textProvided_clearAndSetByWebElement() {
        //given
        final String text = "text_to_set";
        final InOrder inOrder = Mockito.inOrder(this.webElement);

        //when
        final InputWebElement result = this.sut.setValue(text);

        //then
        assertThat(result, sameInstance(this.sut));
        inOrder.verify(this.webElement).clear();
        inOrder.verify(this.webElement).sendKeys("text_to_set");
    }

    @Test
    public void appendText_textProvided_noClearAndPassToWebElement() {
        //given
        final String text = "text_to_set";

        //when
        final InputWebElement result = this.sut.appendValue(text);

        //then
        assertThat(result, sameInstance(this.sut));
        verify(this.webElement, never()).clear();
        verify(this.webElement).sendKeys("text_to_set");
    }

    @Test
    public void isEnabled_whenCheck_passToWebElement() {
        //given
        when(this.webElement.isEnabled()).thenReturn(true);

        //when
        final boolean enabled = this.sut.isEnabled();

        //then
        verify(this.webElement).isEnabled();
        verifyNoMoreInteractions(this.webElement);
        assertThat(enabled, is(true));
    }

    @Test
    public void clear_whenRun_clearByWebElement() {
        //when
        final InputWebElement result = this.sut.clear();

        //then
        assertThat(result, sameInstance(this.sut));
        verify(this.webElement).clear();
        verifyNoMoreInteractions(this.webElement);
    }
}