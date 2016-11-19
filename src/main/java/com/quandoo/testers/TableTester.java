package com.quandoo.testers;

import com.google.common.collect.Lists;
import com.quandoo.init.SeleniumAsserter;
import com.quandoo.init.SeleniumClicker;
import com.quandoo.init.SeleniumFinder;
import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tester for the table page.
 *
 * @author Andrey Klimachev(andre.klim@gmail.com)
 * @since 0.0.1
 */
public class TableTester extends AbstractTester {

    private final String TABLE_CONTAINER = "$('#table2')";
    /**
     * Construct tester for the main page.
     *
     * @param finder    Instance of selenium finder.
     * @param clicker   Instance of selenium clicker.
     * @param asserter  Instance of selenium asserter.
     */
    public TableTester(final SeleniumFinder finder, final SeleniumClicker clicker, final SeleniumAsserter asserter) {
        super(finder, clicker, asserter);
    }

    /*****************************************************************************************************
     *                                        C L I C K S                                                *
     *****************************************************************************************************/

    /**
     * Clicks "Last Name" header.
     */
    public void orderLastName() {
        clicker.clickWebElement(TABLE_CONTAINER + ".find('.last-name')");
    }

    /*****************************************************************************************************
     *                                         A S S E R T                                               *
     *****************************************************************************************************/

    /**
     * Asserts that the list of {@code lastNames} is ordered in an ascending and descending order.
     *
     * @param lastNames     List last names.
     * @param isAscending   True if the list should be ordered in an ascending order, false if in a descending order.
     */
    public void orderedByLastName(List<String> lastNames, boolean isAscending) {
        List<String> orderedList = Lists.newCopyOnWriteArrayList(lastNames);
        if (isAscending) {
            Collections.sort(orderedList);
            Assert.assertTrue("List of last names is not ordered in an ascending order", lastNames.equals(orderedList));
        } else {
            Collections.sort(orderedList);
            Collections.reverse(orderedList);
            Assert.assertTrue("List of last names is not ordered in a descending order", lastNames.equals(orderedList));
        }
    }

    /*****************************************************************************************************
     *                                      A U X I L I A R Y                                            *
     *****************************************************************************************************/

    /**
     * Gets last names.
     *
     * @return  List of last names.
     */
    public List<String> getLastName() {
        List<String> lastNames = new ArrayList<>();
        int numLastNames = finder.findInt(TABLE_CONTAINER + ".find('tbody').find('td.last-name').length");
        for(int i = 0; i < numLastNames; i++) {
            lastNames.add(finder.findString(TABLE_CONTAINER + ".find('tbody').find('td.last-name').eq(" + i + ").text().trim()"));
        }
        return lastNames;
    }
}
