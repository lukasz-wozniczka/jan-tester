package com.lukasw.jan.element.collection.button;

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

public class ButtonByTest {

    @Test
    public void createNew_passListOfLocators_canGet() {
        //given
        final List<By> bys = Arrays.asList(By.id("id"), By.name("name"));

        //when
        final ButtonBy sut = new ButtonBy(bys);

        //then
        assertThat(sut.getByLocators(), hasSize(2));
    }

    @Test
    public void factoryMethods_differentTypes_shouldUseRelatedBy() {
        //given
        final ButtonBy byId = ButtonBy.id("id");
        final ButtonBy byName = ButtonBy.name("name");
        final ButtonBy byXpath = ButtonBy.xpath("xpath");
        final ButtonBy byLinkText = ButtonBy.linkText("linkText");
        final ButtonBy byPartialLinkText = ButtonBy.partialLinkText("partialLinkText");
        final ButtonBy byTagName = ButtonBy.tagName("tagName");
        final ButtonBy byClassName = ButtonBy.className("className");
        final ButtonBy byCssSelector = ButtonBy.cssSelector("cssSelector");


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
        final ButtonBy sut = new ButtonBy(bys, finderInterceptor);

        //then
        assertThat(sut.getByLocators(), hasSize(1));
        assertThat(sut.getFinderInterceptor(), sameInstance(finderInterceptor));
    }

    @Test
    public void getFinderInterceptor_whenCall_returnDefaultInterceptor() {
        //when
        final ButtonBy sut = ButtonBy.id("id");

        //then
        assertThat(sut.getFinderInterceptor(), sameInstance(FinderInterceptor.Default.INSTANCE));
    }


    @Test
    public void id_createBy_firstLocatorId() {
        //when
        final ButtonBy sut = ButtonBy.id("id");

        //then
        assertThat(sut.getByLocators(), hasSize(1));
        assertThat(sut.getByLocators().get(0), equalTo(By.id("id")));
    }

    @Test
    public void xpath_createBy_firstLocatorByXpath() {
        //when
        final ButtonBy sut = ButtonBy.xpath("xpath");

        //then
        assertThat(sut.getByLocators(), hasSize(1));
        assertThat(sut.getByLocators().get(0), equalTo(By.xpath("xpath")));

    }

    @Test
    public void name_createBy_firstLocatorByName() {
        //when
        final ButtonBy sut = ButtonBy.name("name");

        //then
        assertThat(sut.getByLocators(), hasSize(1));
        assertThat(sut.getByLocators().get(0), equalTo(By.name("name")));
    }

    @Test
    public void subReference_whenCall_getInputByReference() {
        //when
        final ButtonBy sut = ButtonBy.name("name");

        //then
        assertThat(sut.subReference(), sameInstance(sut));
    }

    @Test
    public void getElementType_whenCall_getInputWebElement() {
        //given
        final ButtonBy sut = ButtonBy.name("name");

        //then
        assertThat(sut.getElementType(), equalTo(ButtonWebElement.class));
    }

    @Test
    public void bind_whenCall_createInputWebElement() {
        //given
        final ButtonBy sut = ButtonBy.name("name");
        final WebElement webElement = Mockito.mock(WebElement.class);
        final WebDriver webDriver = Mockito.mock(WebDriver.class);
        final PageContext pageContext = PageContext.builder(new JanSeleniumContext(webDriver))
                .name("page")
                .description("page description")
                .build();

        //when
        final ButtonWebElement bind = sut.bind(webElement, pageContext);

        //then
        assertThat(bind, instanceOf(ButtonWebElement.class));
        assertThat(bind.unwrap(), sameInstance(webElement));
    }
}