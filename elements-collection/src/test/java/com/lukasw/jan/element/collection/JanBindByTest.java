package com.lukasw.jan.element.collection;

import com.lukasw.jan.element.collection.button.ButtonWebElement;
import com.lukasw.jan.element.collection.input.InputWebElement;
import com.lukasw.jan.element.collection.text.TextWebElement;
import com.lukasw.jan.element.core.bind.Bindable;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;

public class JanBindByTest {

    @Test
    public void createBindable_useBindBy_passByToLocators() {
        //given
        final JanBindBy bindById = JanBindBy.id("id");
        final JanBindBy bindByXpath = JanBindBy.xpath("xpath");
        final JanBindBy bindByName = JanBindBy.name("name");
        final JanBindBy bindByCssSelector = JanBindBy.cssSelector("cssSelector");
        final JanBindBy bindByClassName = JanBindBy.className("className");
        final JanBindBy bindByLinkText = JanBindBy.linkText("linkText");
        final JanBindBy bindByPartialLinkText = JanBindBy.partialLinkText("partialLinkText");
        final JanBindBy bindByTagName = JanBindBy.tagName("tagName");


        //when
        final Bindable<InputWebElement> inputById = bindById.asInput();
        final Bindable<TextWebElement> textByXpath = bindByXpath.asText();
        final Bindable<ButtonWebElement> buttonByName = bindByName.asButton();
        final Bindable<ButtonWebElement> buttonByCssSelector = bindByCssSelector.asButton();
        final Bindable<TextWebElement> textByClassName = bindByClassName.asText();
        final Bindable<InputWebElement> inputByLinkText = bindByLinkText.asInput();
        final Bindable<ButtonWebElement> buttonByPartialLinkText = bindByPartialLinkText.asButton();
        final Bindable<TextWebElement> textByTagName = bindByTagName.asText();

        //then
        assertThat(inputById.getByLocators().get(0), allOf(sameInstance(bindById.getBy()), equalTo(By.id("id"))));
        assertThat(textByXpath.getByLocators().get(0), allOf(sameInstance(bindByXpath.getBy()), equalTo(By.xpath("xpath"))));
        assertThat(buttonByName.getByLocators().get(0), allOf(sameInstance(bindByName.getBy()), equalTo(By.name("name"))));
        assertThat(buttonByCssSelector.getByLocators().get(0), allOf(sameInstance(bindByCssSelector.getBy()), equalTo(By.cssSelector("cssSelector"))));
        assertThat(textByClassName.getByLocators().get(0), allOf(sameInstance(bindByClassName.getBy()), equalTo(By.className("className"))));
        assertThat(inputByLinkText.getByLocators().get(0), allOf(sameInstance(bindByLinkText.getBy()), equalTo(By.linkText("linkText"))));
        assertThat(buttonByPartialLinkText.getByLocators().get(0), allOf(sameInstance(bindByPartialLinkText.getBy()), equalTo(By.partialLinkText("partialLinkText"))));
        assertThat(textByTagName.getByLocators().get(0), allOf(sameInstance(bindByTagName.getBy()), equalTo(By.tagName("tagName"))));
    }


}