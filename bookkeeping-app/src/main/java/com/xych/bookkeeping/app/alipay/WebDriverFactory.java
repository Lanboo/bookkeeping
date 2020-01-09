package com.xych.bookkeeping.app.alipay;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    private WebDriverFactory() {
    }

    public static WebDriver newInstance() {
        System.setProperty("webdriver.chrome.driver", AlipayConstants.CHROME_DRIVER_PATH);
        ChromeOptions chromeOptions = new ChromeOptions();
        // 利用 Chrome DevTools 协议: https://blog.csdn.net/freeking101/article/details/94601986
        // chromeOptions.setExperimentalOption("debuggerAddress", "127.0.0.1:9999");
        WebDriver driver = new ChromeDriver(chromeOptions);
        return driver;
    }
}
