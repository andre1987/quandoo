package com.quandoo;

import com.quandoo.testers.HoverTester;
import com.quandoo.testers.MainPageTester;
import com.quandoo.testers.TableTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Contains tests for the table page.
 *
 * @author Andrey Klimachev(andre.klim@gmail.com)
 * @since 0.0.1
 */
public class TableTest extends AbstractTest {
    /**
     * Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(TableTest.class);

    private MainPageTester mainPageTester;

    private TableTester tableTester;
    /**
     * Set up the test environment.
     *
     * @description Opens browser.
     */
    @Before
    public void before() {
        initDriver();
        mainPageTester = api.tester().main();
        mainPageTester.clickExerciseLink("tables");
        tableTester = api.tester().table();
    }

    /**
     * Quit browser.
     */
    @After
    public void after() {
        quitDriver();
    }

    /**
     * Tests last name order functionality.
     *
     * @description Click 'Last Name' header.
     * @assertion Assert that the table lines are order by last name in ascending (one click) or descending (two clicks).
     */
    @Test
    public void testSuccessLogin() {
        logger.info("Checking hover scenario");

        tableTester.orderLastName();
        List<String> lastNameAscending = tableTester.getLastName();
        tableTester.orderedByLastName(lastNameAscending, true);

        tableTester.orderLastName();
        List<String> lastNameDescending = tableTester.getLastName();
        tableTester.orderedByLastName(lastNameDescending, false);
    }
}
