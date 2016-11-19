package com.quandoo.testers;

import com.quandoo.init.SeleniumAsserter;
import com.quandoo.init.SeleniumClicker;
import com.quandoo.init.SeleniumFinder;

/**
 * Abstract tester class.
 *
 * @author Andrey Klimachev(andre.klim@gmail.com)
 * @since 0.0.1
 */
public class AbstractTester {

    /**
     * The jquery for the container of this tester.
     */
    protected String container;

    /**
     * Manipulates jquery to find an element.
     */
    protected SeleniumFinder finder;

    /**
     * Clicks in jquery element.
     */
    protected SeleniumClicker clicker;

    /**
     * Asserts element in the page.
     */
    protected SeleniumAsserter asserter;

    /**
     * Construct a Tester with the given parameters.
     *
     * @param container jquery container for this tester.
     * @param finder    Instance of selenium finder.
     * @param clicker   Instance of selenium clicker.
     * @param asserter  Instance of selenium asserter.
     */
    protected AbstractTester(final String container, final SeleniumFinder finder, final SeleniumClicker clicker, final SeleniumAsserter asserter) {
        this.container = container;
        this.finder = finder;
        this.clicker = clicker;
        this.asserter = asserter;
    }

    /**
     * Construct a Tester with the given parameters. The container used in the very generic $('body').
     *
     * @param finder    Instance of selenium finder.
     * @param clicker   Instance of selenium clicker.
     * @param asserter  Instance of selenium asserter.
     */
    protected AbstractTester(final SeleniumFinder finder, final SeleniumClicker clicker, final SeleniumAsserter asserter) {
        this("$('body')", finder, clicker, asserter);
    }
}
