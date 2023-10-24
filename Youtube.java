package Domaci.Nedelja1.Sreda;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class D_01_Mladen_Borisavljevic_ITBG17003 {

    //Otici na YouTube i pustiti jednu pesmu po Vasem izboru.

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.youtube.com/");

        WebElement searchBox = driver.findElement(By.name("search_query"));
        searchBox.sendKeys("Joy division love will tear us apart");

        WebElement searchButton= driver.findElement(By.id("search-icon-legacy"));
        searchButton.click();

        Thread.sleep(2000);
        WebElement song = driver.findElement(By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer[2]/div[1]/div/div[1]/div/h3/a/yt-formatted-string"));
        song.click();

        driver.quit();
    }

}
