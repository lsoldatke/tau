package org.example.lab2;

import org.example.lab2.enums.Browser;
import org.example.lab2.utils.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.example.lab2.utils.Utils.waitForPageLoad;
import static org.junit.jupiter.api.Assertions.*;

public class XkomTests {
    private final String url = "https://www.x-kom.pl/";
    private WebDriver driver;

    @ParameterizedTest
    @EnumSource(Browser.class)
    public void pageIsLoaded(Browser browser) {
        driver = Utils.getDriver(String.valueOf(browser));

        try {
            driver.get(url);

            Thread.sleep(3000);

            JavascriptExecutor jse = (JavascriptExecutor) driver;
            String state = (String) jse.executeScript("return document.readyState");

            assertEquals("complete", state);
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @ParameterizedTest
    @EnumSource(Browser.class)
    public void cookiesCanBeRejected(Browser browser) {
        driver = Utils.getDriver(String.valueOf(browser));

        try {
            driver.get(url);

            WebElement cookieSettingsButton = driver.findElement(By.cssSelector(".parts__ButtonWrapper-sc-6adb784e-0.parts__ManageButton-sc-22bd9b2d-8.kXIGaP.sftOk"));
            cookieSettingsButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

            WebElement necessaryCookiesConsent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".parts__SwitchBackground-sc-151d6b5a-0.jehpSn")));
            List<WebElement> optionalCookiesConsents = driver.findElements(By.cssSelector(".parts__SwitchBackground-sc-151d6b5a-0.cgXexQ"));

            assertEquals("true", necessaryCookiesConsent.getAttribute("aria-pressed"));
            for (WebElement optionalCookieConsent : optionalCookiesConsents) {
                assertEquals("false", optionalCookieConsent.getAttribute("aria-pressed"));
            }

            WebElement saveCookieSettingsButton = driver.findElement(By.cssSelector(".parts__ButtonWrapper-sc-6adb784e-0.parts__ManageButton-sc-22bd9b2d-8.kXIGaP.sftOk"));
            saveCookieSettingsButton.click();

            Thread.sleep(2000);

            List<WebElement> overlay = driver.findElements(By.cssSelector(".ReactModal__Overlay.ReactModal__Overlay--after-open.overlay"));

            assertTrue(overlay.isEmpty());
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @ParameterizedTest
    @EnumSource(Browser.class)
    public void cookiesCanBeAccepted(Browser browser) {
        driver = Utils.getDriver(String.valueOf(browser));

        try {
            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

            WebElement cookieAgreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".parts__ButtonWrapper-sc-6adb784e-0.parts__AcceptButton-sc-22bd9b2d-9.kXIGaP.jbQKAv")));
            cookieAgreeButton.click();

            Thread.sleep(2000);

            List<WebElement> overlay = driver.findElements(By.cssSelector(".ReactModal__Overlay.ReactModal__Overlay--after-open.overlay"));

            assertTrue(overlay.isEmpty());
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @ParameterizedTest
    @EnumSource(Browser.class)
    public void theMostImportantElementsAreVisible(Browser browser) {
        driver = Utils.getDriver(String.valueOf(browser));

        try {
            String[] elementsClasses = {
                    "parts__Img-sc-1cf5826f-1 iocZSK", // logo
                    "parts__Input-sc-60750d44-0 iFdVUS", // search bar
                    "sc-bgqQcB bZGFRv parts__StyledIcon-sc-b4eaed6d-3 ataiD", // log in
                    "sc-bgqQcB bZGFRv parts__StyledIcon-sc-b4eaed6d-3 ataiD", // cart
                    "parts__Container-sc-51f562cf-0 gTwHVo parts__Container-sc-685c9fb1-0 ExKSm", // categories
                    "parts__SlideImage-sc-50f2d72c-0 dALlGL", // ads
                    "parts__Container-sc-c2c41d57-0 hiEeoV parts__HomeSectionLayout-sc-e662adc3-0 ccWwEE", // recommended
                    "parts__Container-sc-c2c41d57-0 jDbNbb parts__HomeSectionLayout-sc-8e130060-0 gfMbat", // bestsellers
                    "parts__Container-sc-51f562cf-0 gTwHVo parts__FooterContainer-sc-8fd7aa6e-1 fayxpE" // footer
            };

            for (int i = 0; i < elementsClasses.length; i++) {
                elementsClasses[i] = ".".concat(elementsClasses[i].replace(" ", "."));
            }

            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            WebElement cookieAgreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".parts__ButtonWrapper-sc-6adb784e-0.parts__AcceptButton-sc-22bd9b2d-9.kXIGaP.jbQKAv")));
            cookieAgreeButton.click();

            Thread.sleep(2000);
            waitForPageLoad(driver);

            for (String elementsClass : elementsClasses) {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elementsClass)));

                assertTrue(element.isDisplayed());
            }
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @ParameterizedTest
    @EnumSource(Browser.class)
    public void searchWorks(Browser browser) {
        driver = Utils.getDriver(String.valueOf(browser));

        try {
            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

            WebElement cookieAgreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".parts__ButtonWrapper-sc-6adb784e-0.parts__AcceptButton-sc-22bd9b2d-9.kXIGaP.jbQKAv")));
            cookieAgreeButton.click();

            Thread.sleep(2000);

            WebElement searchBar = driver.findElement(By.cssSelector(".parts__Input-sc-60750d44-0.iFdVUS"));
            searchBar.sendKeys("ps5");
            searchBar.sendKeys(Keys.ENTER);

            Thread.sleep(5000);

            List<WebElement> resultsTitles = driver.findElements(By.cssSelector(".parts__Title-sc-1d28d-0.fKUIM.parts__Title-sc-6e280ffa-9.hqUuGB"));
            List<String> resultsTitlesStrings = new ArrayList<>();

            for (WebElement resultsTitle : resultsTitles) {
                resultsTitlesStrings.add(resultsTitle.getText());
            }

            for (String resultsTitlesString : resultsTitlesStrings) {
                assertTrue(resultsTitlesString.toLowerCase().replace(" ", "").contains("ps5")
                        || resultsTitlesString.toLowerCase().replace(" ", "").contains("playstation5")
                );
            }
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @ParameterizedTest
    @EnumSource(Browser.class)
    public void noResultsForIncorrectPhrase(Browser browser) {
        driver = Utils.getDriver(String.valueOf(browser));

        try {
            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

            WebElement cookieAgreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".parts__ButtonWrapper-sc-6adb784e-0.parts__AcceptButton-sc-22bd9b2d-9.kXIGaP.jbQKAv")));
            cookieAgreeButton.click();

            Thread.sleep(2000);

            WebElement searchBar = driver.findElement(By.cssSelector(".parts__Input-sc-60750d44-0.iFdVUS"));
            searchBar.sendKeys("efbefusgbfsufssgbef");
            searchBar.sendKeys(Keys.ENTER);

            Thread.sleep(2000);

            WebElement noItemsInfo = driver.findElement(By.cssSelector(".parts__Title-sc-5f6c3fdb-1.gGxjMd"));
            String noItemsInfoText = noItemsInfo.getText();

            assertTrue(noItemsInfo.isDisplayed());
            assertEquals("Nie znaleźliśmy wyników dla „efbefusgbfsufssgbef”", noItemsInfoText);
        } catch (Exception e) {
            fail("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @Test
    public void articleCanBeOpened() {
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
    public void loginPageCanBeOpened() {
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
    public void registerPageCanBeOpened() {
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
    public void cartCanBeOpened() {
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
    public void cantLoginWithBlankData() {
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
