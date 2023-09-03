package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "q")
    WebElement inputRecherche;

    @FindBy(name = "btnK")
    WebElement btnRecherche;

    @FindBy(xpath = "//*[text()='HTML Drag and Drop API']")
    WebElement clickLienToPageDragAndDrop;

    public GooglePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public void rechercheGoogle(String maRecherche) {
        wait.until(ExpectedConditions.visibilityOf(inputRecherche));
        inputRecherche.clear();
        inputRecherche.sendKeys(maRecherche);
    }

    public void submitRechercheGoogle() {
        btnRecherche.click();
    }

    public void chooseLien(String lienRechercheResultat){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(lienRechercheResultat)));
        driver.findElement(By.xpath(lienRechercheResultat)).click();
    }

}
