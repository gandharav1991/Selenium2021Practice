/*package test;

import com.applitools.eyes.Eyes;
import com.applitools.eyes.RectangleSize;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.net.URISyntaxException;

public class Charter {

    public static void main(String[] args) throws URISyntaxException, InterruptedException {

        WebDriver driver = new FirefoxDriver();

        Eyes eyes = new Eyes();
        // This is your api key, make sure you use it in all your tests.
        eyes.setApiKey("eWFrfwRig1mlVHxT9UscWJ9SuDXs3jgXMrWqAWMcbJA110");

        try {
            // Start visual testing with browser viewport set to 1024x768.
            // Make sure to use the returned driver from this point on.
            driver = eyes.open(driver, "Applitools", "Test Web Page", new RectangleSize(1024, 768));

            driver.get("https://www.charter.com/browse/content/charter-home");

            // Visual validation point #1
            eyes.checkWindow("Main Page");

            driver.findElement(By.id("Slot_TPSilver")).click();
             Thread.sleep(20000);
            // Visual validation point #2
            eyes.checkWindow("Learn More");

            // End visual testing. Validate visual correctness.
            eyes.close();
        } finally {
            // Abort test in case of an unexpected error.
            eyes.abortIfNotClosed();
            driver.close();
        }
    }
 }*/