package com.xych.bookkeeping.app.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xych.bookkeeping.app.alipay.AlipayAutoService;

@Controller
@RequestMapping("alipay")
public class AlipayAutoController {
    @Autowired
    private AlipayAutoService alipayAutoService;

    @PostMapping("/grab")
    @ResponseBody
    public String grab(Date startDate, Date endDate, Boolean login) {
        alipayAutoService.grab(startDate, endDate, login);
        return "SUCCESS";
    }
}
