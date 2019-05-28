package com.lukasw.jan.element.collection.text;

import com.lukasw.jan.element.core.bind.FinderInterceptor;
import com.lukasw.jan.element.core.context.JanSeleniumContext;
import com.lukasw.jan.element.core.context.PageContext;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.sameInstance;

public class TextByTest {

    @Test
    public void createNew_passListOfLocators_canGet() {
        //given
        final List<By> bys = Arrays.asList(By.id("id"), By.name("name"));

        //when
        final TextBy sut = new TextBy(bys);

        //then
        assertThat(sut.getByLocators(), hasSize(2));
    }

    @Test
    public void factoryMethods_differentTypes_shouldUseRelatedBy() {
        //given
        final TextBy byId = TextBy.id("id");
        final TextBy byName = TextBy.name("name");
        final TextBy byXpath = TextBy.xpath("xpath");
        final TextBy byLinkText = TextBy.linkText("linkText");
        final TextBy byPartialLinkText = TextBy.partialLinkText("partialLinkText");
        final TextBy byTagName = TextBy.tagName("tagName");
        final TextBy byClassName = TextBy.className("className");
        final TextBy byCssSelector = TextBy.cssSelector("cssSelector");


        //then
        assertThat(byId.getByLocators().get(0), equalTo(By.id("id")));
        assertThat(byName.getByLocators().get(0), equalTo(By.name("name")));
        assertThat(byXpath.getByLocators().get(0), equalTo(By.xpath("xpath")));
        assertThat(byLinkText.getByLocators().get(0), equalTo(By.linkText("linkText")));
        assertThat(byPartialLinkText.getByLocators().get(0), equalTo(By.partialLinkText("partialLinkText")));
        assertThat(byTagName.getByLocators().get(0), equalTo(By.tagName("tagName")));
        assertThat(byClassName.getByLocators().get(0), equalTo(By.className("className")));
        assertThat(byCssSelector.getByLocators().get(0), equalTo(By.cssSelector("cssSelector")));
    }

    @Test
    public void createNew_passLocatorsWithFinder_canGet() {
        //given
        final List<By> bys = Collections.singletonList(By.id("id"));
        final FinderInterceptor finderInterceptor = Mockito.mock(FinderInterceptor.class);

        //when
        final TextBy sut = new TextBy(bys, finderInterceptor);

        //then
        assertThat(sut.getByLocators(), hasSize(1));
        assertThat(sut.getFinderInterceptor(), sameInstance(finderInterceptor));
    }

    @Test
    public void getFinderInterceptor_whenCall_returnDefaultInterceptor() {
        //when
        final TextBy sut = TextBy.id("id");

        //then
        assertThat(sut.getFinderInterceptor(), sameInstance(FinderInterceptor.Default.INSTANCE));
    }

    @Test
    public void id_createBy_firstLocatorId() {
        //when
        final TextBy sut = TextBy.id("id");

        //then
        assertThat(sut.getByLocators(), hasSize(1));
        assertThat(sut.getByLocators().get(0), equalTo(By.id("id")));
    }

    @Test
    public void xpath_createBy_firstLocatorByXpath() {
        //when
        final TextBy sut = TextBy.xpath("xpath");

        //then
        assertThat(sut.getByLocators(), hasSize(1));
        assertThat(sut.getByLocators().get(0), equalTo(By.xpath("xpath")));

    }

    @Test
    public void name_createBy_firstLocatorByName() {
        //when
        final TextBy sut = TextBy.name("name");

        //then
        assertThat(sut.getByLocators(), hasSize(1));
        assertThat(sut.getByLocators().get(0), equalTo(By.name("name")));
    }

    @Test
    public void subReference_whenCall_getInputByReference() {
        //when
        final TextBy sut = TextBy.name("name");

        //then
        assertThat(sut.subReference(), sameInstance(sut));
    }

    @Test
    public void getElementType_whenCall_getInputWebElement() {
        //given
        final TextBy sut = TextBy.name("name");

        //then
        assertThat(sut.getElementType(), equalTo(TextWebElement.class));
    }

    @Test
    public void bind_whenCall_createInputWebElement() {
        //given
        final TextBy sut = TextBy.name("name");
        final WebElement webElement = Mockito.mock(WebElement.class);
        final WebDriver webDriver = Mockito.mock(WebDriver.class);
        final PageContext pageContext = PageContext.builder(new JanSeleniumContext(webDriver))
                .name("page")
                .description("page description")
                .build();

        //when
        final TextWebElement bind = sut.bind(webElement, pageContext);

        //then
        assertThat(bind, instanceOf(TextWebElement.class));
        assertThat(bind.unwrap(), sameInstance(webElement));
    }
}