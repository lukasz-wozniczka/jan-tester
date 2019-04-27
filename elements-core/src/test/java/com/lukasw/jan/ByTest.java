package com.lukasw.jan;

import com.lukasw.jan.support.TestBy;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.sameInstance;
import static org.testng.Assert.fail;

public class ByTest {

    @Test
    public void newBy_seleniumByNotProvided_throwException() {
        //given
        final By seleniumBy = null;

        try {
            //when
            new TestBy(seleniumBy);
            fail("Should throw exception");
        } catch (final Exception e) {
            //then
            assertThat(e, instanceOf(NullPointerException.class));
            assertThat(e.getMessage(), equalTo(format("Exception while trying to create an instance of the %s, by argument cannot be null.", TestBy.class.getSimpleName())));
        }
    }

    @Test
    public void newBy_provideWrappedBy_objectIsValid() {
        //given
        final By by = By.id("id");

        //when
        final TestBy testBy = new TestBy(by);

        //then
        assertThat(testBy.getWrapped(), sameInstance(by));
        assertThat(testBy.getDescription(), equalTo(TestBy.class.getSimpleName()));
    }
}
