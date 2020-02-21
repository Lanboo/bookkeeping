package com.xych.bookkeeping.app.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xych.bookkeeping.app.mapstruct.AccountVOConverter;
import com.xych.bookkeeping.app.vo.account.AccountSaveVO;
import com.xych.bookkeeping.app.vo.account.AccountUpdateVO;
import com.xych.bookkeeping.app.vo.account.AccountVO;
import com.xych.bookkeeping.app.vo.base.PageVO;
import com.xych.bookkeeping.dao.base.dto.Page;
import com.xych.bookkeeping.dao.dto.AccountDTO;
import com.xych.bookkeeping.dao.service.AccountServcie;

/**
 * 资产账户
 * @CreateDate 2020年2月21日下午4:47:19
 */
@Controller
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AccountServcie service;
    @Autowired
    private AccountVOConverter voConverter;

    @PostMapping("/query")
    @ResponseBody
    public PageVO<AccountVO> query(@Valid @RequestBody AccountVO vo) {
        Page<AccountDTO> dtoPage = this.service.findPage(voConverter.toDto(vo));
        return new PageVO<>(dtoPage.getPageNum(), dtoPage.getPageSize(), dtoPage.getTotal(), voConverter.toVoList(dtoPage.getData()));
    }

    @PostMapping("/save")
    @ResponseBody
    public void save(@Valid @RequestBody AccountSaveVO vo) {
        AccountDTO dto = voConverter.toDto(vo);
        dto.setCrtTime(new Date());
        dto.setUptTime(dto.getCrtTime());
        this.service.addOne(dto);
    }

    @PostMapping("/remove")
    @ResponseBody
    public void remove(@Valid @RequestBody List<Long> ids) {
        this.service.deleteByIds(ids);
    }

    @PostMapping("/update")
    @ResponseBody
    public void update(@Valid @RequestBody AccountUpdateVO vo) {
        AccountDTO dto = voConverter.toDto(vo);
        dto.setUptTime(new Date());
        this.service.update(dto);
    }
}
