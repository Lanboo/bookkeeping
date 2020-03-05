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

import com.xych.bookkeeping.app.mapstruct.DicVOConverter;
import com.xych.bookkeeping.app.vo.DicVO;
import com.xych.bookkeeping.app.vo.base.PageVO;
import com.xych.bookkeeping.dao.base.dto.Page;
import com.xych.bookkeeping.dao.dto.DicDTO;
import com.xych.bookkeeping.dao.service.DicServcie;
import com.xych.uid.UidGenerator;

@Controller
@RequestMapping("dic")
public class DicController {
    @Autowired
    private DicServcie service;
    @Autowired
    private DicVOConverter voConverter;
    @Autowired
    private UidGenerator defaultUidGenerator;

    @PostMapping("/query")
    @ResponseBody
    public PageVO<DicVO> query(@Valid @RequestBody DicVO vo) {
        Page<DicDTO> dtoPage = this.service.findPage(voConverter.toDto(vo));
        return new PageVO<>(dtoPage.getPageNum(), dtoPage.getPageSize(), dtoPage.getTotal(), voConverter.toVoList(dtoPage.getData()));
    }

    @PostMapping("/save")
    @ResponseBody
    public void save(@Valid @RequestBody DicVO vo) {
        DicDTO dto = voConverter.toDto(vo);
        dto.setId(defaultUidGenerator.getUID());
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
    public void update(@Valid @RequestBody DicVO vo) {
        DicDTO dto = voConverter.toDto(vo);
        dto.setUptTime(new Date());
        this.service.update(dto);
    }
}
