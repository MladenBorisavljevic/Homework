package Domaci.Nedelja1.Nedelja;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class D_02_Mladen_Borisavljevic_ITBG17003_Anotacije {

    /*
   Ulogujte se na demoqa(https://demoqa.com/ -> Book Store Application) preko cookies-a, dodati dve knjige
   na svoj nalog, zatim se izlogovati brisanjem cookies-a.
   Ulogovati se ponovo preko log-in forme i potvrditi da se knjige i dalje nalaze na nalogu.
    */

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demoqa.com");
    }

    @BeforeMethod
    public void login() {
        List<WebElement> card = driver.findElements(By.className("card-body"));
        for (int i = 0; i < card.size(); i++) {
            if (card.get(i).getText().equals("Book Store Application")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", card.get(i));
                card.get(i).click();
                break;
            }
        }
        Cookie cookie1 = new Cookie("userID", "c81f2db9-b8f2-457c-94e9-4c0bcc9a8eac");
        Cookie cookie2 = new Cookie("userName", "Djamla");
        Cookie cookie3 = new Cookie("expires", "2023-10-08T13%3A20%3A11.106Z");
        Cookie cookie4 = new Cookie("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6IkRqYW1sYSIsInBhc3N3b3JkIjoiUHJvbWFqYXViaXZhMTIzISIsImlhdCI6MTY5NjE2NjQxMX0.JXsnmdERLZFsVeIOjGc84X8biKrWrAjH6gfKehBGwdE");

        driver.manage().addCookie(cookie1);
        driver.manage().addCookie(cookie2);
        driver.manage().addCookie(cookie3);
        driver.manage().addCookie(cookie4);

        driver.navigate().refresh();
    }

    @Test
    public void verifyThatBooksAreInProfileAfterLogout() throws InterruptedException {
        WebElement firstBook = driver.findElement(By.linkText("Git Pocket Guide"));
        firstBook.click();
        WebElement addingBook = driver.findElement(By.cssSelector(".btn.btn-primary"));
        addingBook.click();
        driver.navigate().back();

        WebElement secondBook = driver.findElement(By.linkText("Designing Evolvable Web APIs with ASP.NET"));
        secondBook.click();
        WebElement addingBook1 = driver.findElement(By.cssSelector(".btn.btn-primary"));
        addingBook1.click();

       driver.manage().deleteAllCookies();

        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();

        WebElement usernameField = driver.findElement(By.id("userName"));
        usernameField.sendKeys("Djamla");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Promajaubiva123!");
        loginButton.click();

        Thread.sleep(2000);
        driver.navigate().to("https://demoqa.com/profile");

        WebElement firstBook1 = driver.findElement(By.linkText("Git Pocket Guide"));
        WebElement secondBook2 = driver.findElement(By.linkText("Designing Evolvable Web APIs with ASP.NET"));

        Assert.assertTrue(firstBook1.isDisplayed());
        Assert.assertTrue(secondBook2.isDisplayed());
    }

    @AfterMethod
    public void cookiesAfter() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void quit() {
        driver.quit();
    }
}
