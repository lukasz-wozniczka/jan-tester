package com.lukasw.jan.element.core;

import com.lukasw.jan._utils.SimpleTestWebElement;
import com.lukasw.jan._utils.TestWebElement;
import com.lukasw.jan.element.core.context.PageContext;
import com.lukasw.jan.element.core.context.SeleniumContext;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class JanElementTest {

    WebElement webElement;

    WebDriver webDriver;

    PageContext pageContext;

    TestWebElement sut;

    Actions actions;

    @BeforeClass
    public void beforeClass() {
        this.webDriver = Mockito.mock(WebDriver.class);
        this.webElement = Mockito.mock(WebElement.class);
        this.actions = Mockito.spy(new Actions(this.webDriver));

        final SeleniumContext seleniumContext = Mockito.mock(SeleniumContext.class);
        when(seleniumContext.actions()).thenReturn(this.actions);

        this.pageContext = PageContext.builder(seleniumContext)
                .name("Test page")
                .description("Test page description")
                .build();

        this.sut = new SimpleTestWebElement(this.webElement, this.pageContext);
    }

    @AfterMethod
    public void afterMethod() {
        Mockito.reset(this.webDriver, this.webElement, this.actions);
    }


    @Test
    public void click_whenPerform_passToWebElement() {
        //when
        final TestWebElement result = this.sut.click();

        //then
        assertThat(result, sameInstance(this.sut));
        verify(this.webElement).click();
        verifyNoMoreInteractions(this.webElement);
    }

    @Test
    public void doubleClick_whenPerform_passToWebElement() {
        //when
        final TestWebElement result = this.sut.doubleClick();
        final InOrder inOrder = Mockito.inOrder(this.actions);

        //then
        assertThat(result, sameInstance(this.sut));
        inOrder.verify(this.actions).doubleClick(this.webElement);
        inOrder.verify(this.actions).perform();
    }

    @Test
    public void clickTimes_whenPerform_passToWebElement() {
        //when
        final TestWebElement result = this.sut.clickTimes(3);
        final InOrder inOrder = Mockito.inOrder(this.actions);

        //then
        assertThat(result, sameInstance(this.sut));
        inOrder.verify(this.actions).moveToElement(this.webElement);
        inOrder.verify(this.actions, times(3)).click();
        inOrder.verify(this.actions).perform();
    }

    @Test
    public void getTagName_whenPerform_passToWebElement() {
        //given
        when(this.webElement.getTagName()).thenReturn("h2");

        //when
        final String result = this.sut.getTagName();

        //then
        assertThat(result, equalTo("h2"));
        verify(this.webElement).getTagName();
        verifyNoMoreInteractions(this.webElement);
    }

    @Test
    public void isDisplayed_whenPerform_passToWebElement() {
        //given
        when(this.webElement.isDisplayed()).thenReturn(false);

        //when
        final boolean result = this.sut.isDisplayed();

        //then
        assertThat(result, is(false));
        verify(this.webElement).isDisplayed();
        verifyNoMoreInteractions(this.webElement);
    }

    @Test
    public void unwrap_whenCall_returnWebElement() {

        //when
        final WebElement result = this.sut.unwrap();

        //then
        assertThat(result, sameInstance(this.webElement));
    }
}