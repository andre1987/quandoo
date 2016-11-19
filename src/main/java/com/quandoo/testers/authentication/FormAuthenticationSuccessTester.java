package com.quandoo.testers.authentication;

import com.quandoo.init.SeleniumAsserter;
import com.quandoo.init.SeleniumClicker;
import com.quandoo.init.SeleniumFinder;
import com.quandoo.testers.AbstractTester;

/**
 * Tester for the form success page.
 *
 * @author Andrey Klimachev(andre.klim@gmail.com)
 * @since 0.0.1
 */
public class FormAuthenticationSuccessTester extends AbstractTester {

    /**
     * Construct tester for the success page.
     *
     * @param finder    Instance of selenium finder.
     * @param clicker   Instance of selenium clicker.
     * @param asserter  Instance of selenium asserter.
     */
    public FormAuthenticationSuccessTester(final SeleniumFinder finder, final SeleniumClicker clicker, final SeleniumAsserter asserter) {
        super(finder, clicker, asserter);
    }

    /*****************************************************************************************************
     *                                         A S S E R T                                               *
     *****************************************************************************************************/

    /**
     * Asserts success page.
     */
    public void assertSuccessPage() {
        final String successMessage = "You logged into a secure area!";
        asserter.assertContainsText("$('.flash.success').text().trim()", successMessage, "Success message " + successMessage + " did not appear.");
    }
}
