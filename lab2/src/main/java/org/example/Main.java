package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");

        // Privacy policy prompt - reject all
        driver.findElement(By.id("W0wltc")).click();

        // Search Selenium in Google
        driver.findElement(By.name("q")).sendKeys("Selenium");
        driver.findElement(By.name("q")).submit();

        driver.quit();
    }
}