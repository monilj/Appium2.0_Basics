import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AutomationTestWithAppium {
    static AndroidDriver<AndroidElement> androidDriver;

    @BeforeTest
    public static AndroidDriver<AndroidElement> androidCapForNativeApp() throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app", "/Users/dev/Documents/monilj/AppiumPro/app/API_Demos.apk");

        try {
            androidDriver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        androidDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return androidDriver;

    }

    @Test
    public void openApp(){
        
    }
}
