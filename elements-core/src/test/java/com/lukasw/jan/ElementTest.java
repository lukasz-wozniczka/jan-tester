package com.lukasw.jan;

import com.lukasw.jan.support.TestElement;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.testng.Assert.fail;

public class ElementTest {


    @Test
    public void newElement_webElementIsNull_throwException() {
        //given
        final WebElement webElement = null;
        final BaseContext baseContext = mock(BaseContext.class);

        try {
            //when
            new TestElement(webElement, baseContext);
            fail("Should throw exception");
        } catch (final Exception e) {
            //then
            assertThat(e, instanceOf(NullPointerException.class));
            assertThat(e.getMessage(), equalTo(format("Cannot create instance of com.lukasw.jan.support.TestElement, argument webElement is null.", TestElement.class.getName())));
        }
    }

    @Test
    public void newElement_contextIsNull_throwException() {
        //given
        final WebElement webElement = mock(WebElement.class);
        final BaseContext baseContext = null;

        try {
            //when
            new TestElement(webElement, baseContext);
            fail("Should throw exception");
        } catch (final Exception e) {
            //then
            assertThat(e, instanceOf(NullPointerException.class));
            assertThat(e.getMessage(), equalTo(format("Cannot create instance of com.lukasw.jan.support.TestElement, argument baseContext is null.", TestElement.class.getName())));
        }
    }

    @Test
    public void newElement_passWebElement_createNewInstance() {
        //given
        final WebElement webElement = mock(WebElement.class);
        final BaseContext baseContext = mock(BaseContext.class);

        //when
        final TestElement testElement = new TestElement(webElement, baseContext);

        assertThat(testElement.getDescription(), equalTo("Test element"));
        testElement.click();

        verify(webElement).click();
        verifyNoMoreInteractions(webElement);
    }

    @Test
    public void getTagName_passWebElement_returnElementTag() {
        //given
        final WebElement webElement = mock(WebElement.class);
        final BaseContext baseContext = mock(BaseContext.class);
        when(webElement.getTagName()).thenReturn("h1");
        when(webElement.getAttribute("id")).thenReturn("element_id");

        //when
        final TestElement testElement = new TestElement(webElement, baseContext);

        assertThat(testElement.getTagName(), equalTo("h1"));
    }

    @Test
    public void perform_multipleActions_shouldRunAll() {

        final WebElement webElement = mock(WebElement.class);
        final BaseContext baseContext = mock(BaseContext.class);
        when(webElement.getTagName()).thenReturn("h1");
        when(webElement.getAttribute("id")).thenReturn("element_id");

        final TestElement testElement = new TestElement(webElement, baseContext);

        testElement.perform((e) -> e.click().click());
        verify(webElement, times(2)).click();
    }
}
