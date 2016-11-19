package com.quandoo.testers.authentication;

import com.quandoo.init.SeleniumAsserter;
import com.quandoo.init.SeleniumClicker;
import com.quandoo.init.SeleniumFinder;
import com.quandoo.testers.AbstractTester;

/**
 * Tester for the form authentication page.
 *
 * @author Andrey Klimachev(andre.klim@gmail.com)
 * @since 0.0.1
 */
public class FormAuthenticationTester extends AbstractTester {

    /**
     * Success tester.
     */
    private FormAuthenticationSuccessTester successTester;

    /**
     * Error tester.
     */
    private FormAuthenticationErrorTester errorTester;

    /**
     * Construct tester for the main page.
     *
     * @param finder    Instance of selenium finder.
     * @param clicker   Instance of selenium clicker.
     * @param asserter  Instance of selenium asserter.
     */
    public FormAuthenticationTester(final SeleniumFinder finder, final SeleniumClicker clicker, final SeleniumAsserter asserter) {
        super(finder, clicker, asserter);
        successTester = new FormAuthenticationSuccessTester(finder, clicker, asserter);
        errorTester = new FormAuthenticationErrorTester(finder, clicker, asserter);
    }

    /**
     * @return endpoint to the success tester.
     */
    public FormAuthenticationSuccessTester success() {
        return successTester;
    }

    /**
     * @return  endpoint to the error tester.
     */
    public FormAuthenticationErrorTester error() {
        return errorTester;
    }

    /*****************************************************************************************************
     *                                          E D I T                                                  *
     *****************************************************************************************************/

    /**
     * Edits username field.
     *
     * @param username    Username to be introduced in username field.
     */
    public void editUsername(String username) {
        clicker.sendKeysToWebElement("$('#username')", username);
    }

    /**
     * Edits password field.
     *
     * @param password    Password to be introduced in username field.
     */
    public void editPassword(String password) {
        clicker.sendKeysToWebElement("$('#password')", password);
    }

    /*****************************************************************************************************
     *                                        C L I C K S                                                *
     *****************************************************************************************************/

    /**
     * Clicks login button.
     */
    public void clickLogin() {
        clicker.clickWebElement("$('.fa-sign-in')");
    }
}
