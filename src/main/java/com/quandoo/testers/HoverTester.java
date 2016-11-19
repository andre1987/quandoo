package com.quandoo.testers;

import com.quandoo.init.SeleniumAsserter;
import com.quandoo.init.SeleniumClicker;
import com.quandoo.init.SeleniumFinder;

/**
 * Tester for the hover page.
 *
 * @author Andrey Klimachev(andre.klim@gmail.com)
 * @since 0.0.1
 */
public class HoverTester extends AbstractTester {

    /**
     * Construct tester for the main page.
     *
     * @param finder    Instance of selenium finder.
     * @param clicker   Instance of selenium clicker.
     * @param asserter  Instance of selenium asserter.
     */
    public HoverTester(final SeleniumFinder finder, final SeleniumClicker clicker, final SeleniumAsserter asserter) {
        super(finder, clicker, asserter);
    }

    /*****************************************************************************************************
     *                                        C L I C K S                                                *
     *****************************************************************************************************/

    /**
     * Moves mouse over user.
     *
     * @param userNum   Number of the user from left to right.
     */
    public void mouseOverUser(int userNum) {
        finder.moveCursorOver("$('.figure').eq(" + (userNum - 1) + ")");
    }

    /*****************************************************************************************************
     *                                        A S S E R T                                                *
     *****************************************************************************************************/

    /**
     * Asserts user visible information
     *
     * @param userNum   Number of the user from left to right.
     */
    public void assertUserInfo(int userNum) {
        asserter.assertContainsText("$('.figcaption:visible').find('h5').text()", "user" + userNum, "User info does not appear");
    }
}
