package com.quandoo.init;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clicker class. This class clicks in jquery elements, send chars to them, select them, etc.
 *
 * @author Andrey Klimachev(andre.klim@gmail.com)
 * @since 0.0.1
 */
public class SeleniumClicker {
    /**
     * Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(SeleniumClicker.class);

    /**
     * Instance of selenium finder.
     */
    private SeleniumFinder finder;

    /**
     * Makes a selenium clicker.
     *
     * @param finder    Instance of selenium finder.
     */
    public SeleniumClicker(final SeleniumFinder finder) {
        this.finder = finder;
    }

    /**
     * Click in the web element.
     *
     * @param jquery    to the element to be clicked.
     */
    public void clickWebElement(final String jquery) {
        finder.findWebElement(jquery).click();
    }

    /**
     * Sends {@code keys} to the web element defined by {@code jquery}.
     *
     * @param jquery    element to find web element.
     * @param keys      string to write to the element.
     */
    public void sendKeysToWebElement(final String jquery, final String keys) {
        sendKeysToWebElement(jquery, keys, true);
    }

    /**
     * Sends {@code keys} to the web element defined by {@code jquery}.
     *
     * @param jquery    element to find web element.
     * @param keys      string to write to the element.
     * @param clear     true if the element should be cleaned before writing.
     */
    public void sendKeysToWebElement(final String jquery, final String keys, final boolean clear) {
        //Click the element before sending keys to it. This is a selenium bug.
        //http://stackoverflow.com/questions/20936403/sendkeys-are-not-working-in-selenium-webdriver
        clickWebElement(jquery);
        sendKeys(jquery, keys, clear);
    }

    /**
     * Sends {@code keys} to the web element defined by {@code jquery}.
     *
     * @param jquery    element to find web element.
     * @param keys      string to write to the element.
     * @param clear     true if the element should be cleaned before writing.
     */
    private void sendKeys(final String jquery, final String keys, final boolean clear) {
        WebElement element = finder.findWebElement(jquery);
        if (clear) {
            element.clear();
        }

        logger.debug("Writing {} in {}", keys, jquery);
        element.sendKeys(keys);
    }
}
