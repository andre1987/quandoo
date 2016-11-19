package com.quandoo;

import com.quandoo.testers.HoverTester;
import com.quandoo.testers.MainPageTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Contains tests for the hover page.
 *
 * @author Andrey Klimachev(andre.klim@gmail.com)
 * @since 0.0.1
 */
public class HoverTest extends AbstractTest {
    /**
     * Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(HoverTest.class);

    private MainPageTester mainPageTester;

    private HoverTester hoverTester;
    /**
     * Set up the test environment.
     *
     * @description Opens browser.
     */
    @Before
    public void before() {
        initDriver();
        mainPageTester = api.tester().main();
        mainPageTester.clickExerciseLink("hovers");
        hoverTester = api.tester().hover();
    }

    /**
     * Quit browser.
     */
    @After
    public void after() {
        quitDriver();
    }

    /**
     * Tests hovers.
     *
     * @description Move mouse cursor over every user.
     * @assertion Assert that aditional information appears.
     */
    @Test
    public void testSuccessLogin() {
        logger.info("Checking hover scenario");

        hoverTester.mouseOverUser(1);
        hoverTester.assertUserInfo(1);

        hoverTester.mouseOverUser(2);
        hoverTester.assertUserInfo(2);

        hoverTester.mouseOverUser(3);
        hoverTester.assertUserInfo(3);
    }
}
