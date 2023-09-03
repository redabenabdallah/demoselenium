package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.DragUtilitaire;
import pageObjects.GooglePage;
import utils.WebElts;

public class DragAndDrop {
    private WebDriver driver;
    private GooglePage googlePage;
    private ManageAlerts manageAlerts;
    private DragUtilitaire du = new DragUtilitaire();
    private WebElts wElts = new WebElts();

    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeEach
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                "src\\drivers\\chromedriver.exe");

        driver = new ChromeDriver();
        googlePage = new GooglePage(driver);
        manageAlerts = new ManageAlerts(driver);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get("https://www.google.com/");
        Thread.sleep(2000);
        if (wElts.isElementPresent(driver, By.xpath("//*[@id=\"W0wltc\"]/div"))) {
            driver.findElement(By.xpath("//*[@id=\"W0wltc\"]/div")).click();
        }

    }

    @org.junit.jupiter.api.Test
    public void dragAndDrop() throws Exception {
        googlePage.rechercheGoogle("drag and drop example");
        googlePage.submitRechercheGoogle();
        String pathElm = "//*[text()='HTML Drag and Drop API']";
        googlePage.chooseLien(pathElm);
        manageAlerts.clickBtnAlert("accept-choices");

        String idFrom = "drag1";
        String idTo = "div2";
        assertEquals(driver.findElement(By.id(idTo)).findElements(By.tagName("img")).size(), 0);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        du.dragAndDrop(idFrom, idTo, driver, wait);
        assertEquals(driver.findElement(By.id(idTo)).findElements(By.tagName("img")).size(), 1);
    }

    @AfterEach
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

}
