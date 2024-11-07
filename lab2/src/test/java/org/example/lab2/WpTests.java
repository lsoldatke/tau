package org.example.lab2;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.*;

public class WpTests {
    String url = "https://www.wp.pl/";

    @Test
    public void testIfPageIsLoaded() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(url);

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
            driver.get(url);

            WebElement advancedSettingsButton = driver.findElement(By.xpath("//button[text()='USTAWIENIA ZAAWANSOWANE']"));
            advancedSettingsButton.click();

            WebElement saveSettingsButton = driver.findElement(By.xpath("//button[text()='ZAPISZ']"));
            saveSettingsButton.click();

            Thread.sleep(5000);

            List<WebElement> modal = driver.findElements(By.className("ct0khm4"));

            assertTrue(modal.isEmpty());
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
