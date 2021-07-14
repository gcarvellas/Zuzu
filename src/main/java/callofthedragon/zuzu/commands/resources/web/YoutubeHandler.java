package callofthedragon.zuzu.commands.resources.web;
;
import callofthedragon.zuzu.config.ConfigParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YoutubeHandler {

    //Xpaths
    private static final String INIT_DOWNLOAD_BTN_XPATH = "//button[@id='process_mp3' and @class='btn btn-success btn-mp3']";
    private static final String FINAL_DOWNLOAD_BTN_XPATH = "//a[@class='btn btn-success btn-file' and @type='button']";

    //Variables
    private static final String YTD_URL = "https://www.y2mate.com/youtube-mp3/";
    private static OperaOptions options;

    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement finalDownloadButton;

    public YoutubeHandler(String unformattedURL){
            driver = new OperaDriver(options);
            driver.get(setURL(unformattedURL));
            wait = new WebDriverWait(driver, 10);
    }

    private String setURL(String unformattedURL){
        return YTD_URL + unformattedURL.substring(unformattedURL.indexOf("v=") + 2);
    }

    public static void setOptions(){
        options = new OperaOptions();
        options.setBinary(ConfigParser.getOperaBinary());
    }


    public String convertToMP3DirectDownload(){
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(INIT_DOWNLOAD_BTN_XPATH))).click();
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FINAL_DOWNLOAD_BTN_XPATH)));
            finalDownloadButton = driver.findElement(By.xpath(FINAL_DOWNLOAD_BTN_XPATH));
            return finalDownloadButton.getAttribute("href").toString();
    }

    public void shutdown(){
        driver.close();
    }
}
