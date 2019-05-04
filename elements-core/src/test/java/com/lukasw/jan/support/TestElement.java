package com.lukasw.jan.support;

import com.lukasw.jan.AbstractElement;
import com.lukasw.jan.BaseContext;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

public class TestElement extends AbstractElement<TestActions> implements TestActions {


    public TestElement(@Nonnull final WebElement webElement, @Nonnull final BaseContext baseContext) {
        super(webElement, baseContext);
    }

    @Override
    protected TestActions getElementActions() {
        return this;
    }

    @Override
    public String getDescription() {
        return "Test element";
    }
}
