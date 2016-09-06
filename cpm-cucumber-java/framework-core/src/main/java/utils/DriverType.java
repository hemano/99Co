package utils;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by hemantojha on 09/07/16.
 */
public enum DriverType implements DriverSetup {

    FIREFOX {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities =
                    DesiredCapabilities.firefox();
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new FirefoxDriver(capabilities);
        }
    },

    FIREFOX_LOCAL {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities =
                    DesiredCapabilities.firefox();
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            FirefoxProfile profile = new FirefoxProfile();

            try {
                String firebugExt = new PropertyReader().readProperty("firebug.extension.xpi");
                String firepathExt = new PropertyReader().readProperty("firepath.extension.xpi");
                profile.addExtension(new File(firebugExt));
                profile.addExtension(new File(firepathExt));

                String ext = "extensions.firebug.";
                String ext1 = "extensions.firepath.";

                profile.setPreference(ext + "currentVersion", "2.0.16");
                profile.setPreference(ext1 + "currentVersion", "0.9.7");
                profile.setPreference(ext + "allPagesActivation", "on");
                profile.setPreference(ext + "defaultPanelName", "net");
                profile.setPreference(ext + "net.enableSites", true);


                WebDriver firefox = new FirefoxDriver(profile);
                firefox.manage().deleteAllCookies();
                return firefox;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new FirefoxDriver(capabilities);
        }
    },

    CHROME {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities =
                    DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches",
                    Arrays.asList("--no-default-browser-check"));
            HashMap<String, String> chromePreferences = new HashMap<String, String>();
            chromePreferences.put(
                    "profile.password_manager_enabled", "false");
            capabilities.setCapability("chrome.prefs", chromePreferences);

            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new ChromeDriver(capabilities);
        }
    },

    IE {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities =
                    DesiredCapabilities.internetExplorer();
            capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
                    true);
            capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,
                    true);
            capabilities.setCapability("requireWindowFocus",
                    true);

            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new InternetExplorerDriver(capabilities);
        }
    },

    SAFARI {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities =
                    DesiredCapabilities.safari();
            capabilities.setCapability("safari.cleansession",
                    true);

            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new SafariDriver(capabilities);
        }
    },

    OPERA {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities =
                    DesiredCapabilities.operaBlink();
            capabilities.setCapability("safari.cleansession",
                    true);

            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new OperaDriver(capabilities);
        }
    },
    //TODO  - we're now extending to Mobile automation, we can use driver instead of browser from pom.xml
    IOS_DRIVER {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("appium-version", "1.0");
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("platformVersion", "9.2");
            capabilities.setCapability("deviceName", "iPhone 6s");
            capabilities.setCapability("app", "/Users/hemantojha/Documents/Cellpoint/mbeapp_3_12/MBE.app");
            capabilities.setCapability("autoAcceptAlerts", true);

            return capabilities;
        }

    public WebDriver getWebDriverObject(DesiredCapabilities capabilities) throws MalformedURLException {

            String appiumServerURL = new PropertyReader().readProperty("AppiumServerURL");
           return new IOSDriver(new URL(appiumServerURL), capabilities);
    }
    }

}
