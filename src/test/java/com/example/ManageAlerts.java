package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManageAlerts {

        private WebDriver driver;
    private WebDriverWait wait;

        public ManageAlerts(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }
    
    public void clickBtnAlert(String idBtn){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(idBtn)));
        driver.findElement(By.id(idBtn)).click();
    }
}
