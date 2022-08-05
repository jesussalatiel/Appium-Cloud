package com.eot.sample.android;

import com.eot.sample.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstAppiumTest
        extends Hooks {
    private AppiumDriver driver;

    @Test
    public void runMessagesTest() {
        try {
            // 1. Create a AppiumDriver
            // 1.1 Set the capabilities of the driver
            DesiredCapabilities capabilities = new DesiredCapabilities ( );
            capabilities.setCapability ( MobileCapabilityType.AUTOMATION_NAME ,
                    "UiAutomator2" );
            capabilities.setCapability ( MobileCapabilityType.DEVICE_NAME ,
                    "emulator-5554" );
            capabilities.setCapability ( MobileCapabilityType.PLATFORM_NAME ,
                    "Android" );
            capabilities.setCapability ( AndroidMobileCapabilityType.APP_PACKAGE ,
                    "com.google.android.apps.messaging" );
            capabilities.setCapability ( AndroidMobileCapabilityType.APP_ACTIVITY ,
                    "com.google.android.apps.messaging.ui.ConversationListActivity" );
            capabilities.setCapability ( MobileCapabilityType.NO_RESET ,
                    false );
            capabilities.setCapability ( MobileCapabilityType.FULL_RESET ,
                    false );
            driver = new AppiumDriver <> ( getAppiumServerUrl ( ) ,
                    capabilities );
            System.out.println ( "Created AppiumDriver" );

            // 2. Orchestrate the test scenario
            try {
                driver.findElementById ( "com.google.android.apps.messaging:id/conversation_list_google_tos_popup_positive_button" )
                        .click ( );
                driver.findElementById ( "android:id/button2" )
                        .click ( );
                driver.findElementById ( "android:id/button1" )
                        .click ( );
            } catch (Exception e) {
                driver.findElementById ( "com.google.android.apps.messaging:id/start_new_conversation_button" ).click ();
                Thread.sleep ( 8000 );
            }
            String setName = "Itzell Rangel";
                driver.findElementById ( "com.google.android.apps.messaging:id/recipient_text_view" ).sendKeys ( setName );

            String getText = driver.findElementById ( "com.google.android.apps.messaging:id/recipient_text_view" ).getText ();

            System.out.println ( "---------------------->  " + ((getText.equals ( setName))?"Test Pass":"Test Failed")+ "  <----------------------------" );

            if ( null != driver ) {
                System.out.println ( "Close the driver" );
                driver.quit ( );
            }
        }catch (Exception e){
            System.out.println ("Bad connection: " + e.getMessage ());
        }
    }

    private void waitFor(int numberOfSeconds) {
        try {
            System.out.println("Sleep for " + numberOfSeconds);
            Thread.sleep(numberOfSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
