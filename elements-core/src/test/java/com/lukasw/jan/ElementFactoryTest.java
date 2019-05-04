package com.lukasw.jan;

import com.lukasw.jan.support.TestBaseContext;
import com.lukasw.jan.support.TestBy;
import com.lukasw.jan.support.TestElement;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

public class ElementFactoryTest {

    @Mock
    WebDriver webDriver;

    @Mock
    WebElement webElement;

    private ElementFactory sut;

    @BeforeClass
    public void beforeClass() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void beforeMethod() {
        reset(this.webDriver, this.webElement);
        final BaseContext baseContext = new TestBaseContext(this.webDriver);
        this.sut = new SimpleElementFactory(baseContext);
    }

    @Test
    public void findBy_byIsProvided_getRelatedElement() {
        // given

        final By locator = By.id("id");
        when(this.webDriver.findElement(locator)).thenReturn(this.webElement);
        final TestBy by = TestBy.id("id");

        // when
        final TestElement element = this.sut.findBy(by);

        // then
        assertThat(element, notNullValue(TestElement.class));
    }

    @Test
    public void findBy_elementNotExist_throwException() {
        //given
        final By locator = By.id("id");
        when(this.webDriver.findElement(locator)).thenThrow(NoSuchElementException.class);
        final TestBy by = TestBy.id("id");

        //when
        try {
            this.sut.findBy(by);
            Assert.fail("Should throw exception");
        } catch (final Exception e) {
            assertThat(e, instanceOf(NoSuchElementException.class));
        }
    }
}
