package com.lukasw.jan.element.core.bind;

import com.lukasw.jan._utils.TestBy;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.sameInstance;

public class AbstractByTest {

    @Test
    public void getLocators_defineMany_getWithDefinedOrder() {
        //when
        final TestBy testBy = new TestBy(By.id("id"))
                .orById("id2")
                .orByName("name")
                .orByTagName("tagName")
                .orByXpath("xpath")
                .orByClassName("className")
                .orByCssSelector("cssSelector")
                .orByLinkText("linkText")
                .orByPartialLinkText("partialLinkText");

        //then
        assertThat(testBy.getByLocators(), contains(
                By.id("id"),
                By.id("id2"),
                By.name("name"),
                By.tagName("tagName"),
                By.xpath("xpath"),
                By.className("className"),
                By.cssSelector("cssSelector"),
                By.linkText("linkText"),
                By.partialLinkText("partialLinkText")
        ));
    }

    @Test
    public void getLocators_createWithMany_getWithDefinedOrder() {
        //given
        final List<By> locators = Arrays.asList(By.id("id"), By.name("name"), By.tagName("tagName"));

        //when
        final TestBy testBy = new TestBy(locators);

        //then
        assertThat(testBy.getByLocators(), contains(
                By.id("id"),
                By.name("name"),
                By.tagName("tagName"))
        );
    }

    @Test
    public void getFinderInterceptor_whenNotDefined_getDefaultInterceptor() {
        //when
        final TestBy testBy = new TestBy(By.id("id"));

        //then
        assertThat(testBy.getFinderInterceptor(), sameInstance(FinderInterceptor.Default.INSTANCE));
    }

    @Test
    public void getFinderInterceptor_whenDefined_getDefinedInterceptor() {

        //given
        final FinderInterceptor finderInterceptor = Mockito.mock(FinderInterceptor.class);

        //when
        final TestBy testBy = new TestBy(By.id("id")).withInterceptor(finderInterceptor);

        //then
        assertThat(testBy.getFinderInterceptor(), sameInstance(finderInterceptor));
    }

}