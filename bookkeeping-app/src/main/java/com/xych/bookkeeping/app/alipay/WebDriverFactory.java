package com.xych.bookkeeping.app.alipay;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    /**
     * 启动端口号
     */
    private static final String PORT = "9999";
    /**
     * chrome的用户配置文件，防止污染自己的chrome配置
     */
    private static final String CHROME_USER_DATA_DIR = "D:\\data\\logs\\selenum\\chrome\\AutomationProfile";

    private WebDriverFactory() {
    }

    public static WebDriver newInstance(boolean cmd) {
        try {
            if(cmd) {
                // 利用cmd，加载chrome.exe，指定端口号、用户目录
                String cmdStr = AlipayConstants.CHROME_INSTALLATION_PATH + " --remote-debugging-port=" + PORT + " --user-data-dir=\"" + CHROME_USER_DATA_DIR + "\"";
                Runtime.getRuntime().exec(cmdStr);
            }
            System.setProperty("webdriver.chrome.driver", AlipayConstants.CHROME_DRIVER_PATH);
            ChromeOptions chromeOptions = new ChromeOptions();
            // 利用 Chrome DevTools 协议: https://blog.csdn.net/freeking101/article/details/94601986
            chromeOptions.setExperimentalOption("debuggerAddress", "127.0.0.1:" + PORT);
            WebDriver driver = new ChromeDriver(chromeOptions);
            return driver;
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}