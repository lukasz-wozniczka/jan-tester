package com.lukasw.jan.element.core.context;

import com.lukasw.jan._utils.TestBy;
import com.lukasw.jan._utils.TestWebElement;
import com.lukasw.jan.element.core.bind.Bindable;
import com.lukasw.jan.element.core.bind.FinderInterceptor;
import com.lukasw.jan.element.core.bind.TooManyElementsException;
import com.lukasw.jan.element.core.bind.WebElementFinder;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.testng.Assert.fail;

public class PageContextTest {

    private static final Bindable<TestWebElement> TEST = TestBy.id("id");


    WebDriver webDriver;
    PageContext sut;
    SeleniumContext seleniumContext;

    @BeforeClass
    public void beforeClass() {
        this.webDriver = Mockito.mock(WebDriver.class);

        this.seleniumContext = new JanSeleniumContext(this.webDriver);

        this.sut = PageContext.builder(this.seleniumContext)
                .name("Test page")
                .description("Test page description")
                .build();
    }

    @AfterMethod
    public void afterMethod() {
        Mockito.reset(this.webDriver);
    }

    @Test
    public void createNew_passAllParams_allParamsInitialised() {
        //given
        final WebElementFinder webElementFinder = Mockito.mock(WebElementFinder.class);


        //given
        final PageContext pageContext = PageContext.builder(this.seleniumContext)
                .webElementFinder(webElementFinder)
                .name("Super Page")
                .description("Super page description")
                .build();

        //then
        assertThat(pageContext.getWebElementFinder(), sameInstance(webElementFinder));
        assertThat(pageContext.getName(), equalTo("Super Page"));
        assertThat(pageContext.getDescription(), equalTo("Super page description"));
    }

    @Test
    public void createNew_seleniumContextIsNull_throwException() {
        //given
        final SeleniumContext seleniumContext = null;

        try {
            //when
            PageContext.builder(seleniumContext).build();
            fail("Should throw null pointer exception");
        } catch (final Exception e) {
            //then
            assertThat(e, instanceOf(NullPointerException.class));
            assertThat(e.getMessage(), equalTo("Exception while trying to create new instance of: 'com.lukasw.jan.element.core.context.PageContext', argument: 'seleniumContext' is null."));
        }
    }

    @Test
    public void createNew_descriptionIsNull_throwException() {
        //given
        final String description = null;

        try {
            //when
            PageContext.builder(this.seleniumContext)
                    .name("Page")
                    .description(null)
                    .build();
            fail("Should throw null pointer exception");
        } catch (final Exception e) {
            //then
            assertThat(e, instanceOf(NullPointerException.class));
            assertThat(e.getMessage(), equalTo("Exception while trying to create new instance of: 'com.lukasw.jan.element.core.context.PageContext', argument: 'description' is null."));
        }
    }

    @Test
    public void createNew_finderIsNull_useDefaultFinder() {
        //given
        final WebElementFinder finder = null;

        //when
        final PageContext pageContext = PageContext.builder(this.seleniumContext)
                .name("Page")
                .description("Page description")
                .webElementFinder(finder)
                .build();

        //then
        assertThat(pageContext.getWebElementFinder(), sameInstance(WebElementFinder.Default.INSTANCE));
    }

    @Test
    public void createNew_nameIsNull_throwException() {
        //given
        final String name = null;

        try {
            //when
            PageContext.builder(this.seleniumContext)
                    .name(name)
                    .build();
            fail("Should throw null pointer exception");
        } catch (final Exception e) {
            //then
            assertThat(e, instanceOf(NullPointerException.class));
            assertThat(e.getMessage(), equalTo("Exception while trying to create new instance of: 'com.lukasw.jan.element.core.context.PageContext', argument: 'name' is null."));
        }
    }

    @Test
    public void selenium_whenCalled_returnSeleniumContext() {
        //when
        final SeleniumContext result = this.sut.selenium();

        //then
        assertThat(result, sameInstance(this.seleniumContext));
    }

    @Test
    public void getWebElementFinder_notProvided_returnDefaultFinder() {
        //when
        final WebElementFinder result = this.sut.getWebElementFinder();

        //then
        assertThat(result, sameInstance(WebElementFinder.Default.INSTANCE));
    }

    @Test
    public void findBy_oneWebElementExist_returnJanElement() {
        //given
        final WebElement webElement = Mockito.mock(WebElement.class);
        when(this.webDriver.findElements(By.id("id"))).thenReturn(Collections.singletonList(webElement));

        //when
        final TestWebElement result = this.sut.findBy(TEST);

        //then
        assertThat(result.unwrap(), sameInstance(webElement));
    }

    @Test
    public void findBy_manyWebElementsExists_throwException() {
        //given
        final WebElement firstElement = Mockito.mock(WebElement.class);
        final WebElement secondElement = Mockito.mock(WebElement.class);
        when(firstElement.toString()).thenReturn("first_element");
        when(secondElement.toString()).thenReturn("second_element");
        when(this.webDriver.findElements(By.id("id"))).thenReturn(Arrays.asList(firstElement, secondElement));

        try {
            //when
            final TestWebElement result = this.sut.findBy(TEST);
            fail("Should throw too many elements exception");
        } catch (final Exception e) {
            //then
            assertThat(e, instanceOf(TooManyElementsException.class));
            assertThat(e.getMessage(), equalTo("Exception while trying to find element by locator: 'By.id: id', on a page 'Test page'.\nExpected that one element is present but found: \n1) first_element\n2) second_element"));
        }
    }

    @Test
    public void findBy_noWebElement_throwException() {
        //given
        when(this.webDriver.findElements(By.id("id"))).thenReturn(Collections.emptyList());

        try {
            //when
            this.sut.findBy(TEST);
            fail("Should throw too many elements exception");
        } catch (final Exception e) {
            //then
            assertThat(e, instanceOf(ElementNotFoundException.class));
            assertThat(e.getMessage(), equalTo("Exception while trying to find an element of type: 'TestWebElement', on a page with name: 'Test page' by locator: 'By.id: id'"));
        }
    }

    @Test
    public void findBy_noWebElementByManyLocators_throwException() {
        //given
        final Bindable<TestWebElement> manyLocators = TestBy.id("id").orByName("name").orByXpath("xpath");

        when(this.webDriver.findElements(By.xpath("xpath"))).thenReturn(Collections.emptyList());
        when(this.webDriver.findElements(By.name("name"))).thenReturn(Collections.emptyList());
        when(this.webDriver.findElements(By.id("id"))).thenReturn(Collections.emptyList());
        final InOrder inOrder = Mockito.inOrder(this.webDriver);

        try {
            //when
            this.sut.findBy(manyLocators);
            fail("Should throw too many elements exception");
        } catch (final Exception e) {
            //then
            assertThat(e, instanceOf(ElementNotFoundException.class));
            assertThat(e.getMessage(), equalTo("Exception while trying to find an element of type: 'TestWebElement', on a page with name: 'Test page'.\nNone of the provided by locators match: \n1) By.id: id\n2) By.name: name\n3) By.xpath: xpath"));
            inOrder.verify(this.webDriver).findElements(By.id("id"));
            inOrder.verify(this.webDriver).findElements(By.name("name"));
            inOrder.verify(this.webDriver).findElements(By.xpath("xpath"));
        }
    }

    @Test
    public void findBy_manyLocatorsFindByOne_returnFirstThatMatch() {
        //given
        final Bindable<TestWebElement> manyLocators = TestBy.id("id").orByName("name").orByXpath("xpath");
        final WebElement namedWebElement = Mockito.mock(WebElement.class);

        when(this.webDriver.findElements(By.xpath("xpath"))).thenReturn(Collections.emptyList());
        when(this.webDriver.findElements(By.name("name"))).thenReturn(Collections.singletonList(namedWebElement));
        when(this.webDriver.findElements(By.id("id"))).thenReturn(Collections.emptyList());
        final InOrder inOrder = Mockito.inOrder(this.webDriver);

        //when
        final TestWebElement result = this.sut.findBy(manyLocators);

        //then
        assertThat(result.unwrap(), sameInstance(namedWebElement));
        inOrder.verify(this.webDriver).findElements(By.id("id"));
        inOrder.verify(this.webDriver).findElements(By.name("name"));
        inOrder.verify(this.webDriver, never()).findElements(Mockito.any());
    }

    @Test
    public void findBy_allLocatorsMatch_returnFirst() {
        //given
        final Bindable<TestWebElement> manyLocators = TestBy.id("id").orByName("name").orByXpath("xpath");
        final WebElement idWebElement = Mockito.mock(WebElement.class);
        final WebElement namedWebElement = Mockito.mock(WebElement.class);
        final WebElement xpathWebElement = Mockito.mock(WebElement.class);

        when(this.webDriver.findElements(By.id("id"))).thenReturn(Collections.singletonList(idWebElement));
        when(this.webDriver.findElements(By.name("name"))).thenReturn(Collections.singletonList(namedWebElement));
        when(this.webDriver.findElements(By.xpath("xpath"))).thenReturn(Collections.singletonList(xpathWebElement));
        final InOrder inOrder = Mockito.inOrder(this.webDriver);

        //when
        final TestWebElement result = this.sut.findBy(manyLocators);

        //then
        assertThat(result.unwrap(), sameInstance(idWebElement));
        inOrder.verify(this.webDriver).findElements(By.id("id"));
        inOrder.verify(this.webDriver, never()).findElements(Mockito.any());
    }

    @Test
    public void findBy_locatorWithInterceptor_callIntercept() {
        //given
        final WebElement interceptorWebElement = Mockito.mock(WebElement.class);
        final FinderInterceptor finderInterceptor = new FinderInterceptor() {
            @Override
            public WebElement intercept(final WebElementFinder toDecorate, final By by, final Class<?> elementType, final PageContext pageContext) {
                return interceptorWebElement;
            }
        };
        final Bindable<TestWebElement> manyLocators = TestBy.id("id").withInterceptor(finderInterceptor);

        //when
        final TestWebElement result = this.sut.findBy(manyLocators);

        //then
        assertThat(result.unwrap(), sameInstance(interceptorWebElement));
        verifyZeroInteractions(this.webDriver);
    }

    @Test
    public void findBy_interceptorThrowException_passException() {
        //given
        final FinderInterceptor finderInterceptor = new FinderInterceptor() {
            @Override
            public WebElement intercept(final WebElementFinder toDecorate, final By by, final Class<?> elementType, final PageContext pageContext) {
                throw new IllegalArgumentException("Cannot intercept");
            }
        };
        final Bindable<TestWebElement> manyLocators = TestBy.id("id").withInterceptor(finderInterceptor);

        try {
            //when
            this.sut.findBy(manyLocators);
            fail("Should throw illegal argument exception");
        } catch (final Exception e) {
            //then
            assertThat(e, instanceOf(IllegalArgumentException.class));
            assertThat(e.getMessage(), equalTo("Cannot intercept"));
            verifyZeroInteractions(this.webDriver);
        }
    }

    @Test
    public void findBy_interceptorReturnNull_throwElementNotFound() {
        //given
        final FinderInterceptor finderInterceptor = new FinderInterceptor() {
            @Override
            public WebElement intercept(final WebElementFinder toDecorate, final By by, final Class<?> elementType, final PageContext pageContext) {
                return null;
            }
        };
        final Bindable<TestWebElement> manyLocators = TestBy.id("id").withInterceptor(finderInterceptor);

        try {
            //when
            this.sut.findBy(manyLocators);
            fail("Should throw illegal argument exception");
        } catch (final Exception e) {
            //then
            assertThat(e, instanceOf(ElementNotFoundException.class));
            assertThat(e.getMessage(), equalTo("Exception while trying to find an element of type: 'TestWebElement', on a page with name: 'Test page' by locator: 'By.id: id'"));
            verifyZeroInteractions(this.webDriver);
        }
    }
}