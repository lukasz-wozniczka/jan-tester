package com.lukasw.jan.element.core.context;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.annotation.concurrent.Immutable;
import java.util.List;
import java.util.Set;

/**
 * Define the selenium context created when the new test instance is started.
 * {@link JanSeleniumContext} is propagated and available by all sub-element within a test.
 * This context should not be shared between threads.
 */
@Immutable
public final class JanSeleniumContext implements SeleniumContext {

    private final JavascriptExecutor javascriptExecutor;
    private final WebDriver webDriver;
    private final Actions actions;

    public JanSeleniumContext(final WebDriver webDriver) {
        this.webDriver = webDriver;
        this.actions = new Actions(webDriver);
        this.javascriptExecutor = ensureJsExecutor(webDriver);
    }

    private JavascriptExecutor ensureJsExecutor(final WebDriver webDriver) {
        return (webDriver instanceof JavascriptExecutor) ? (JavascriptExecutor) webDriver : NoJavaScriptExecutor.INSTANCE;
    }

    @Override
    public Actions actions() {
        return this.actions;
    }

    @Override
    public void get(final String url) {
        this.webDriver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return this.webDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return this.webDriver.getTitle();
    }

    @Override
    public List<WebElement> findElements(final By by) {
        return this.webDriver.findElements(by);
    }

    @Override
    public WebElement findElement(final By by) {
        return this.webDriver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return this.webDriver.getPageSource();
    }

    @Override
    public void close() {
        this.webDriver.close();
    }

    @Override
    public void quit() {
        this.webDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return this.webDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return this.webDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return this.webDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return this.webDriver.navigate();
    }

    @Override
    public Options manage() {
        return this.webDriver.manage();
    }

    @Override
    public Object executeScript(final String script, final Object... args) {
        return this.javascriptExecutor.executeScript(script, args);
    }

    @Override
    public Object executeAsyncScript(final String script, final Object... args) {
        return this.javascriptExecutor.executeAsyncScript(script, args);
    }

    private enum NoJavaScriptExecutor implements JavascriptExecutor {
        INSTANCE;

        @Override
        public Object executeScript(final String script, final Object... args) {
            throw new UnsupportedOperationException("Exception while trying to execute javascript code by this webDriver");
        }

        @Override
        public Object executeAsyncScript(final String script, final Object... args) {
            throw new UnsupportedOperationException("Exception while trying to execute asynck javascript code by this webDriver");
        }
    }
}
