package com.quandoo.init;

import com.quandoo.testers.HoverTester;
import com.quandoo.testers.MainPageTester;
import com.quandoo.testers.TableTester;
import com.quandoo.testers.authentication.FormAuthenticationTester;
import org.openqa.selenium.WebDriver;

/**
 * An endpoint to all the testers that can be called.
 *
 * @author Andrey Klimachev(andre.klim@gmail.com)
 * @since 0.0.1
 */
public class Testers {
    /**
     * Web driver to manipulates the browser.
     */
    private WebDriver driver;

    /**
     * Manipulates jquery to find an element.
     */
    private SeleniumFinder finder;

    /**
     * Clicks in jquery element.
     */
    private SeleniumClicker clicker;

    /**
     * Asserts element in the page.
     */
    private SeleniumAsserter asserter;

    /**
     * Constructor for all resources.
     *
     * @param driver    Instance of web driver.
     * @param finder    Instance of selenium finder.
     * @param clicker   Instance of selenium clicker.
     * @param asserter  Instance of selenium asserter.
     */
    public Testers(final WebDriver driver, final SeleniumFinder finder, final SeleniumClicker clicker, final SeleniumAsserter asserter) {
        this.driver = driver;
        this.finder = finder;
        this.clicker = clicker;
        this.asserter = asserter;
    }

    /**
     * Tester for the main page.
     */
    private MainPageTester mainPageTester;

    /**
     * Tester for the form authentication page.
     */
    private FormAuthenticationTester loginTester;

    /**
     * Hover tester.
     */
    private HoverTester hoverTester;

    /**
     * Table tester.
     */
    private TableTester tableTester;

    /**
     * Gets tester for the main page.
     *
     * @return  tester for the main page.
     */
    public MainPageTester main() {
        if (mainPageTester == null) {
            mainPageTester = new MainPageTester(finder, clicker, asserter);
        }
        return mainPageTester;
    }

    /**
     * Gets tester for the form authentication page.
     *
     * @return  tester for the form authentication page.
     */
    public FormAuthenticationTester login() {
        if (loginTester == null) {
            loginTester = new FormAuthenticationTester(finder, clicker, asserter);
        }
        return loginTester;
    }

    /**
     * Gets tester for the hover page.
     *
     * @return  tester for the hover page.
     */
    public HoverTester hover() {
        if (hoverTester == null) {
            hoverTester = new HoverTester(finder, clicker, asserter);
        }
        return hoverTester;
    }

    /**
     * Gets tester for the table page.
     *
     * @return  tester for the table page.
     */
    public TableTester table() {
        if (tableTester == null) {
            tableTester = new TableTester(finder, clicker, asserter);
        }
        return tableTester;
    }
}
