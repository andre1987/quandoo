package com.quandoo;

import com.quandoo.testers.MainPageTester;
import com.quandoo.testers.authentication.FormAuthenticationTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Contains tests for the form authentication page.
 *
 * @author Andrey Klimachev(andre.klim@gmail.com)
 * @since 0.0.1
 */
public class FormAuthenticationTest extends AbstractTest {
    /**
     * Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(FormAuthenticationTest.class);

    private MainPageTester mainPageTester;

    private FormAuthenticationTester loginTester;

    /**
     * Set up the test environment.
     *
     * @description Opens browser.
     */
    @Before
    public void before() {
        initDriver();
        mainPageTester = api.tester().main();
        loginTester = api.tester().login();
        mainPageTester.clickExerciseLink("login");
    }

    /**
     * Quit browser.
     */
    @After
    public void after() {
        quitDriver();
    }

    /**
     * Tests for empty password.
     *
     * @description Fill username field and keep password field empty.
     * @assertion Assert that error message appears.
     */
    @Test
    public void testSuccessLogin() {
        logger.info("Checking success login scenario");

        loginTester.editUsername("tomsmith");
        loginTester.editPassword("SuperSecretPassword!");
        loginTester.clickLogin();

        loginTester.success().assertSuccessPage();
    }

    /**
     * Tests for empty password.
     *
     * @description Fill username field and keep password field empty.
     * @assertion Assert that error message appears.
     */
    @Test
    public void testEmptyPassword() {
        logger.info("Checking empty password scenario.");

        loginTester.editUsername("tomsmith");
        loginTester.clickLogin();
        loginTester.error().assertError("Your password is invalid!");
    }

    /**
     * Tests for empty username.
     *
     * @description Fill username with wrong username and keep password field empty.
     * @assertion Assert that error message appears.
     */
    @Test
    public void testWrongUsername() {
        logger.info("Checking wrong username scenario.");

        loginTester.editUsername("asd");
        loginTester.clickLogin();
        loginTester.error().assertError("Your username is invalid!");
    }
}
