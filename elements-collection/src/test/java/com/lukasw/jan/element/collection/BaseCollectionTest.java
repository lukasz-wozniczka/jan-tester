package com.lukasw.jan.element.collection;

import com.lukasw.jan.element.core.context.JanSeleniumContext;
import com.lukasw.jan.element.core.context.PageContext;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class BaseCollectionTest {

    public WebElement webElement;

    public WebDriver webDriver;

    public PageContext pageContext;

    @BeforeClass
    public void beforeClass() {

        this.webElement = Mockito.mock(WebElement.class);

        this.webDriver = Mockito.mock(WebDriver.class);

        final JanSeleniumContext seleniumContext = new JanSeleniumContext(this.webDriver);

        this.pageContext = PageContext.builder(seleniumContext)
                .name("Test page")
                .description("Test page description")
                .build();
        doBefore(this.webElement, this.pageContext);

    }

    protected void doBefore(final WebElement webElement, final PageContext pageContext) {

    }

    @AfterMethod
    public void afterMethod() {
        Mockito.reset(this.webElement, this.webDriver);
    }
}
