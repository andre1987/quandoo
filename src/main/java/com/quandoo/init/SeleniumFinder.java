package com.quandoo.init;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Finder class that manipulates jquery to find a given element in the page.
 *
 * @author Andrey Klimachev(andre.klim@gmail.com)
 * @since 0.0.1
 */
public class SeleniumFinder {
    /**
     * Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(SeleniumFinder.class);

    /**
     * Selenium API Javascript executor.
     */
    private JavascriptExecutor jse;

    /**
     * Times to do the find tries.
     */
    private final int FIND_TIMEOUT = 10;

    /**
     * Milliseconds to wait for each find try.
     */
    private final int FIND_WAIT = 1000;

    /**
     * Times to check the jquery active.
     */
    private final int JQUERY_ACTIVE_TIMEOUT = 120;

    /**
     * Milliseconds to wait for each jquery verification.
     */
    private final int JQUERY_ACTIVE_WAIT = 250;

    /**
     * Number of retries for jquery.
     */
    private final int JQUERY_RETRIES_WAIT = 10;

    /**
     * Milliseconds to wait for each jquery retry.
     */
    private final int JQUERY_RETRIES_TIMEOUT = 1000;

    /**
     * Instance of webdriver.
     */
    private WebDriver webDriver;

    /**
     * Makes a selenium finder.
     *
     * @param webDriver instance of web driver.
     */
    public SeleniumFinder(final WebDriver webDriver) {
        this.webDriver = webDriver;
        jse = (JavascriptExecutor) webDriver;
    }

    /**
     * Find an web element defined by {@code jquery}.
     *
     * @param jquery    jquery to be executed.
     * @return          the found WebElement.
     */
    public WebElement findWebElement(String jquery) {
        return (WebElement) find("return " + jquery + "[0]");
    }

    /**
     * Find a string in a page defined by {@code jquery}.
     *
     * @param jquery    Jquery for detect element.
     * @return          Found string.
     */
    public String findString(final String jquery) {
        return (String) find("return " + jquery);
    }

    /**
     * Find an integer number in a page defined by {@code jquery}
     *
     * @param jquery    Jquery for detect element.
     * @return          Found int.
     */
    public int findInt(final String jquery) {
        return ((Long) find("return " + jquery)).intValue();
    }

    /**
     * This method serves to check if element is presented on the page.
     * Normally, this method is used to check that the execution of CSS effects are done.
     *
     * @param jquery    Jquery for detect element.
     */
    public void checkElementIsThere(String jquery) {
        checkElementPresence(jquery, true);
    }

    /**
     * Check if element defined by {@code jquery} is visible.
     *
     * @param jquery    Jquery for detect element.
     */
    public void checkIfVisible(String jquery) {
        boolean isVisible;
        for (int i = 0; i < FIND_TIMEOUT; i++) {
            isVisible = findString(jquery + ".is(':visible').toString()").equals("true");

            if(isVisible) {
                return;
            } else {
                logger.info("Element '{}' does not appear.", jquery);

                try {
                    Thread.sleep(FIND_WAIT);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Wait was interrupted");
                }
            }
        }

        throw new RuntimeException("Element is not visible. The condition was not accomplished.");
    }

    /**
     * Move cursor over element defined by {@code query}.
     *
     * @param jquery    Jquery for detect element.
     */
    public void moveCursorOver(String jquery) {
        checkElementIsThere(jquery);
        new Actions(webDriver).moveToElement(findWebElement(jquery)).build().perform();
    }

    /**
     * Checks if element defined by {@code jquery} is present or not.
     *
     * @param jquery          Jquery for detect element.
     * @param shouldAppear    True if an element should appear, false if not.
     */
    private void checkElementPresence(String jquery, boolean shouldAppear) {
        boolean isPresent;
        for (int i = 0; i < FIND_TIMEOUT; i++) {
            isPresent = findInt(jquery + ".length") > 0 ? true : false;
            if (isPresent == shouldAppear) {
                return;
            } else {
                if (shouldAppear) {
                    logger.info("Element '{}' appears.", jquery);
                } else {
                    logger.info("Element '{}' does not appear.", jquery);
                }


                try {
                    Thread.sleep(FIND_WAIT);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Wait was interrupted");
                }
            }
        }

        throw new RuntimeException("Element still appears or not in a page. The condition was not accomplished.");
    }

    /**
     * Tries to get an element.
     * If the element was not found, try again after waiting {@code FIND_WAIT}.
     * If the element wasn't found after {@code FIND_TIMEOUT}, return null.
     *
     * @param jquery    Jquery to be executed.
     * @return          Found element, otherwise null.
     */
    private Object find(final String jquery) {
        for (int i = 0; i < FIND_TIMEOUT; i++) {
            Object foundObject = executeScript(jquery);
            if (foundObject == null) {
                logger.info("Element '{}' wasn't found.", jquery);
                try {
                    Thread.sleep(FIND_WAIT);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Wait was interrupted");
                }
            } else {
                return foundObject;
            }
        }

        return null;
    }

    /**
     * Checks if jquery is active. Normally it is not active when there are one or several jquery requests are in background.
     *
     * @return  True if there is not requests in background, false if there are.
     */
    private boolean isJqueryActive() {
        boolean jqueryIsActive = false;

        for (int i = 0; i < JQUERY_ACTIVE_TIMEOUT; i++) {
            if (i != 0) {
                logger.info("Jquery is not active. Check again.");
            }
            try {
                jqueryIsActive = (Boolean) jse.executeScript("return $.active == 0");
                if (!jqueryIsActive) {
                    try {
                        Thread.sleep(JQUERY_ACTIVE_WAIT);
                    } catch (InterruptedException e) {
                        throw new RuntimeException("Wait was interrupted");
                    }
                } else {
                    break;
                }
            } catch (Exception e) {
                try {
                    Thread.sleep(JQUERY_ACTIVE_WAIT);
                } catch (InterruptedException e1) {
                    throw new RuntimeException("Interrupted exception has occurred.");
                }
                logger.info("Something went wrong while checking if jquery is active.");
            }
        }

        return jqueryIsActive;
    }

    /**
     * Executes {@code jquery}.
     *
     * @param jquery    Jquery to be executed.
     * @return          Found object.
     */
    private Object executeScript(final String jquery) {
        boolean isJqueryActive = isJqueryActive();
        if (!isJqueryActive) {
            throw new RuntimeException("Jquery is not active. Command couldn't be performed.");
        }

        for (int i = 0; i < JQUERY_RETRIES_WAIT; i++) {
            try {
                logger.info("Executing '{}'", jquery);
                return jse.executeScript(jquery);
            } catch (Exception e) {
                logger.info("Something went wrong while executing jquery '{}'", jquery);
                try {
                    Thread.sleep(JQUERY_RETRIES_TIMEOUT);
                } catch (InterruptedException e1) {
                    throw new RuntimeException("Interrupted exception has occurred.");
                }
            }
        }
        return null;
    }
}
