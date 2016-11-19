package com.quandoo.testers.authentication;

import com.quandoo.init.SeleniumAsserter;
import com.quandoo.init.SeleniumClicker;
import com.quandoo.init.SeleniumFinder;
import com.quandoo.testers.AbstractTester;

/**
 * Tester for error on the form authentication page.
 *
 * @author Andrey Klimachev(andre.klim@gmail.com)
 * @since 0.0.1
 */
public class FormAuthenticationErrorTester extends AbstractTester {

    /**
     * Construct tester for the main page.
     *
     * @param finder    Instance of selenium finder.
     * @param clicker   Instance of selenium clicker.
     * @param asserter  Instance of selenium asserter.
     */
    public FormAuthenticationErrorTester(final SeleniumFinder finder, final SeleniumClicker clicker, final SeleniumAsserter asserter) {
        super(finder, clicker, asserter);
    }

    /*****************************************************************************************************
     *                                         A S S E R T                                               *
     *****************************************************************************************************/

    /**
     * Assert error message on the page.
     *
     * @param errorMessage  Error message to be asserted.
     */
    public void assertError(String errorMessage) {
        asserter.assertContainsText("$('.flash.error').text().trim()", errorMessage, "Error message " + errorMessage + " did not appear.");
    }
}
