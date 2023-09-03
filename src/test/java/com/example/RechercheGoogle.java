package com.example;


import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.GooglePage;
import utils.WebElts;



public class RechercheGoogle {
  private WebDriver driver;
  private GooglePage googlePage;
  private StringBuffer verificationErrors = new StringBuffer();
  private WebElts wElts = new WebElts();
  

  @BeforeEach
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver",
        "src/drivers/chromedriver.exe");

    driver = new ChromeDriver();
    googlePage = new GooglePage(driver);
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

  @Test
  public void recherche() throws Exception {
    googlePage.rechercheGoogle("YouTube");
    googlePage.submitRechercheGoogle();;
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
