package org.example.lab2;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.*;

public class XkomTests {
    String url = "https://www.x-kom.pl/";

    @Test
    public void testIfPageIsLoaded() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(url);
            assertEquals("Page titles are different", driver.getTitle(), "x-kom.pl - Sklep komputerowy");
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

            WebElement settingsButton = driver.findElement(By.xpath("//button[text()='Ustawienia']"));
            settingsButton.click();

            WebElement saveButton = driver.findElement(By.xpath("//button[text()='Zapisz']"));
            saveButton.click();
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testIfSearchWorks() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(url);

            WebElement searchBar = driver.findElement(By.xpath("//input[@placeholder='Czego szukasz?']"));
            searchBar.sendKeys("ps5");
            searchBar.sendKeys(Keys.ENTER);

            List<WebElement> noResultsInfo = driver.findElements(By.xpath("//div[text()='Nie znaleźliśmy wyników dla']"));
            assertTrue(noResultsInfo.isEmpty());

//            WebElement resultsDiv = driver.findElement(By.xpath("//div[@id='listing-container-wrapper']"));
//            List<WebElement> results = resultsDiv.findElements(By.xpath("./*"));
//
//            assertFalse(results.isEmpty());

            Thread.sleep(3000);
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
