package com.lukasw.jan.element.core.context;

import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.withSettings;

public class JanSeleniumContextTest {

    private WebDriver webDriver;

    private SeleniumContext seleniumContext;

    @BeforeClass
    public void beforeClass() {
        this.webDriver = Mockito.mock(WebDriver.class, withSettings().extraInterfaces(JavascriptExecutor.class));
        this.seleniumContext = new JanSeleniumContext(this.webDriver);
    }

    @AfterMethod
    public void afterMethod() {
        Mockito.reset(this.webDriver);
    }


    @Test
    public void actions_callMethod_returnSeleniumActions() {
        //when
        final Actions actions = this.seleniumContext.actions();


        //then
        assertThat(actions, notNullValue());
    }

    @Test
    public void get_withUrl_passToWebDriver() {
        //when
        this.seleniumContext.get("url");

        //then
        verify(this.webDriver).get("url");
        verifyNoMoreInteractions(this.webDriver);
    }

    @Test
    public void getCurrentUrl_whenCall_passToWebDriver() {
        //when
        this.seleniumContext.getCurrentUrl();

        //then
        verify(this.webDriver).getCurrentUrl();
        verifyNoMoreInteractions(this.webDriver);
    }

    @Test
    public void getTitle_whenCall_passToWebDriver() {
        //when
        this.seleniumContext.getTitle();

        //then
        verify(this.webDriver).getTitle();
        verifyNoMoreInteractions(this.webDriver);
    }

    @Test
    public void findElements_whenCall_passToWebDriver() {
        //when
        this.seleniumContext.findElements(By.id("12"));

        //then
        verify(this.webDriver).findElements(By.id("12"));
        verifyNoMoreInteractions(this.webDriver);
    }

    @Test
    public void findElement_whenCall_passToWebDriver() {
        //when
        this.seleniumContext.findElement(By.id("12"));

        //then
        verify(this.webDriver).findElement(By.id("12"));
        verifyNoMoreInteractions(this.webDriver);
    }

    @Test
    public void getPageSource_whenCall_passToWebDriver() {
        //when
        this.seleniumContext.getPageSource();

        //then
        verify(this.webDriver).getPageSource();
        verifyNoMoreInteractions(this.webDriver);
    }

    @Test
    public void close_whenCall_passToWebDriver() {
        //when
        this.seleniumContext.close();

        //then
        verify(this.webDriver).close();
        verifyNoMoreInteractions(this.webDriver);
    }

    @Test
    public void quit_whenCall_passToWebDriver() {
        //when
        this.seleniumContext.quit();

        //then
        verify(this.webDriver).quit();
        verifyNoMoreInteractions(this.webDriver);
    }

    @Test
    public void getWindowHandles_whenCall_passToWebDriver() {
        //when
        this.seleniumContext.getWindowHandles();

        //then
        verify(this.webDriver).getWindowHandles();
        verifyNoMoreInteractions(this.webDriver);
    }

    @Test
    public void getWindowHandle_whenCall_passToWebDriver() {
        //when
        this.seleniumContext.getWindowHandle();

        //then
        verify(this.webDriver).getWindowHandle();
        verifyNoMoreInteractions(this.webDriver);
    }

    @Test
    public void switchTo_whenCall_passToWebDriver() {
        //when
        this.seleniumContext.switchTo();

        //then
        verify(this.webDriver).switchTo();
        verifyNoMoreInteractions(this.webDriver);
    }

    @Test
    public void navigate_whenCall_passToWebDriver() {
        //when
        this.seleniumContext.navigate();

        //then
        verify(this.webDriver).navigate();
        verifyNoMoreInteractions(this.webDriver);
    }

    @Test
    public void manage_whenCall_passToWebDriver() {
        //when
        this.seleniumContext.manage();

        //then
        verify(this.webDriver).manage();
        verifyNoMoreInteractions(this.webDriver);
    }

    @Test
    public void executeScript_whenCall_passToWebDriver() {
        //when
        this.seleniumContext.executeScript("script");

        //then
        verify((JavascriptExecutor) this.webDriver).executeScript("script");
        verifyNoMoreInteractions(this.webDriver);
    }

    @Test
    public void executeAsyncScript_whenCall_passToWebDriver() {
        //when
        this.seleniumContext.executeAsyncScript("script");

        //then
        verify((JavascriptExecutor) this.webDriver).executeAsyncScript("script");
        verifyNoMoreInteractions(this.webDriver);
    }

    @Test
    public void executeScript_noJsDriver_throwException() {
        //given
        final WebDriver noJsDriver = Mockito.mock(WebDriver.class);
        final SeleniumContext noJsContext = new JanSeleniumContext(noJsDriver);

        //when
        try {
            noJsContext.executeScript("script");
            Assert.fail("Should throw unsupported operation exception");
        } catch (final Exception e) {
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }
    }

    @Test
    public void executeAsyncScript_noJsDriver_throwException() {
        //given
        final WebDriver noJsDriver = Mockito.mock(WebDriver.class);
        final SeleniumContext noJsContext = new JanSeleniumContext(noJsDriver);

        //when
        try {
            noJsContext.executeAsyncScript("script");
            Assert.fail("Should throw unsupported operation exception");
        } catch (final Exception e) {
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }
    }
}