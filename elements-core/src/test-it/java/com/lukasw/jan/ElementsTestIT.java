package com.lukasw.jan;

import com.lukasw.jan.support.BaseMockServerIT;
import com.lukasw.jan.support.ResourcesHtmlResponses;
import com.lukasw.jan.support.TestBy;
import com.lukasw.jan.support.TestElement;
import org.mockserver.model.HttpRequest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.lukasw.jan.support.ResourcesHtmlResponses.HtmlFile.TEST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ElementsTestIT extends BaseMockServerIT {

    private static final String URL = "http://localhost:1080/test";

    private WebDriver drv;
    private BaseContext testContext;
    private ElementFactory elementFactory;

    @BeforeClass
    public void before() {

        final ChromeOptions options = new ChromeOptions();
        options.addArguments("no-sandbox", "headless", "whitelisted-ips", "disable-extensions", "disable-dev-shm-usage");


        this.drv = new ChromeDriver(options);

        this.testContext = new BaseContext() {
            @Override
            public WebDriver webDriver() {
                return ElementsTestIT.this.drv;
            }
        };

        this.elementFactory = new SimpleElementFactory(this.testContext);

        mockServerClient()
                .clear(HttpRequest.request().withPath("/test"))
                .when(HttpRequest.request().withPath("/test")).
                respond(org.mockserver.model.HttpResponse.response()
                        .withBody(ResourcesHtmlResponses.getHtmlFromResource(TEST)));
    }

    @Test
    public void testFindElementById() {
        //given
        this.drv.get(URL);
        final TestBy by = TestBy.id("h1");

        //when
        final TestElement element = this.elementFactory.findBy(by);

        //then
        assertThat(element, notNullValue());
        assertThat(element.getTagName(), equalTo("h1"));
    }

    @Test
    public void testPerformActionsOnElement() {
        //given
        this.drv.get(URL);
        final TestBy button = TestBy.id("button_id");
        final TestBy buttonClick = TestBy.id("button_click_id");

        //when
        final TestElement element = this.elementFactory.findBy(button);
        final TestElement input = this.elementFactory.findBy(buttonClick);

        element.perform((actions -> actions.click().click()));

        //then
        assertThat(input.getValue(), equalTo("2"));

        //when
        element.click();

        assertThat(input.getValue(), equalTo("3"));
    }


    @AfterClass
    public void after() {
        this.drv.quit();
    }
}
