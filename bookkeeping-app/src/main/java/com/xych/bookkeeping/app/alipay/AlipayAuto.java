package com.xych.bookkeeping.app.alipay;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.xych.bookkeeping.app.common.utils.InputUtils;

public class AlipayAuto {
    private static final long intervalMillis = 500L;
    private WebDriver driver;
    private String userName = "@qq.com";
    private String userPwd = "";

    public static void main(String[] args) {
        new AlipayAuto().download();
    }

    public void download() {
        try {
            init();
            login();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void login() throws InterruptedException {
        // 跳转至登陆界面
        driver.get(AlipayConstants.PATH_LOGIN);
        // 账户密码登陆
        WebElement loginTabsEle = driver.findElement(By.id("J-loginMethod-tabs"));
        WebElement loginTabEle = loginTabsEle.findElements(By.tagName("li")).get(1);
        loginTabEle.click();
        // 输入用户名
        WebElement userNameEle = driver.findElement(By.id("J-input-user"));
        InputUtils.set(userNameEle, userName);
        // 睡眠
        Thread.sleep(intervalMillis);
        // 输入密码
        WebElement userPwdEle = driver.findElement(By.id("password_rsainput"));
        InputUtils.set(userPwdEle, userPwd);
        // 登陆
        WebElement loginBtn = driver.findElement(By.id("J-login-btn"));
        loginBtn.click();
        //等待20s，用于人工输入验证码
        Thread.sleep(20000);//用于输入验证码
        // 跳转
        driver.get(AlipayConstants.PATH_RECORD);
    }

    private void init() throws InterruptedException {
        // -------------启动火狐浏览器--------------------
        //        System.setProperty("webdriver.firefox.bin", "D:\\firfox\\firefox.exe");
        //        WebDriver driver = new FirefoxDriver();
        // -------------启动Chrome浏览器------------------
        System.setProperty("webdriver.chrome.driver", AlipayConstants.CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
    }
}
