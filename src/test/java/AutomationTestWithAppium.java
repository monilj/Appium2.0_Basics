import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AutomationTestWithAppium {
    static AndroidDriver<AndroidElement> androidDriver;
    static AppiumDriverLocalService service;
    @BeforeTest
    public static AndroidDriver<AndroidElement> androidCapForNativeApp() throws IOException {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.usingAnyFreePort();
        builder.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"));
        builder.withAppiumJS(new File("C:\\Users\\monil.joshi\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium"));
        HashMap<String, String> environment = new HashMap();
        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
        builder.withEnvironment(environment);
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app", "D:\\IntellijProjects\\Appium 2.0\\Appium2.0_Basics\\app\\Learn_Android_With_Source_Code.apk");

        androidDriver = new AndroidDriver<AndroidElement>(service.getUrl(), capabilities);
        androidDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return androidDriver;

    }

    @Test
    public void testUiWidgets() {
        String textPass = "Hello";
        androidDriver.findElementByXPath("//android.widget.TextView[@text='Android UI Widgets']").click();
        androidDriver.findElementByXPath("//android.widget.TextView[@text='EditText']/following-sibling::android.widget.ImageView").click();
        androidDriver.findElementByClassName("android.widget.EditText").sendKeys(textPass);
        String typedText = androidDriver.findElementById("com.tutorials.learn.androidexample:id/txtedt").getText();
        Assert.assertTrue(textPass.equalsIgnoreCase(typedText));

    }
}
