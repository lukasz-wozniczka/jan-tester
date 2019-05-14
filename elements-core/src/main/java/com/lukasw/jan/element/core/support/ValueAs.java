package com.lukasw.jan.element.core.support;

import com.google.common.base.MoreObjects;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

/**
 * A container for a value of type {@link String} which provides sets of methods to do a conversion and return custom type.
 */
@Immutable
public final class ValueAs implements Wrapped<String> {

    private final String value;

    private ValueAs(final String value) {
        Arguments.argumentNotNull(value, "value", getClass());
        this.value = value;
    }

    /**
     * Returns an {@code ValueAs} with the specified present non-null string value.
     *
     * @param value the value to be wrapped, which must be non-null
     * @return an {@code ValueAs} with the value present
     * @throws NullPointerException if value is null
     */
    public static ValueAs of(final String value) {
        return new ValueAs(value);
    }

    @Override
    public String unwrap() {
        return this.value;
    }

    public int integerType() {
        return Integer.valueOf(this.value);
    }

    public Optional<Integer> integerSafe() {
        return typeSafe(Integer::valueOf);
    }

    public long longType() {
        return Long.valueOf(this.value);
    }

    public Optional<Long> longSafe() {
        return typeSafe(Long::valueOf);
    }

    public double doubleType() {
        return Double.valueOf(this.value);
    }

    public Optional<Double> doubleSafe() {
        return typeSafe(Double::valueOf);
    }

    public float floatType() {
        return Float.valueOf(this.value);
    }

    public Optional<Float> floatSafe() {
        return typeSafe(Float::valueOf);
    }

    /**
     * Return a {@link LocalDate} value.
     * <p>
     * The string value must represent a valid date and is parsed using
     * {@link java.time.format.DateTimeFormatter#ISO_LOCAL_DATE}.
     * <p>
     * Check {@link LocalDate#parse(CharSequence)} for more details.
     *
     * @return LocalDate
     * @throws java.time.format.DateTimeParseException if value cannot be parsed
     */
    public LocalDate localDate() {
        return LocalDate.parse(this.value);
    }

    public LocalDate localDate(final DateTimeFormatter dateTimeFormatter) {
        return LocalDate.parse(this.value, dateTimeFormatter);
    }

    public LocalDate localDate(final String dateTimeFormatter) {
        return LocalDate.parse(this.value, DateTimeFormatter.ofPattern(dateTimeFormatter));
    }

    public LocalDateTime localDateTime() {
        return LocalDateTime.parse(this.value);
    }

    public LocalDateTime localDateTime(final DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime.parse(this.value, dateTimeFormatter);
    }

    public LocalDateTime localDateTime(final String dateTimeFormatter) {
        return LocalDateTime.parse(this.value, DateTimeFormatter.ofPattern(dateTimeFormatter));
    }

    public Year year(final DateTimeFormatter dateTimeFormatter) {
        return Year.parse(this.value, dateTimeFormatter);
    }

    public Year year() {
        return Year.parse(this.value);
    }

    public <R> R type(final StringConverter<R> converter) {
        return converter.convert(this.value);
    }

    /**
     * Use a converter to convert string value, if any exception will be thrown then
     * return alternative value.
     *
     * @param converter to convert
     * @param orValue   to return if convert fail
     * @param <R>       type to return
     * @return converted value if convert not fail otherwise alternative orValue
     */
    public <R> R typeOr(final StringConverter<R> converter, final R orValue) {
        return safeGet(converter, orValue);
    }

    /**
     * Custom converter to a specific object of type {@code R}.
     *
     * @param converter to convert string value
     * @param <R>       specific type of converted value
     * @return Optional with a converter value if string value has been converted successfully otherwise return {@link Optional#empty()}if an exception has been thrown or converted value is null
     */
    public <R> Optional<R> typeSafe(final StringConverter<R> converter) {
        try {
            return Optional.ofNullable(converter.convert(this.value));
        } catch (final Exception e) {
            return Optional.empty();
        }
    }

    private <R> R safeGet(final StringConverter<R> converter, final R orValue) {
        try {
            return converter.convert(this.value);
        } catch (final Exception e) {
            return orValue;
        }
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", this.value)
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ValueAs)) return false;
        final ValueAs valueAs = (ValueAs) o;
        return Objects.equals(this.value, valueAs.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }


}
