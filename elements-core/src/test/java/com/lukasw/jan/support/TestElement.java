package com.lukasw.jan.support;

import com.google.common.annotations.VisibleForTesting;
import com.lukasw.jan.AbstractElement;
import com.lukasw.jan.ElementContext;

import javax.annotation.Nonnull;

public class TestElement extends AbstractElement<TestElement> {

    @VisibleForTesting
    public TestElement(@Nonnull final ElementContext<TestElement> elementContext) {
        super(elementContext);
    }
}
