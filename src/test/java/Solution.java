import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Solution {
    private static ChromeDriver driver;
    private static ChromeDriver driver1;
    private String expected_result = "13";

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Yelena\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void hw_1() throws InterruptedException{
        driver.get("https://dgotlieb.github.io/WebCalculator/");
        WebElement button7 = driver.findElement(By.id("seven"));
        System.out.println(button7.getSize());
        System.out.println(driver.findElement(By.id("six")).isDisplayed());
        driver.findElement(By.id("six")).click();
        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("seven")).click();
        driver.findElement(By.id("equal")).click();

        String answer = driver.findElement(By.id("screen")).getText();
        Assert.assertEquals(expected_result,answer);
    }
    @Test
    public void hw_2() {
        String test_url = "https://www.facebook.com";
        Assert.assertEquals(driver.getCurrentUrl(),test_url);
    }
    @Test
    public void hw_3() {
        String website_title = "Some Test Title for Question #3";
        driver.navigate().refresh();
        Assert.assertEquals(website_title,driver.getTitle());
    }
    @Test
    public void hw_4() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-exceptions");
        driver1 = new ChromeDriver(options);
        driver1.get("https://gist.github.com/leahakb");
    }
    @Test
    public void hw_5() {
        driver.navigate().to("https://dgotlieb.github.io/Actions/");
        WebElement element = driver.findElement(By.id("div1"));
        File screenShotFile = element.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShotFile, new File("screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //double click
        Actions myAction = new Actions(driver);
        myAction.doubleClick(driver.findElement(By.xpath("//p[@ondblclick='doubleClickFunction()']")));
        myAction.perform();
        expected_result = "You double clicked";
        System.out.println(driver.findElement(By.id("demo")).getText());
        String actual = driver.findElement(By.id("demo")).getText();
        Assert.assertEquals(expected_result,actual);
        //hover
        Actions hoverAct = new Actions(driver);
        WebElement myElement = driver.findElement(By.id("close"));
        hoverAct.moveToElement(myElement).perform();
        //Select
        Select mySelect = new Select(driver.findElement(By.name("food")));
        mySelect.selectByValue("pizza");
        mySelect.selectByValue("pasta");
        //Upload file
        driver.findElement(By.name("pic")).sendKeys("C:\\Users\\Yelena\\Downloads\\SeleniumMaven2\\SeleniumMaven\\screenshot.png");
        //scroll down to "click me" button
        //scroll to element
        WebElement scrollElement = driver.findElement(By.id("clickMe"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scrollElement);
        //scroll to location
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(250,350)");
    }
    @Test
    public void hw_6(){
        driver.navigate().to("https://dgotlieb.github.io/Controllers/");
        //radio button element select
        WebElement radioSelect = driver.findElement(By.xpath("//input[@value='Cheese']"));
        radioSelect.click();
        //dropdown element select
        Select selectOption = new Select(driver.findElement(By.name("dropdownmenu")));
        selectOption.selectByValue("Cheese");
        //print all options in dropdown menu
        List<WebElement> allOptions = selectOption.getAllSelectedOptions();
        for (WebElement option:allOptions){
            System.out.println(option.getText());
        }
    }
    @AfterClass
    public static void afterClass(){
        driver1.quit();
    }
}
