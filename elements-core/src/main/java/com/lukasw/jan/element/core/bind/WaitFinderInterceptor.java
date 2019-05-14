package com.lukasw.jan.element.core.bind;

import com.google.common.collect.ImmutableList;
import com.lukasw.jan.element.core.context.PageContext;
import com.lukasw.jan.element.core.context.SeleniumContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines for how long we can wait before the element is 'present' on the page.
 */
public class WaitFinderInterceptor implements FinderInterceptor {

    private static final Duration DEFAULT_WAIT_DURATION = Duration.ofMillis(500);

    private Duration timeout = DEFAULT_WAIT_DURATION;
    private Duration interval = DEFAULT_WAIT_DURATION;
    private List<Class<? extends Throwable>> ignoredExceptions = ImmutableList.of(NoSuchElementException.class);

    public WaitFinderInterceptor() {
    }

    public WaitFinderInterceptor(final Duration timeout) {
        this.timeout = timeout;
    }

    @Override
    public WebElement intercept(final WebElementFinder toDecorate, final By by, final Class<?> elementType, final PageContext pageContext) {

        final FluentWait<SeleniumContext> wait = new FluentWait<>(pageContext.selenium())
                .withTimeout(this.timeout)
                .pollingEvery(this.interval)
                .withMessage(() -> String.format("Exception while waiting for element %s to be visible on a page %s. Timeout after %s millis.", elementType.getSimpleName(), pageContext.getName(), this.timeout.toMillis()))
                .ignoreAll(this.ignoredExceptions);

        return wait.until(webDriver -> toDecorate.findBy(by, pageContext));
    }

    /**
     * Sets how long to wait for the evaluated condition to be true. The default timeout is
     * {@link #DEFAULT_WAIT_DURATION}.
     *
     * @param timeout The timeout duration.
     * @return A self reference.
     */
    public WaitFinderInterceptor withTimeout(final Duration timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * Sets how often the condition should be evaluated.
     *
     * <p>
     * In reality, the interval may be greater as the cost of actually evaluating a condition function
     * is not factored in. The default polling interval is {@link #DEFAULT_WAIT_DURATION}.
     *
     * @param interval The timeout duration.
     * @return A self reference.
     */
    public WaitFinderInterceptor pollingEvery(final Duration interval) {
        this.interval = interval;
        return this;
    }

    /**
     * Configures this instance to ignore specific types of exceptions while waiting for a condition.
     * Any exceptions not whitelisted will be allowed to propagate, terminating the wait.
     *
     * @param type  The type of exception to ignore.
     * @param types The types of exceptions to ignore.
     * @param <T>   an Exception that extends Throwable
     * @return A self reference.
     */
    public <T extends Throwable> WaitFinderInterceptor setIgnore(final Class<? extends T> type, final Class<? extends T> types) {
        this.ignoredExceptions = ImmutableList.<Class<? extends Throwable>>builder().add(type).add(types).build();
        return this;
    }
}
