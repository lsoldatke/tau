package org.example.lab2;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.*;

public class MoreleTests {
    String url = "https://www.morele.net/";

    @Test
    public void testIfPageIsLoaded() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(url);

            Thread.sleep(5000);

            assertEquals("Page titles are different", "Morele - zakupy online to pestka", driver.getTitle());
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

            Thread.sleep(2000);

            WebElement settingsButton = driver.findElement(By.xpath("//button[text()='Ustawienia zaawansowane']"));
            settingsButton.click();

            WebElement saveButton = driver.findElement(By.xpath("//button[text()='Zapisz']"));
            saveButton.click();

            Thread.sleep(5000);

            List<WebElement> modal = driver.findElements(By.className("overlay"));

            assertTrue(modal.isEmpty());
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

            Thread.sleep(2000);

            WebElement settingsButton = driver.findElement(By.xpath("//button[text()='Ustawienia zaawansowane']"));
            settingsButton.click();

            WebElement saveButton = driver.findElement(By.xpath("//button[text()='Zapisz']"));
            saveButton.click();

            WebElement searchBar = driver.findElement(By.name("q"));
            searchBar.sendKeys("xbox series x");
            searchBar.sendKeys(Keys.ENTER);

            List<WebElement> noResultsInfo = driver.findElements(By.xpath("//h2[text()='Brak wyników :(']"));

            Thread.sleep(5000);

            assertTrue(noResultsInfo.isEmpty());
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testIfArticleCanBeOpened() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(url);

            Thread.sleep(2000);

            WebElement settingsButton = driver.findElement(By.xpath("//button[text()='Ustawienia zaawansowane']"));
            settingsButton.click();

            WebElement saveButton = driver.findElement(By.xpath("//button[text()='Zapisz']"));
            saveButton.click();

            WebElement searchBar = driver.findElement(By.name("q"));
            searchBar.sendKeys("xbox series x");
            searchBar.sendKeys(Keys.ENTER);

            Thread.sleep(2000);

            WebElement articles = driver.findElement(By.className("cat-list-products"));
            WebElement firstArticle = articles.findElement(By.cssSelector(":first-child"));

            firstArticle.click();

            Thread.sleep(5000);

            assertNotEquals("https://www.morele.net/wyszukiwarka/?q=xbox+series+x&d=0", driver.getTitle());
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testIfArticleCanBeAddedToCart() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(url);

            Thread.sleep(2000);

            WebElement settingsButton = driver.findElement(By.xpath("//button[text()='Ustawienia zaawansowane']"));
            settingsButton.click();

            WebElement saveButton = driver.findElement(By.xpath("//button[text()='Zapisz']"));
            saveButton.click();

            WebElement searchBar = driver.findElement(By.name("q"));
            searchBar.sendKeys("xbox series x");
            searchBar.sendKeys(Keys.ENTER);

            Thread.sleep(2000);

            WebElement articles = driver.findElement(By.className("cat-list-products"));
            WebElement firstArticle = articles.findElement(By.cssSelector(":first-child"));

            firstArticle.click();

            String articleUrl = "";

            WebElement addToCartButton = driver.findElement(By.xpath("//*[contains(text(), 'Do koszyka')]"));
            addToCartButton.click();

            Thread.sleep(2000);

            WebElement noWarrantButton = driver.findElement(By.xpath("//button[text()='Nie potrzebuję dodatkowej ochrony']"));
            addToCartButton.click();

            WebElement addedProductUrl = driver.findElement(By.xpath("//div[@class='product-title']//a"));
            addedProductUrl.getAttribute("href");

            Thread.sleep(5000);

            String addedArticleUrl = "";

            assertEquals("Article URL and added article URL are different", articleUrl, addedArticleUrl);
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
