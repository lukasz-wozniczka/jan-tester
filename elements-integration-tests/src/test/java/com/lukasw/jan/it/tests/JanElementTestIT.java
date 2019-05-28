package com.lukasw.jan.it.tests;


import com.lukasw.jan.element.collection.JanBindBy;
import com.lukasw.jan.element.collection.button.ButtonWebElement;
import com.lukasw.jan.element.collection.input.InputWebElement;
import com.lukasw.jan.element.collection.text.TextBy;
import com.lukasw.jan.element.collection.text.TextWebElement;
import com.lukasw.jan.element.core.bind.Bindable;
import com.lukasw.jan.element.core.context.ElementNotFoundException;
import com.lukasw.jan.element.core.context.JanSeleniumContext;
import com.lukasw.jan.element.core.context.PageContext;
import com.lukasw.jan.it.tests.support.BaseMockServerTestIT;
import com.lukasw.jan.it.tests.support.ResourcesHtmlResponses;
import org.mockito.Mockito;
import org.mockserver.model.HttpRequest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.lukasw.jan.it.tests.support.ResourcesHtmlResponses.HtmlFile.TEST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;

public class JanElementTestIT extends BaseMockServerTestIT {

    private static final String URL = "http://localhost:1080/test";

    private WebDriver drv;
    private PageContext pageContext;

    @BeforeClass
    public void before() {

        final ChromeOptions options = new ChromeOptions();
        options.addArguments("no-sandbox", "headless", "whitelisted-ips", "disable-extensions", "disable-dev-shm-usage");

        this.drv = new ChromeDriver(options);

        final JanSeleniumContext testContext = new JanSeleniumContext(this.drv);

        this.pageContext = PageContext.builder(testContext)
                .name("Test page")
                .description("Test integration page")
                .build();


        mockServerClient()
                .clear(HttpRequest.request().withPath("/test"))
                .when(HttpRequest.request().withPath("/test")).
                respond(org.mockserver.model.HttpResponse.response()
                        .withBody(ResourcesHtmlResponses.getHtmlFromResource(TEST)));
    }

    @Test
    public void searchTextById_elementExist_foundAndValid() {
        //given
        this.drv.get(URL);
        final Bindable<TextWebElement> by = TextBy.id("h1");

        //when
        final TextWebElement element = this.pageContext.findBy(by);

        //then
        assertThat(element, notNullValue());
        assertThat(element.getTagName(), equalTo("h1"));
        assertThat(element.getText(), equalTo("test_text"));
    }

    @Test
    public void searchElement_withInterceptor_shouldInterceptSearch() {
        //given
        this.drv.get(URL);

        final WebElement replacedWebElement = Mockito.mock(WebElement.class);

        final Bindable<TextWebElement> by = TextBy.id("h1").withInterceptor((toDecorate, by1, elementType, pageContext) -> {
            assertThat(by1, notNullValue());
            assertThat(elementType, equalTo(TextWebElement.class));
            assertThat(pageContext, sameInstance(pageContext));
            return replacedWebElement;
        });

        //when
        final TextWebElement element = this.pageContext.findBy(by);

        //then
        assertThat(element, notNullValue());
        assertThat(element.unwrap(), sameInstance(replacedWebElement));
    }

    @Test
    public void searchByManyLocators_noneMatch_shouldFail() {
        //given
        this.drv.get(URL);
        final Bindable<TextWebElement> by = TextBy.id("fake_id").orByName("fake_name").orByXpath("fake_xpath");

        //when
        try {
            final TextWebElement element = this.pageContext.findBy(by);
            Assert.fail("Should throw ElementNotFoundException");
        } catch (final Exception e) {
            //then
            assertThat(e, instanceOf(ElementNotFoundException.class));
            assertThat(e.getMessage(), equalTo("Exception while trying to find an element of type: 'TextWebElement', on a page with name: 'Test page'.\nNone of the provided by locators match: \n1) By.id: fake_id\n2) By.name: fake_name\n3) By.xpath: fake_xpath"));
        }
    }

    @Test
    public void searchByManyLocators_oneOfThemMatch_foundAndValid() {
        //given
        final Bindable<TextWebElement> by = TextBy.id("fake_id").orByName("fake_name").orById("h1");

        //when
        final TextWebElement element = this.pageContext.findBy(by);

        //then
        assertThat(element, notNullValue());
        assertThat(element.getTagName(), equalTo("h1"));
    }

    @Test
    public void click_manyAndDifferentTypeOfClick_correctSumOfClicks() {
        //given
        this.drv.get(URL);
        final Bindable<ButtonWebElement> button = JanBindBy.id("button_id").asButton();
        final Bindable<InputWebElement> buttonClick = JanBindBy.id("button_click_count").asInput();

        //when
        final ButtonWebElement element = this.pageContext.findBy(button);
        final InputWebElement input = this.pageContext.findBy(buttonClick);

        element.clickTimes(5);

        //then
        assertThat(input.getValue(), equalTo("5"));
        assertThat(input.getValueAs().integerType(), equalTo(5));
        assertThat(input.getValueAs().longType(), equalTo(5L));

        //when
        element.click();

        assertThat(input.getValue(), equalTo("6"));
        assertThat(input.getValueAs().integerType(), equalTo(6));
        assertThat(input.getValueAs().longType(), equalTo(6L));

        //when
        element.doubleClick();

        assertThat(input.getValue(), equalTo("8"));
        assertThat(input.getValueAs().integerType(), equalTo(8));
        assertThat(input.getValueAs().longType(), equalTo(8L));
    }


    @AfterClass
    public void after() {
        this.drv.quit();
    }
}
