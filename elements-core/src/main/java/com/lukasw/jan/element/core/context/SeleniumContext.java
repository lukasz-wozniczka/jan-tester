package com.lukasw.jan.element.core.context;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public interface SeleniumContext extends WebDriver, JavascriptExecutor {

    Actions actions();
}
