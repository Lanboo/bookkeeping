package com.xych.bookkeeping.app.common.utils;

import org.openqa.selenium.WebElement;

public class InputUtils {
    public static void set(WebElement inputEle, String str) {
        inputEle.clear();
        inputEle.sendKeys(str);
    }

    public static void setByChar(WebElement inputEle, String str) {
        setByChar(inputEle, str, 500);
    }

    public static void setByChar(WebElement inputEle, String str, long intervalMillis) {
        try {
            inputEle.clear();
            for(int i = 0, len = str.length(); i < len; i++) {//输入用户名，每隔500ms输入一个字符，过快输入会登录失败
                inputEle.sendKeys(str.charAt(i) + "");
                Thread.sleep(intervalMillis);
            }
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
