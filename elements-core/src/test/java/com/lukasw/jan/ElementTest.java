package com.lukasw.jan;

import com.lukasw.jan.support.TestElement;
import org.mockito.Mockito;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.sameInstance;
import static org.testng.Assert.fail;

public class ElementTest {


    @Test
    public void newElement_elementContextIsNull_throwException() {
        //given
        final ElementContext<TestElement> elementContext = null;

        try {
            //when
            new TestElement(elementContext);
            fail("Should throw exception");
        } catch (final Exception e) {
            //then
            assertThat(e, instanceOf(NullPointerException.class));
            assertThat(e.getMessage(), equalTo(format("Exception while trying to create an instance of the %s, elementContext argument cannot be null.", TestElement.class.getSimpleName())));
        }
    }

    @Test
    public void newElement_passElementContext_createNewInstance() {
        //given
        final WebElement webElement = Mockito.mock(WebElement.class);
        final BaseContext baseContext = Mockito.mock(BaseContext.class);
        final ElementContext<TestElement> elementContext = new DefaultElementContext<>(webElement, baseContext);

        //when
        final TestElement testElement = new TestElement(elementContext);

        assertThat(testElement.webElement(), sameInstance(webElement));
    }
}
