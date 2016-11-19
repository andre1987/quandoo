package com.quandoo.init;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * God class of the selenium API for quandoo. From here all API methods are invoked.
 * It also starts and closes the Web driver.
 *
 * @author Andrey Klimachev(andre.klim@gmail.com)
 * @since 0.0.1
 */
public class QuandooSeleniumApi {
    /**
     * Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(QuandooSeleniumApi.class);

    /**
     * Base url of quandoo page.
     */
    private static final String BASE_URL = "http://the-internet.herokuapp.com/";

    /**
     * Web driver used to interact with the browser.
     */
    private WebDriver driver;

    /**
     * Tester object. This object is the endpoint to all tester object that will contain the API to manipulate quandoo page.
     */
    private Testers testers;

    /**
     * Manipulates jquery to find an element.
     */
    private SeleniumFinder finder;

    /**
     * Clicks in jquery element.
     */
    private SeleniumClicker clicker;

    /**
     * Asserts element in the page.
     */
    private SeleniumAsserter asserter;

    /**
     * Path to chrome driver for linux.
     */
    public static final String CHROME_DRIVER_LINUX_PATH = "/src/test/resources/chromedriver";

    /**
     * Path to chrome driver for windows.
     */
    public static final String CHROME_DRIVER_WINDOWS_PATH = "/src/test/resources/chromedriver.exe";

    /**
     * Default public constructor.
     */
    public QuandooSeleniumApi() {

    }

    /**
     * Initializes the quandoo selenium API: selenium driver and base url.
     */
    public void init() {
        logger.debug("Starting web driver for GoEuro.");
        Parameters parameters = new Parameters();
        parameters.verifyCmdParameters();

        logger.info("Tests will run in {} - {}", parameters.osType, parameters.browserType);

        switch (parameters.type) {
            case WINDOWS_FIREFOX:
            case LINUX_FIREFOX: driver = new FirefoxDriver(DesiredCapabilities.firefox()); break;
            case LINUX_CHROME:
                System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, new File("").getAbsolutePath() + CHROME_DRIVER_LINUX_PATH);
                driver = new ChromeDriver(DesiredCapabilities.chrome());
                break;
            case WINDOWS_CHROME:
                System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, new File("").getAbsolutePath() + CHROME_DRIVER_WINDOWS_PATH);
                driver = new ChromeDriver(DesiredCapabilities.chrome());
                break;
            default: throw new RuntimeException("Machine type is invalid");
        }

        //maximize the window
        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        finder = new SeleniumFinder(driver);
        clicker = new SeleniumClicker(finder);
        asserter = new SeleniumAsserter(finder);

        testers = new Testers(driver, finder, clicker, asserter);

        driver.get(BASE_URL);
        logger.debug("Started web driver for quandoo.");
    }

    /**
     * Quit Selenium Web Driver.
     */
    public void quitDriver() {
        logger.debug("Quitting Selenium Web Driver.");
        if (driver != null ) {
            driver.quit();
        }
        logger.debug("Quited Selenium Web Driver.");
        driver = null;
    }

    /**
     * Endpoint to testers API.
     *
     * @return  Testers API class.
     */
    public Testers tester() {
        return testers;
    }

    /**
     * Enumeration the type of machines where selenium tests can run.
     */
    public enum MachinesTypes {
        /**
         * Linux machine with firefox.
         */
        LINUX_FIREFOX("linux_firefox"),
        /**
         * Linux machine with chrome.
         */
        LINUX_CHROME("linux_chrome"),
        /**
         * Windows machine with firefox.
         */
        WINDOWS_FIREFOX("windows_firefox"),
        /**
         * Windows machine with chrome.
         */
        WINDOWS_CHROME("windows_chrome");

        /**
         * Machine in text.
         */
        private final String machine;

        /**
         * Constructor.
         *
         * @param machine   code for the machine type.
         */
        MachinesTypes(final String machine) {
            this.machine = machine;
        }

        /**
         * Converts a string into enum.
         *
         * @param machine   String to be converted.
         * @return          Found enum, otherwise null.
         */
        public static MachinesTypes fromString(String machine) {
            if (machine != null) {
                for (MachinesTypes machinesType : MachinesTypes.values()) {
                    if (machine.equalsIgnoreCase(machinesType.machine)) {
                        return machinesType;
                    }
                }
            }
            return null;
        }

        /**
         * @see java.lang.Enum#toString()
         */
        public String toString() {
            return machine;
        }
    }

    /**
     * Class to handle the parameters of quandoo selenium API.
     */
    private static class Parameters {
        /**
         * Allowed operating system.
         */
        private String[] allowedOS = {"windows", "linux"};

        /**
         * Allowed browsers.
         */
        private String[] allowedBrowsers = {"chrome", "firefox"};

        /**
         * Chosen operating system.
         */
        private String osType;

        /**
         * Chosen browsers.
         */
        private String browserType;

        /**
         * Chosen machine type.
         */
        public MachinesTypes type;

        /**
         * Check if the parameters are ok.
         */
        public void verifyCmdParameters() {
            verifyOS();
            verifyBrowser();

            type = MachinesTypes.fromString(osType.toLowerCase() + "_" + browserType.toLowerCase());
        }

        /**
         * Verify the operating system.
         */
        private void verifyOS() {
            //Default operating system is the current operating system.
            osType = System.getProperty("os");
            if (osType == null) {
                throw new RuntimeException("Please define operating system with property -Dos.");
            }

            if (!Arrays.asList(allowedOS).contains(osType)) {
                logger.error("The operating system defined by the property -Dos is not supported.");

                String err = "The supported operating system are: ";
                for (String os : allowedOS) {
                    err = err + "'" + os + "' ";
                }
                err = err.concat(".");
                throw new RuntimeException(err);
            }
        }

        /**
         * Verify the browser.
         */
        private void verifyBrowser() {
            //Default browser if firefox.
            browserType = System.getProperty("browser") == null ? "firefox" : System.getProperty("browser");

            if (!Arrays.asList(allowedBrowsers).contains(browserType)) {
                logger.error("The Browser '{}' defined by the property -Dbrowser is not supported.", browserType);

                String err = "The supported browsers are: ";
                for (String browser : allowedBrowsers) {
                    err = err + "'" + browser + "' ";
                }
                err = err.concat(".");
                throw new RuntimeException(err);
            }
        }
    }
}
