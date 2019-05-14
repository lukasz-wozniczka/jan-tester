package com.lukasw.jan.element.core.support;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;

public class ValueAsTest {

    @Test
    public void of_newValue_wrapString() {
        //given
        final String value = "test";

        //when
        final ValueAs result = ValueAs.of(value);

        //then
        assertThat(result.unwrap(), sameInstance(value));
    }

    @Test
    public void of_passNull_throwException() {
        //given
        final String value = null;

        try {
            //when
            ValueAs.of(value);
            Assert.fail("Should throw null pointer exception");
        } catch (final Exception e) {
            //then
            assertThat(e, instanceOf(NullPointerException.class));
            assertThat(e.getMessage(), equalTo("Exception while trying to create new instance of: 'com.lukasw.jan.element.core.support.ValueAs', argument: 'value' is null."));
        }
    }

    @DataProvider(name = "valid_integers")
    public Object[][] provideValidIntegers() {
        return new Object[][]{
                {"0", 0},
                {"-1", -1},
                {"56", 56},
                {String.valueOf(Integer.MIN_VALUE), Integer.MIN_VALUE},
                {String.valueOf(Integer.MAX_VALUE), Integer.MAX_VALUE}
        };
    }

    @Test(dataProvider = "valid_integers")
    public void integerTypeAndSafe_checkValid_shouldCreate(final String value, final int expected) {
        //when
        final ValueAs result = ValueAs.of(value);

        //then
        assertThat(result.integerType(), equalTo(expected));
        assertThat(result.integerSafe().get(), equalTo(expected));
    }

    @DataProvider(name = "invalid_integers")
    public Object[][] provideInvalidIntegers() {
        return new Object[][]{
                {" 0"},
                {"zero"},
                {"56.0"},
                {String.valueOf(Long.MAX_VALUE)},
                {" "}
        };
    }

    @Test(dataProvider = "invalid_integers")
    public void integerType_checkInValid_throwException(final String value) {
        try {
            //when
            ValueAs.of(value).integerType();
            Assert.fail("Should throw number format exception");
        } catch (final Exception e) {
            //then
            assertThat(e, instanceOf(NumberFormatException.class));
        }
    }


    @Test(dataProvider = "invalid_integers")
    public void integerSafe_checkInValid_returnEmptyOptional(final String value) {
        //when
        final Optional<Integer> result = ValueAs.of(value).integerSafe();

        //then
        assertThat(result, equalTo(Optional.empty()));
    }

    @DataProvider(name = "valid_long")
    public Object[][] provideValidLong() {
        return new Object[][]{
                {"0", 0L},
                {"-12", -12L},
                {"56", 56L},
                {String.valueOf(Long.MIN_VALUE), Long.MIN_VALUE},
                {String.valueOf(Long.MAX_VALUE), Long.MAX_VALUE}

        };
    }

    @Test(dataProvider = "valid_long")
    public void longTypeAndSafe_checkValid_shouldCreate(final String value, final Long expected) {
        //when
        final ValueAs result = ValueAs.of(value);

        //then
        assertThat(result.longType(), equalTo(expected));
        assertThat(result.longSafe().get(), equalTo(expected));
    }

    @DataProvider(name = "invalid_long")
    public Object[][] provideInvalidLong() {
        return new Object[][]{
                {" 0"},
                {"one"},
                {"56.0"},
                {String.valueOf(Double.MAX_VALUE)},
                {" "}};
    }

    @Test(dataProvider = "invalid_long")
    public void longType_checkInValid_throwException(final String value) {
        try {
            //when
            ValueAs.of(value).longType();
            Assert.fail("Should throw number format exception");
        } catch (final Exception e) {
            //then
            assertThat(e, instanceOf(NumberFormatException.class));
        }
    }

    @Test(dataProvider = "invalid_long")
    public void longSafe_checkInValid_returnEmptyOptional(final String value) {
        //when
        final Optional<Long> result = ValueAs.of(value).longSafe();

        //then
        assertThat(result, equalTo(Optional.empty()));
    }

    @DataProvider(name = "valid_double")
    public Object[][] provideValidDouble() {
        return new Object[][]{
                {"0", 0.0D},
                {"-13.0002", -13.0002D},
                {"-12", -12D},
                {" 56", 56D},
                {"89.32", 89.32D},
                {"57 ", 57D},
                {"6   ", 6D},
                {"NaN", Double.NaN},
                {"Infinity", Double.POSITIVE_INFINITY},
                {String.valueOf(Double.MAX_VALUE), Double.MAX_VALUE},
                {String.valueOf(Double.MIN_VALUE), Double.MIN_VALUE}

        };
    }

    @Test(dataProvider = "valid_double")
    public void doubleTypeAndSafe_checkValid_shouldCreate(final String value, final Double expected) {
        //when
        final ValueAs result = ValueAs.of(value);

        //then
        assertThat(result.doubleType(), equalTo(expected));
        assertThat(result.doubleSafe().get(), equalTo(expected));
    }

    @DataProvider(name = "invalid_double")
    public Object[][] provideInvalidDouble() {
        return new Object[][]{
                {". 0"},
                {"one"},
                {"56;"},
                {" "}
        };
    }

    @Test(dataProvider = "invalid_double")
    public void doubleType_checkInValid_throwException(final String value) {
        try {
            //when
            ValueAs.of(value).doubleType();
            Assert.fail("Should throw number format exception");
        } catch (final Exception e) {
            //then
            assertThat(e, instanceOf(NumberFormatException.class));
        }
    }

    @Test(dataProvider = "invalid_double")
    public void doubleSafe_checkInValid_returnEmptyOptional(final String value) {
        //when
        final Optional<Double> result = ValueAs.of(value).doubleSafe();

        //then
        assertThat(result, equalTo(Optional.empty()));
    }

    @DataProvider(name = "valid_float")
    public Object[][] provideValidFloat() {
        return new Object[][]{
                {"0", 0.0F},
                {" 0", 0.0F},
                {"12  ", 12.0F},
                {"-13.0002", -13.0002F},
                {"-12", -12F},
                {"56", 56F},
                {"89.32", 89.32F}
        };
    }

    @Test(dataProvider = "valid_float")
    public void floatTypeAndSafe_checkValid_shouldCreate(final String value, final Object expected) {
        //when
        final ValueAs result = ValueAs.of(value);

        //then
        assertThat(result.floatType(), equalTo(expected));
        assertThat(result.floatSafe().get(), equalTo(expected));
    }

    @DataProvider(name = "invalid_float")
    public Object[][] provideInvalidFloat() {
        return new Object[][]{
                {"one"},
                {" "}};
    }

    @Test(dataProvider = "invalid_float")
    public void floatType_checkInValid_throwException(final String value) {
        try {
            //when
            ValueAs.of(value).floatType();
            Assert.fail("Should throw number format exception");
        } catch (final Exception e) {
            //then
            assertThat(e, instanceOf(NumberFormatException.class));
        }
    }

    @Test(dataProvider = "invalid_float")
    public void floatSafe_checkInValid_returnEmptyOptional(final String value) {
        //when
        final Optional<Float> result = ValueAs.of(value).floatSafe();

        //then
        assertThat(result, equalTo(Optional.empty()));
    }

    @Test
    public void localDate_differentVariant_parseToLocalDate() {
        //when
        final LocalDate isoDate = ValueAs.of("2012-12-20").localDate();
        final LocalDate stringFormatter = ValueAs.of("12/09/2013").localDate("dd/MM/yyyy");
        final LocalDate dateFormatter = ValueAs.of("2017.1.2").localDate(DateTimeFormatter.ofPattern("yyyy.M.d"));

        //then
        assertThat(isoDate, equalTo(LocalDate.of(2012, 12, 20)));
        assertThat(stringFormatter, equalTo(LocalDate.of(2013, 9, 12)));
        assertThat(dateFormatter, equalTo(LocalDate.of(2017, 1, 2)));
    }


    @Test
    public void localDateTime_differentVariant_parseToLocalDateTime() {
        //when
        final LocalDateTime isoDate = ValueAs.of("2012-12-20T20:21").localDateTime();
        final LocalDateTime stringFormatter = ValueAs.of("12/09/2013 22:12").localDateTime("dd/MM/yyyy HH:mm");
        final LocalDateTime dateFormatter = ValueAs.of("2017.1.2 4:8").localDateTime(DateTimeFormatter.ofPattern("yyyy.M.d H:m"));

        //then
        assertThat(isoDate, equalTo(LocalDateTime.of(2012, 12, 20, 20, 21)));
        assertThat(stringFormatter, equalTo(LocalDateTime.of(2013, 9, 12, 22, 12)));
        assertThat(dateFormatter, equalTo(LocalDateTime.of(2017, 1, 2, 4, 8)));
    }

    @Test
    public void year_differentVariant_parseToYear() {
        //when
        final Year year = ValueAs.of("2022").year();
        final Year yearParse = ValueAs.of("97").year(DateTimeFormatter.ofPattern("yy"));

        //then
        assertThat(year, equalTo(Year.of(2022)));
        assertThat(yearParse, equalTo(Year.of(2097)));
    }

    @Test
    public void type_customConverter_convert() {
        //when
        final String result = ValueAs.of("test").type(value -> "#" + value);

        //then
        assertThat(result, equalTo("#test"));
    }

    @Test
    public void typeOr_convertSuccess_returnConvertedValue() {
        //when
        final String result = ValueAs.of("test").typeOr(value -> "#" + value, "alt");

        //then
        assertThat(result, equalTo("#test"));
    }

    @Test
    public void typeOr_convertFail_returnAlternativeValue() {
        //when
        final String failResult = ValueAs.of("test").typeOr(value -> {
            throw new IllegalArgumentException();
        }, "alt");

        //then
        assertThat(failResult, equalTo("alt"));
    }

    @Test
    public void typeOr_convertToNull_returnNull() {
        //when
        final String result = ValueAs.of("test").typeOr(value -> null, "alt");

        //then
        assertThat(result, nullValue());
    }

    @Test
    public void typeSafe_convertToValue_getOptionalWithValue() {
        //when
        final Optional<String> result = ValueAs.of("test").typeSafe(value -> "$test");

        //then
        assertThat(result, equalTo(Optional.of("$test")));
    }

    @Test
    public void typeSafe_convertToNull_resultEmptyOptional() {
        //when
        final Optional<String> result = ValueAs.of("test").typeSafe(value -> null);

        //then
        assertThat(result, equalTo(Optional.empty()));
    }

    @Test
    public void toString_call_should() {
        ///given
        final ValueAs valueAs = ValueAs.of("super_string");

        //when
        final String result = valueAs.toString();

        //then
        assertThat(result, equalTo("ValueAs{value=super_string}"));
    }

    @Test
    public void testEqualsHashCode() {
        //given
        final ValueAs objectA = ValueAs.of("test");
        final ValueAs objectB = ValueAs.of("test");
        final ValueAs objectC = ValueAs.of("test");
        final ValueAs objectD = ValueAs.of("no_match");
        final Object objectE = new Object();


        //when
        assertThat(objectA.equals(null), is(false));
        assertThat(objectA.hashCode(), equalTo(objectB.hashCode()));
        Assert.assertTrue(objectA.equals(objectB) && objectB.equals(objectA));
        Assert.assertTrue(objectA.equals(objectC) && objectC.equals(objectB) && objectA.equals(objectB));

        Assert.assertFalse(objectA.equals(objectD));
        Assert.assertFalse(objectA.equals(objectE));
    }
}