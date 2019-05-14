package com.lukasw.jan.element.core.support;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public final class Arguments {

    private Arguments() {
        //no-op
    }

    /**
     * Util method to check that the argument is not null otherwise throw an exception with human-readable message.
     *
     * @param argument     to check if is null
     * @param argumentName the name of the argument to check
     * @param type         the type of class where the argument is provided
     * @param <T>          argument type to check
     * @return checked argument if not null
     * @throws NullPointerException if argument to check is null
     */
    public static <T> T argumentNotNull(@Nullable final T argument, @Nonnull final String argumentName, @Nonnull final Class<?> type) {
        return Objects.requireNonNull(argument, String.format("Exception while trying to create new instance of: '%s', argument: '%s' is null.", type.getName(), argumentName));
    }
}
