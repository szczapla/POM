package com.github.sz.czapla.pomautomat.tests;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class firstPOMTest {

    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    private void setUp() {
        //New instance of driver
        driver = new FirefoxDriver();
    }

    @BeforeMethod(alwaysRun = true)
    public void openHomePage(){
        driver.get("http://www.amazon.com");
    }

    @AfterClass(alwaysRun = true)
    private void tearDown() {
        //Quit driver
        driver.quit();
    }

    @Test
    public void testAddingItemToCart() throws InterruptedException {
        //Select 'Books' from search category dropdown
        new Select(driver.findElement(By.id("searchDropdownBox"))).
                selectByVisibleText("Books");

        //Enter search key: 'Selenium'
        driver.findElement(By.id("twotabsearchtextbox"))
                .sendKeys("Selenium");

        //Click 'Go' button
        driver.findElement(By.xpath("//*[@value='Go']"))
                .click();

        //Click the first search result item title
        WebElement firstItemTitleElement = driver.findElement(By.className("s-access-title"));
        String firstItemTitle = firstItemTitleElement.getText();
        firstItemTitleElement.click();

        System.out.println("\n" + firstItemTitle + "\n");

        //Verify product title
        assert (driver.findElement(By.id("productTitle"))
                .getText()
                .equals(firstItemTitle));

        //Click 'Add to cart' button
        driver.findElement(By.id("add-to-cart-button"))
                .click();

        //Verify confirmation text appears
       /* assert (driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div/div/div/div[3]/div/div/div/div/div[1]/span/span[1]"))
                .getText()
                .equals("Cart subtotal (1 item)"));
        System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div/div/div/div[3]/div/div/div/div/div[1]/span/span[1]"))
                .getText());*/

       //Navigate to 'Cart' page
        driver.findElement(By.id("nav-cart"))
                .click();
        //Wait for popup
        WebElement element = (new WebDriverWait(driver, 5)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[@id='activeCartViewForm']/div[2]/div/div[4]/div/div/div/div/div[2]/ul/li/span/a/span")));
        element.click();

        //Verify item is displayed on Shopping Cart list
        /*assert (driver.findElement(By.className("a-list-item"))
                .getText()
                .equals(firstItemTitle));*/

        WebElement cartBookTitleElement = driver.findElement(By.xpath("//form[@id='activeCartViewForm']/div[2]/div/div[4]/div/div/div/div/div[2]/ul/li/span/a/span"));

        String cartBookTitle = cartBookTitleElement
                .getText();
        System.out.println(cartBookTitle);
        Assert
                .assertEquals(firstItemTitle, cartBookTitle);
    }
}


