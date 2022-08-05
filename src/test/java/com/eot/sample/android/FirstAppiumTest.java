package com.eot.sample.android;

import com.eot.sample.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class FirstAppiumTest
        extends Hooks {

    private AppiumDriver driver;
    public WebDriverWait wait;
    @Test
    public void runMessagesTest() {
        try {
            // 1. Create a AppiumDriver
            // 1.1 Set the capabilities of the driver
            DesiredCapabilities capabilities = new DesiredCapabilities ( );
            //capabilities.setCapability ( MobileCapabilityType.AUTOMATION_NAME ,
            //        "UiAutomator1" );
            capabilities.setCapability ( MobileCapabilityType.DEVICE_NAME , "Android Emulator" );
            capabilities.setCapability ( MobileCapabilityType.PLATFORM_NAME , "Android" );
            capabilities.setCapability ( MobileCapabilityType.PLATFORM_VERSION, "7.1.1" );
            //capabilities.setCapability ( "app",  new File (System.getProperty("user.dir") +"/app/spotify.apk").getPath () );
            capabilities.setCapability ( "app", "https://dw.uptodown.com/dwn/X2squymuacwklods30kSpzMGoHUXhioXMoG6x-kulpgqHrVJ3DpilV8bMSZOZyQJvgxS3xerEEKhT8UuD1FXv8z9DkCt05QT6ZM5yOxDp08-ZIxGoPwyuqxjS_LFz45i/F_7oz2lrZF3VeyJBbA4GimofiO4gJoFo8Tbmn5p7LjDIuk0ZLTECfBl841I7QZcQvcGld7XLKH0ET1hfaEChq2JSQZDRf70xzzE34Wm2AR4-UXfm0rPgDnr6IBPbV7uU/dSrRRY8f3vRgqJk8EiYz9_PaTMrxqOy5MTKlax7pMYQ0PffT65u5bhRopVxI3vFkFJ9Dpz_NaztaHHXuTvnxuQ==/" );
            capabilities.setCapability ( MobileCapabilityType.NO_RESET , false );
            capabilities.setCapability ( MobileCapabilityType.FULL_RESET , false );
            driver = new AppiumDriver <> ( getAppiumServerUrl ( ) , capabilities );

            wait = new WebDriverWait ( driver, 30);

            System.out.println ( "Created AppiumDriver" );

            // 2. Orchestrate the test scenario
            try {
                Thread.sleep ( 15000 );

                driver.launchApp ();

                // Click on Login
                By loginBy = By.xpath ( "//*[@text='Sign up free']" );
                wait.until ( ExpectedConditions.visibilityOfElementLocated ( loginBy ) ).click ( );

                // Enter user
                By enterUserBy = By.id ( "com.spotify.music:id/email" );
                wait.until ( ExpectedConditions.visibilityOfElementLocated ( enterUserBy ) ).sendKeys ( "test@test.com" );

                // Click on Login
                By submitBy = By.id ( "com.spotify.music:id/email_next_button" );
                wait.until ( ExpectedConditions.visibilityOfElementLocated ( submitBy ) ).click ( );

                // Go to Login
                By goToLoginBy = By.id ( "com.spotify.music:id/button_positive" );
                wait.until ( ExpectedConditions.visibilityOfElementLocated ( goToLoginBy ) ).click ( );

                // Validate "Sign up free" Button is Displayed
                By assertMagicLinkBy = By.id ( "com.spotify.music:id/request_magiclink_lower_button" );

                System.out.println ("-----------------------------------------> " + wait.until ( ExpectedConditions.visibilityOfElementLocated ( assertMagicLinkBy ) ).isDisplayed ( ) + " <--------------------------------");
                Assert.assertTrue ( wait.until ( ExpectedConditions.visibilityOfElementLocated ( assertMagicLinkBy ) ).isDisplayed ( )  );

            } catch (Exception e) {
                e.printStackTrace ();
            }

            if ( null != driver ) {
                System.out.println ( "Close the driver" );
                driver.quit ( );
            }
        }catch (Exception e){
            System.out.println ("Bad connection: " + e.getMessage ());
        }
    }
}
