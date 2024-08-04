package azamat;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import dev.failsafe.internal.util.Durations;

/**
 * GMail : los.pollos515225
 * LosPollosHermanosFamily1991@
 *
 */
public class EmailSender {
    private String name;
    private String email;
    private String date;
    private String time;
    private String size;
    private String specialRequests;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public String getSize() {
        return size;
    }


    public void setSize(String size) {
        this.size = size;
    }


    public String getSpecialRequests() {
        return specialRequests;
    }


    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }


    public void sendMessage() {
        String message = String.format("Hello, %s. Your reservation have been recorded on date : %s and time : %s. Reservation for : %s. Also your special requests will be taken into account : %s. Thank you for being with us, Los Pollos Hermanos Family.",
                name,
                date,
                time,
                size,
                specialRequests
                );
        System.setProperty("webdrive.chrome.driver", "selenium\\chrome.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.google.com/gmail/about/");

        WebElement webElement = webDriver.findElement(By.xpath("/html/body/header/div/div/div/a[2]"));
        webElement.click();

        waitThreeSec();
        waitThreeSec();

        WebElement webElement2 = webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/c-wiz/div/div[2]/div/div/div[1]/form/span/section/div/div/div[1]/div/div[1]/div/div[1]/input"));
        webElement2.sendKeys("los.pollos515225@gmail.com");

        WebElement webElement3 = webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/c-wiz/div/div[3]/div/div[1]/div/div/button/span"));
        webElement3.click();

        waitThreeSec();
        waitThreeSec();

        WebElement webElement4 = webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/c-wiz/div/div[2]/div/div/div/form/span/section[2]/div/div/div[1]/div[1]/div/div/div/div/div[1]/div/div[1]/input"));
        webElement4.sendKeys("LosPollosHermanosFamily1991@");

        WebElement webElement5 = webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/c-wiz/div/div[3]/div/div[1]/div/div/button/span"));
        webElement5.click();

        waitThreeSec();
        waitThreeSec();

        WebElement webElement6 = webDriver.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div/div"));
        webElement6.click();

        waitThreeSec();
        waitThreeSec();

        // WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(60));
        WebElement webElement7 = webDriver.findElement(By.cssSelector(".agP.aFw"));
        webElement7.sendKeys(email);
        
        WebElement webElement8 = webDriver.findElement(By.cssSelector(".Am.aiL.Al.editable.LW-avf.tS-tW"));

        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("arguments[0].innerText = '" + message + " " + Keys.ENTER + "test'", webElement8);

        WebElement webElement9 =  webDriver.findElement(By.cssSelector(".T-I.J-J5-Ji.aoO.v7.T-I-atl.L3"));
        webElement9.click();
    }
    

    public static void waitThreeSec(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
