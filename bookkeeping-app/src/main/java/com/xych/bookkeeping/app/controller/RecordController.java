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

import com.xych.bookkeeping.app.common.support.UserSupport;
import com.xych.bookkeeping.app.mapstruct.RecordVOConverter;
import com.xych.bookkeeping.app.vo.RecordVO;
import com.xych.bookkeeping.app.vo.base.PageVO;
import com.xych.bookkeeping.dao.base.dto.Page;
import com.xych.bookkeeping.dao.dto.RecordDTO;
import com.xych.bookkeeping.dao.service.RecordServcie;
import com.xych.uid.UidGenerator;

@Controller
@RequestMapping("record")
public class RecordController {
    @Autowired
    private RecordServcie service;
    @Autowired
    private RecordVOConverter voConverter;
    @Autowired
    private UidGenerator defaultUidGenerator;
    @Autowired
    private UserSupport userSupport;

    @PostMapping("/query")
    @ResponseBody
    public PageVO<RecordVO> query(@Valid @RequestBody RecordVO vo) {
        vo.setUserCode(userSupport.getUser().getUserCode());
        Page<RecordDTO> dtoPage = this.service.findPage(voConverter.toDto(vo));
        return new PageVO<>(dtoPage.getPageNum(), dtoPage.getPageSize(), dtoPage.getTotal(), voConverter.toVoList(dtoPage.getData()));
    }

    @PostMapping("/save")
    @ResponseBody
    public void save(@Valid @RequestBody RecordVO vo) {
        RecordDTO dto = voConverter.toDto(vo);
        dto.setId(defaultUidGenerator.getUID());
        dto.setUserCode(userSupport.getUser().getUserCode());
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
    public void update(@Valid @RequestBody RecordVO vo) {
        RecordDTO dto = voConverter.toDto(vo);
        dto.setUptTime(new Date());
        this.service.update(dto);
    }
}
