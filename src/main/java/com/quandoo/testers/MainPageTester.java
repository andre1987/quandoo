package com.quandoo.testers;

import com.quandoo.init.SeleniumAsserter;
import com.quandoo.init.SeleniumClicker;
import com.quandoo.init.SeleniumFinder;

/**
 * Tester for the main page.
 *
 * @author Andrey Klimachev(andre.klim@gmail.com)
 * @since 0.0.1
 */
public class MainPageTester extends AbstractTester {

    /**
     * Construct tester for the main page.
     *
     * @param finder    Instance of selenium finder.
     * @param clicker   Instance of selenium clicker.
     * @param asserter  Instance of selenium asserter.
     */
    public MainPageTester(final SeleniumFinder finder, final SeleniumClicker clicker, final SeleniumAsserter asserter) {
        super(finder, clicker, asserter);
    }

    /*****************************************************************************************************
     *                                        C L I C K S                                                *
     *****************************************************************************************************/

    /**
     * Clicks exercise link and find it {@code link}.
     *
     * @param link  The link to be clicked.
     */
    public void clickExerciseLink(String link) {
        finder.findWebElement("$('[href=\"/" + link + "\"]')").click();
    }

}
