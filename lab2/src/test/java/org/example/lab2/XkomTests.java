package org.example.lab2;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class XkomTests {
    String url = "https://www.x-kom.pl/";

    @Test
    public void pageIsLoaded() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(url);

            JavascriptExecutor jse = (JavascriptExecutor) driver;
            String state = (String) jse.executeScript("return document.readyState");

            assertEquals("complete", state);
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @Test
    public void cookiesCanBeRejected() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(url);

            WebElement settingsButton = driver.findElement(By.cssSelector(".parts__ButtonWrapper-sc-6adb784e-0.parts__ManageButton-sc-22bd9b2d-8.kXIGaP.sftOk"));
            settingsButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

            WebElement necessaryCookiesConsent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".parts__SwitchBackground-sc-151d6b5a-0.jehpSn")));
            List<WebElement> optionalCookiesConsents = driver.findElements(By.cssSelector(".parts__SwitchBackground-sc-151d6b5a-0.cgXexQ"));

            assertEquals("true", necessaryCookiesConsent.getAttribute("aria-pressed"));
            for (WebElement optionalCookieConsent : optionalCookiesConsents) {
                assertEquals("false", optionalCookieConsent.getAttribute("aria-pressed"));
            }

            WebElement saveSettingsButton = driver.findElement(By.cssSelector(".parts__ButtonWrapper-sc-6adb784e-0.parts__ManageButton-sc-22bd9b2d-8.kXIGaP.sftOk"));
            saveSettingsButton.click();

            Thread.sleep(2000);

            List<WebElement> overlay = driver.findElements(By.cssSelector(".ReactModal__Overlay.ReactModal__Overlay--after-open.overlay"));

            assertTrue(overlay.isEmpty());
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @Test
    public void cookiesCanBeAccepted() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

            WebElement agreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".parts__ButtonWrapper-sc-6adb784e-0.parts__AcceptButton-sc-22bd9b2d-9.kXIGaP.jbQKAv")));
            agreeButton.click();

            Thread.sleep(2000);

            List<WebElement> overlay = driver.findElements(By.cssSelector(".ReactModal__Overlay.ReactModal__Overlay--after-open.overlay"));

            assertTrue(overlay.isEmpty());
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

            WebElement settingsButton = driver.findElement(By.xpath("//button[text()='Ustawienia']"));
            settingsButton.click();

            WebElement saveButton = driver.findElement(By.xpath("//button[text()='Zapisz']"));
            saveButton.click();

            WebElement searchBar = driver.findElement(By.xpath("//input[@placeholder='Czego szukasz?']"));
            searchBar.sendKeys("ps5");
            searchBar.sendKeys(Keys.ENTER);

            List<WebElement> noResultsInfo = driver.findElements(By.xpath("//div[text()='Nie znaleźliśmy wyników dla']"));

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

            WebElement settingsButton = driver.findElement(By.xpath("//button[text()='Ustawienia']"));
            settingsButton.click();

            WebElement saveButton = driver.findElement(By.xpath("//button[text()='Zapisz']"));
            saveButton.click();

            WebElement searchBar = driver.findElement(By.xpath("//input[@placeholder='Czego szukasz?']"));
            searchBar.sendKeys("ps5");
            searchBar.sendKeys(Keys.ENTER);

            Thread.sleep(2000);

            WebElement articles = driver.findElement(By.id("listing-container"));
            WebElement firstArticle = articles.findElement(By.cssSelector(":first-child"));

            firstArticle.click();

            Thread.sleep(5000);

            assertNotEquals("https://www.x-kom.pl/g-7/c/2572-konsole-playstation.html", driver.getTitle());
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testIfLoginPageCanBeOpened() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(url);

            WebElement settingsButton = driver.findElement(By.xpath("//button[text()='Ustawienia']"));
            settingsButton.click();

            WebElement saveButton = driver.findElement(By.xpath("//button[text()='Zapisz']"));
            saveButton.click();

            WebElement accountLink = driver.findElement(By.cssSelector("a[href='/konto']"));
            accountLink.click();

            Thread.sleep(2000);

            WebElement loginLink = driver.findElement(By.cssSelector("a[href='/logowanie']"));
            loginLink.click();

            Thread.sleep(5000);

            assertEquals("Logowanie - Sklep komputerowy - x-kom.pl", driver.getTitle());
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testIfRegisterPageCanBeOpened() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(url);

            WebElement settingsButton = driver.findElement(By.xpath("//button[text()='Ustawienia']"));
            settingsButton.click();

            WebElement saveButton = driver.findElement(By.xpath("//button[text()='Zapisz']"));
            saveButton.click();

            WebElement accountLink = driver.findElement(By.cssSelector("a[href='/konto']"));
            accountLink.click();

            Thread.sleep(2000);

            WebElement loginLink = driver.findElement(By.cssSelector("a[href='/rejestracja']"));
            loginLink.click();

            Thread.sleep(5000);

            assertEquals("Rejestracja - Sklep komputerowy - x-kom.pl", driver.getTitle());
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testIfCartCanBeOpened() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(url);

            WebElement settingsButton = driver.findElement(By.xpath("//button[text()='Ustawienia']"));
            settingsButton.click();

            WebElement saveButton = driver.findElement(By.xpath("//button[text()='Zapisz']"));
            saveButton.click();

            WebElement accountLink = driver.findElement(By.cssSelector("a[href='/koszyk']"));
            accountLink.click();

            Thread.sleep(5000);

            assertEquals("Twój koszyk - Sklep komputerowy - x-kom.pl", driver.getTitle());
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testLoginWithBlankData() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(url);

            WebElement settingsButton = driver.findElement(By.xpath("//button[text()='Ustawienia']"));
            settingsButton.click();

            WebElement saveButton = driver.findElement(By.xpath("//button[text()='Zapisz']"));
            saveButton.click();

            WebElement accountLink = driver.findElement(By.cssSelector("a[href='/konto']"));
            accountLink.click();

            Thread.sleep(2000);

            WebElement loginLink = driver.findElement(By.cssSelector("a[href='/logowanie']"));
            loginLink.click();

            Thread.sleep(2000);

            WebElement loginInput = driver.findElement(By.name("login"));
            loginInput.sendKeys("");

            WebElement passwordInput = driver.findElement(By.name("password"));
            passwordInput.sendKeys("");

            WebElement submitButton = driver.findElement(By.cssSelector("button[type=submit]"));
            submitButton.click();

            Thread.sleep(5000);
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
