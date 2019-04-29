package com.lukasw.jan;

import java.util.Objects;

public final class Arguments {

    private Arguments() {
        throw new IllegalStateException("Cannot create instance of Arguments class");
    }

    public static <T> T argumentNotNull(final T argument, final String name, final Class<?> type) {
        return Objects.requireNonNull(argument, String.format("Cannot create instance of %s, argument %s is null.", type.getName(), name));
    }
}
