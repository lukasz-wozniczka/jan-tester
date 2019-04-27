package com.lukasw.jan;

import com.lukasw.jan.support.BaseMockServerIT;
import com.lukasw.jan.support.ResourcesHtmlResponses;
import com.lukasw.jan.support.TestBy;
import com.lukasw.jan.support.TestElement;
import org.hamcrest.Matchers;
import org.mockserver.model.HttpRequest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.lukasw.jan.support.ResourcesHtmlResponses.HtmlFile.TEST;
import static org.hamcrest.MatcherAssert.assertThat;

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

        mockServerClient().when(HttpRequest.request().withPath("/test")).
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
        assertThat(element, Matchers.notNullValue());
    }

    @AfterClass
    public void after() {
        this.drv.quit();
    }
}
