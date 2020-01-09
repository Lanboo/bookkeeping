package com.xych.bookkeeping.app.alipay;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.xych.bookkeeping.app.common.utils.InputUtils;
import com.xych.bookkeeping.dao.dto.AlipayRecordDTO;

public class AlipayAuto {
    private static final long intervalMillis = 500L;
    private WebDriver driver;
    private String userName = "";
    private String userPwd = "";

    public static void main(String[] args) {
        Date startDate = new Date();
        Date endDate = new Date();
        new AlipayAuto().grab(startDate, endDate);
    }

    public void grab(Date startDate, Date endDate) {
        driver = WebDriverFactory.newInstance();
        try {
            login("SAOMA");
            doGrab(startDate, endDate);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doGrab(Date startDate, Date endDate) {
        // 跳转
        driver.get(AlipayConstants.PATH_RECORD);
        // 设置查询时间类型：自定义时间
        Select timeRangSelect = new Select(driver.findElement(By.id("J-select-range")));
        timeRangSelect.selectByValue("customDate");
        // 
        DateTime tempDt = new DateTime(startDate.getTime()).withHourOfDay(0).withSecondOfMinute(0).withMillisOfSecond(0);
        DateTime endDateTime = new DateTime(endDate.getTime());
        for(; tempDt.compareTo(endDateTime) >= 0; tempDt = tempDt.plusDays(1)) {
            String dateStr = tempDt.toString("yyyy.MM.dd");
            selectRang(dateStr, dateStr);
            List<AlipayRecordDTO> dtoList = grabData();
            System.out.println(dtoList);
        }
    }

    /**
     * 抓取当前页的数据
     * @CreateDate 2020年1月9日下午3:52:01
     */
    private List<AlipayRecordDTO> grabData() {
        return null;
    }

    /**
     * 设置查询时间
     * @CreateDate 2020年1月9日下午3:50:06
     */
    private void selectRang(String startDate, String endDate) {
        // 设置开始时间
        WebElement beginDateEle = driver.findElement(By.id("beginDate"));
        InputUtils.set(beginDateEle, startDate);
        // 设置结束时间
        WebElement endDateEle = driver.findElement(By.id("endDate"));
        InputUtils.set(endDateEle, endDate);
    }

    /**
     * 登录：MIMA-密码账户,SAOMA-手机扫码
     * @CreateDate 2020年1月9日下午3:50:49
     */
    private void login(String loginType) throws InterruptedException {
        // 跳转至登陆界面
        driver.get(AlipayConstants.PATH_LOGIN);
        if("MIMA".equals(loginType)) {
            inputNamePwd();
            clickLoginBtn();
            try {
                while(driver.findElement(By.className("authcenter-body-login")) != null) {
                    inputNamePwd();
                    Thread.sleep(20000);//用于输入验证码 或者 扫码登陆
                }
            }
            catch(Exception e) {
                // 啥都不做
            }
        }
        else if("SAOMA".equals(loginType)) {
            try {
                while(driver.findElement(By.className("authcenter-body-login")) != null) {
                    Thread.sleep(5000);//用于输入验证码 或者 扫码登陆
                }
            }
            catch(Exception e) {
                // 啥都不做
            }
        }
    }

    /**
     * 点击登录按钮
     * @CreateDate 2020年1月9日下午3:50:27
     */
    private void clickLoginBtn() throws InterruptedException {
        // 登陆
        WebElement loginBtn = driver.findElement(By.id("J-login-btn"));
        loginBtn.click();
        Thread.sleep(1000);
    }

    /**
     * 输入用户名&密码
     * @CreateDate 2020年1月8日下午5:26:36
     */
    private void inputNamePwd() throws InterruptedException {
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
    }
}
