package com.lukasw.jan.it.tests.support;

import com.lukasw.jan.element.collection.JanBindBy;
import com.lukasw.jan.element.collection.input.InputWebElement;
import com.lukasw.jan.element.core.bind.AbstractBy;
import org.openqa.selenium.By;

public class TestBindBy extends JanBindBy {

    protected TestBindBy(final By by) {
        super(by);
    }

    @Override
    public AbstractBy<InputWebElement, ?> asInput() {
        return new MyInputBy(getBy());
    }
}
