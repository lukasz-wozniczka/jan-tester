package com.lukasw.jan.element.core.support;

import javax.annotation.Nullable;

@FunctionalInterface
interface ValueConverter<T, R> {

    @Nullable
    R convert(T from);
}
