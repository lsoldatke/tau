package org.example.lab2;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class WpTests {
    @Test
    public void testIfPageIsLoaded() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.wp.pl/");

            Thread.sleep(5000);

            assertEquals("Page titles are different", driver.getTitle(), "Wirtualna Polska - Wszystko co ważne - www.wp.pl");
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testIfPrivacyPolicyPromptCanBeRejected() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.wp.pl/");

            Thread.sleep(5000);

            WebElement advancedSettingsButton = driver.findElement(By.xpath("//button[text()='USTAWIENIA ZAAWANSOWANE']"));
            advancedSettingsButton.click();

            Thread.sleep(2000);

            WebElement saveSettingsButton = driver.findElement(By.xpath("//button[text()='ZAPISZ']"));
            saveSettingsButton.click();
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
