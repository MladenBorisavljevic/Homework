package Domaci.Nedelja1.Sreda;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


public class D_02_Mladen_Borisavljevic_ITBG17003 {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demoqa.com/text-box");

        String inputName = "Mladen Borisavljevic";
        String inputEmail = "89mladen@gmail.com";
        String inputCurrentAddress = "Adresa za testiranje BB";
        String inputPermanentAddress = "Adresa za testiranje BB";

        WebElement fullNameField = driver.findElement(By.id("userName"));
        fullNameField.sendKeys(inputName);

        WebElement emailField = driver.findElement(By.id("userEmail"));
        emailField.sendKeys(inputEmail);

        WebElement currentAddressField = driver.findElement(By.id("currentAddress"));
        currentAddressField.sendKeys(inputCurrentAddress);

        WebElement permanentAddressField = driver.findElement(By.id("permanentAddress"));
        permanentAddressField.sendKeys(inputPermanentAddress);


        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        Thread.sleep(2000);
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        String expectedURL = "https://demoqa.com/text-box";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        WebElement output = driver.findElement(By.id("output"));
        Assert.assertTrue(output.isDisplayed());






    }

}
