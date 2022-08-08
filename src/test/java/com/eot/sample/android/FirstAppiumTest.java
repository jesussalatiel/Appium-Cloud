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
import java.net.URL;
import java.time.Duration;

public class FirstAppiumTest
        extends Hooks {

    private AppiumDriver driver;
    public WebDriverWait wait;

    @Test
    public void runMessagesTest() {
        try {
            File app = new File (System.getProperty("user.dir") +"/notepad.apk");
            // 1. Create a AppiumDriver
            // 1.1 Set the capabilities of the driver
            DesiredCapabilities capabilities = new DesiredCapabilities ( );
            capabilities.setCapability ( MobileCapabilityType.DEVICE_NAME , "android" );
            capabilities.setCapability("enableVNC", true);
            //capabilities.setCapability ( MobileCapabilityType.PLATFORM_NAME , "Android" );
            capabilities.setCapability ( "app",  "https://github.com/jesussalatiel/Appium-Cloud/raw/main/notepad.apk"  );
            capabilities.setCapability ( "androidInstallTimeout", 180000 );
            capabilities.setCapability ( "newCommandTimeout", 180000 );
            capabilities.setCapability ( MobileCapabilityType.NO_RESET , false );
            capabilities.setCapability ( MobileCapabilityType.FULL_RESET , false );
            driver = new AppiumDriver <> ( getAppiumServerUrl ( ) , capabilities );

            wait = new WebDriverWait ( driver, 30);

            System.out.println ( "Created AppiumDriver" );

            // 2. Orchestrate the test scenario
            try {
                String title = "test@test.com";

                driver.launchApp ();

                // Click on Add Note
                By addBy = By.id ( "com.onto.notepad:id/add_note" );
                wait.until ( ExpectedConditions.visibilityOfElementLocated ( addBy ) ).click ( );

                // Enter Title
                By enterUserBy = By.id ( "com.onto.notepad:id/titleEdit" );
                wait.until ( ExpectedConditions.visibilityOfElementLocated ( enterUserBy ) ).sendKeys ( title );

                // Enter Content
                By submitBy = By.id ( "com.onto.notepad:id/contentEdit" );
                wait.until ( ExpectedConditions.visibilityOfElementLocated ( submitBy ) ).sendKeys ( "Jesus Salatiel" );

                // Click on save note
                By goToLoginBy = By.id ( "com.onto.notepad:id/save_note" );
                wait.until ( ExpectedConditions.visibilityOfElementLocated ( goToLoginBy ) ).click ( );


                By checkText = By.xpath ( "//*[@text='"+title+"']" );
                String getText = wait.until ( ExpectedConditions.visibilityOfElementLocated ( checkText ) ).getText ();

                Assert.assertEquals ( getText, title, "Is not equals" );

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
