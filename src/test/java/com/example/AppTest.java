package com.example;

import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.reporters.JUnitTestReporter;

/**
 * Unit test for simple App.
 */
public class AppTest extends JUnitTestReporter{
  private WebDriver driver;

  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver",
        "C:\\Users\\Admin\\driverSelenium\\chromedriver-win64\\chromedriver.exe");

    // Creating an object of ChromeDriver
    driver = new ChromeDriver();
    driver.manage().window().maximize();

    // Deleting all the cookies
    driver.manage().deleteAllCookies();

    // Specifiying pageLoadTimeout and Implicit wait
    driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    // launching the specified URL
    driver.get("https://www.google.com/");
    Thread.sleep(2000);
    if(this.isElementPresent(By.xpath("//*[@id=\"W0wltc\"]/div"))){
      driver.findElement(By.xpath("//*[@id=\"W0wltc\"]/div")).click();
    }
  
  }

  @Test
  public void testSelenium() throws Exception {
    // Locating the elements using name locator for the text box
        WebDriverWait wait = new WebDriverWait(driver,30);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
    driver.findElement(By.name("q")).sendKeys("YouTube");

    // name locator for google search button

   driver.findElement(By.name("btnK")).click();

  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alert.getText();
    } finally {
      acceptNextAlert = true;
    }
  }
}
