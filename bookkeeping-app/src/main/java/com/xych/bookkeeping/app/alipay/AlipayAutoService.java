package com.xych.bookkeeping.app.alipay;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.app.common.support.UserSupport;
import com.xych.bookkeeping.app.common.utils.InputUtils;
import com.xych.bookkeeping.dao.dto.AlipayRecordDTO;
import com.xych.bookkeeping.dao.service.AlipayRecordServcie;
import com.xych.uid.UidGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AlipayAutoService {
    private static final long intervalMillis = 500L;
    private WebDriver driver;
    private String userName = "";
    private String userPwd = "";
    @Autowired
    private AlipayRecordServcie alipayRecordServcie;
    @Autowired
    private UidGenerator defaultUidGenerator;
    @Autowired
    private UserSupport userSupport;

    public static void main(String[] args) {
        Date startDate = new Date();
        Date endDate = new Date();
        new AlipayAutoService().grab(startDate, endDate, true, true);
    }

    /**
     * @param startDate 开始日期
     * @param endDate 结束日期（包含）
     * @param newOpen 是否重新打开一个Chrome应用
     * @param login 是否重新登录
     * @CreateDate 2020年1月15日上午11:49:25
     */
    public void grab(Date startDate, Date endDate, Boolean newOpen, Boolean login) {
        login = newOpen ? true : login;
        driver = WebDriverFactory.newInstance(newOpen);
        try {
            if(login) {
                // 扫描登录
                login("SAOMA");
            }
            openRecordPage();
            doGrab(startDate, endDate);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void doGrab(Date startDate, Date endDate) throws Exception {
        // 设置查询时间类型：自定义时间
        WebElement timeRangSelectEle = driver.findElements(By.cssSelector("#J-datetime-select>a")).get(0);
        timeRangSelectEle.click();
        WebElement timeRangSelectDivEle = driver.findElement(By.cssSelector("div[data-widget-cid=widget-4]"));
        WebElement customDateItemEle = timeRangSelectDivEle.findElement(By.cssSelector("li[data-value=customDate]"));
        customDateItemEle.click();
        // 
        DateTime tempDt = new DateTime(startDate.getTime()).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
        DateTime endDateTime = new DateTime(endDate.getTime());
        for(; tempDt.compareTo(endDateTime) <= 0; tempDt = tempDt.plusDays(1)) {
            String dateStr = tempDt.toString("yyyy.MM.dd");
            log.info("抓取[{}]的数据:START", dateStr);
            selectRangAndSearch(dateStr, dateStr);
            do {
                List<AlipayRecordDTO> dtoList = grabDataList();
                log.info("抓取[{}]的数据{}条", dateStr, dtoList.size());
                alipayRecordServcie.addList(dtoList);
            }
            while(hasNextPageAndClick());
            log.info("抓取[{}]的数据:END", dateStr);
        }
    }

    /**
     * 是否存在下一页，存在的话跳转到下一页
     * @CreateDate 2020年1月14日上午10:47:49
     */
    private boolean hasNextPageAndClick() {
        boolean b = false;
        try {
            WebElement nextPageEle = driver.findElement(By.cssSelector("a.page-next[seed=pageLink-pageNextT1]"));
            if(nextPageEle != null) {
                b = true;
                nextPageEle.click();
                Thread.sleep(1000);
            }
        }
        catch(Exception e) {
            b = false;
        }
        return b;
    }

    /**
     * 抓取当前页的数据
     * @CreateDate 2020年1月9日下午3:52:01
     */
    private List<AlipayRecordDTO> grabDataList() throws Exception {
        List<AlipayRecordDTO> dtoList = new ArrayList<>();
        WebElement tableEle = driver.findElement(By.id("tradeRecordsIndex"));
        List<WebElement> trEles = tableEle.findElements(By.className("J-item"));
        for(WebElement trEle : trEles) {
            AlipayRecordDTO dto = grabData(trEle);
            // log.info("{}", dto);
            Thread.sleep(500);
            dtoList.add(dto);
        }
        return dtoList;
    }

    private AlipayRecordDTO grabData(WebElement trEle) throws Exception {
        AlipayRecordDTO dto = new AlipayRecordDTO();
        dto.setId(defaultUidGenerator.getUID());
        dto.setUserCode(userSupport.getUser().getUserCode());
        dto.setConsumeTime(grabConsumeTime(trEle));
        dto.setMemo(grabMemo(trEle));
        dto.setConsumeTitle(grabConsumeTitle(trEle));
        String[] tradeStrs = grabTrade(trEle);
        if(tradeStrs.length > 1) {
            dto.setTradeNo(tradeStrs[0]);
            dto.setTradeId(tradeStrs[1]);
        }
        else {
            dto.setTradeId(tradeStrs[0]);
        }
        dto.setOther(grabOther(trEle));
        dto.setStatus(garbStatus(trEle));
        grabDetailPage(dto, trEle);
        String amountStr = grabAmount(trEle);
        if(amountStr.startsWith("-") || amountStr.startsWith("+")) {
            dto.setAmount(new BigDecimal(amountStr.substring(1).trim()).multiply(BigDecimal.valueOf(100)));
            dto.setFundFlow(amountStr.charAt(0) == '-' ? -1 : 1);
        }
        else {
            dto.setAmount(new BigDecimal(amountStr));
            // 资金流向最后判断
            dto.setFundFlow(analysisFlow(dto));
        }
        dto.setCrtTime(new Date());
        dto.setUptTime(dto.getCrtTime());
        dto.setOperator("system");
        return dto;
    }

    /**
     * 根据已有信息分析资金流向
     * @date 2020年1月11日下午9:35:01
     */
    private Integer analysisFlow(AlipayRecordDTO dto) {
        return null;
    }

    /**
     * 抓取金额
     * @CreateDate 2020年1月10日上午10:09:40
     */
    private String grabAmount(WebElement trEle) {
        String amountStr = trEle.findElement(By.cssSelector(".amount > .amount-pay")).getText().trim();
        return amountStr;
    }

    /**
     * 抓取支付/收款的账户
     * @param dto
     * @CreateDate 2020年1月10日上午11:27:16
     */
    private void grabDetailPage(AlipayRecordDTO dto, WebElement trEle) throws InterruptedException {
        // 当前页的Handle
        String recordPageHandle = driver.getWindowHandle();
        // 获取超链接对象
        WebElement titleAEle = trEle.findElement(By.cssSelector(".name > .consume-title > a"));
        // 点击超链接
        titleAEle.click();
        Set<String> handleSet = driver.getWindowHandles();
        String detailPageHandle = null;
        for(String handle : handleSet) {
            if(!recordPageHandle.equals(handle)) {
                detailPageHandle = handle;
            }
        }
        // 定位到新窗口k
        WebDriver detailDriver = driver.switchTo().window(detailPageHandle);
        WebElement tableEle = detailDriver.findElement(By.className("fund-table"));
        List<WebElement> tdEleList = tableEle.findElements(By.className("detail-list"));
        if(tdEleList.size() == 1) {
            String fundTool = tdEleList.get(0).findElement(By.className("fundTool")).getText().trim();
            dto.setFundTool(fundTool);
        }
        else {
            String fundToolFrom = null;
            try {
                fundToolFrom = tdEleList.get(0).findElement(By.cssSelector(".fundTool > a")).getText().trim();
            }
            catch(Exception e) {
                fundToolFrom = tdEleList.get(0).findElement(By.cssSelector(".fundTool")).getText().trim();
            }
            dto.setFundToolFrom(fundToolFrom);
            String fundTool = tdEleList.get(1).findElement(By.className("fundTool")).getText().trim();
            dto.setFundTool(fundTool);
        }
        Thread.sleep(500);
        detailDriver.close();
        driver.switchTo().window(recordPageHandle);
    }

    /**
     * 抓取状态
     * @CreateDate 2020年1月10日上午10:21:09
     */
    private String garbStatus(WebElement trEle) {
        String status = trEle.findElement(By.cssSelector(".status > p:first-child")).getText().trim();
        return status;
    }

    /**
     * 抓取对方名称
     * @CreateDate 2020年1月9日下午6:05:46
     */
    private String grabOther(WebElement trEle) {
        String other = trEle.findElement(By.cssSelector(".other > p.name")).getText().trim();
        return other;
    }

    /**
     * 抓取：商家订单号|交易号
     * @CreateDate 2020年1月9日下午5:50:54
     */
    private String[] grabTrade(WebElement trEle) {
        String[] strs;
        String tradeStr = trEle.findElement(By.cssSelector(".tradeNo > p")).getText().trim();
        if(tradeStr.indexOf("|") > 0) {
            String[] tempStrs = tradeStr.split("\\|");
            strs = new String[2];
            strs[0] = tempStrs[0].substring(tempStrs[0].indexOf(':') + 1).trim();
            strs[1] = tempStrs[1].substring(tempStrs[1].indexOf(':') + 1).trim();
        }
        else {
            strs = new String[1];
            strs[0] = tradeStr.substring(tradeStr.indexOf(':') + 1);
        }
        return strs;
    }

    /**
     * 抓取标题
     * @CreateDate 2020年1月9日下午5:43:35
     */
    private String grabConsumeTitle(WebElement trEle) {
        String title = trEle.findElement(By.cssSelector(".name > .consume-title > a")).getText().trim();
        return title;
    }

    /**
     * 抓取备注信息
     * @Author WeiXiaowei
     * @CreateDate 2020年1月17日上午11:05:46
     */
    private String grabMemo(WebElement trEle) {
        WebElement tdEle = trEle.findElement(By.cssSelector("td.memo"));
        if(StringUtils.isBlank(tdEle.getText())) {
            return null;
        }
        return tdEle.findElement(By.cssSelector("#J-memo-cnt-4 > div > p.memo-info")).getText().trim();
    }

    /**
     * 抓取交易时间
     * @throws ParseException
     * @CreateDate 2020年1月9日下午5:37:43
     */
    private Date grabConsumeTime(WebElement trEle) throws ParseException {
        String titleHref = trEle.findElement(By.cssSelector(".name > .consume-title > a")).getAttribute("href").trim();
        String timeStr = titleHref.substring(titleHref.lastIndexOf("=") + 1).trim();
        Date date = DateUtils.parseDate(timeStr, "yyyyMMddHHmmss");
        return date;
    }

    /**
     * 设置查询时间
     * @CreateDate 2020年1月9日下午3:50:06
     */
    private void selectRangAndSearch(String startDate, String endDate) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // 设置开始时间
        WebElement beginDateEle = driver.findElement(By.id("beginDate"));
        js.executeScript("arguments[0].setAttribute('value', arguments[1]);", beginDateEle, startDate);
        // 设置结束时间
        WebElement endDateEle = driver.findElement(By.id("endDate"));
        js.executeScript("arguments[0].setAttribute('value', arguments[1]);", endDateEle, endDate);
        // 点击搜索
        driver.findElement(By.id("J-set-query-form")).click();
    }

    /**
     * 打开账单界面
     * @CreateDate 2020年1月10日下午3:46:33
     */
    private void openRecordPage() throws InterruptedException {
        // 跳转
        driver.get(AlipayConstants.PATH_RECORD);
        Thread.sleep(2000);//用于输入验证码 或者 扫码登陆
        // 再次扫码
        try {
            while(driver.findElement(By.id("risk_qrcode_cnt")) != null) {
                Thread.sleep(5000);//用于输入验证码 或者 扫码登陆
            }
        }
        catch(Exception e) {
            // 啥都不做
        }
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
