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
import com.xych.bookkeeping.app.mapstruct.MemberVOConverter;
import com.xych.bookkeeping.app.vo.MemberVO;
import com.xych.bookkeeping.app.vo.base.PageVO;
import com.xych.bookkeeping.dao.base.dto.Page;
import com.xych.bookkeeping.dao.dto.MemberDTO;
import com.xych.bookkeeping.dao.service.MemberServcie;
import com.xych.uid.UidGenerator;

@Controller
@RequestMapping("member")
public class MemberController {
    @Autowired
    private UidGenerator defaultUidGenerator;
    @Autowired
    private UserSupport userSupport;
    @Autowired
    private MemberServcie memberService;
    @Autowired
    private MemberVOConverter voConverter;

    @PostMapping("/query")
    @ResponseBody
    public PageVO<MemberVO> query(@Valid @RequestBody MemberVO vo) {
        Page<MemberDTO> dtoPage = this.memberService.findPage(voConverter.toDto(vo));
        return new PageVO<>(dtoPage.getPageNum(), dtoPage.getPageSize(), dtoPage.getTotal(), voConverter.toVoList(dtoPage.getData()));
    }

    @PostMapping("/save")
    @ResponseBody
    public void save(@Valid @RequestBody MemberVO vo) {
        MemberDTO dto = new MemberDTO();
        dto.setId(defaultUidGenerator.getUID());
        dto.setUserCode(userSupport.getUser().getUserCode());
        dto.setMemberName(vo.getMemberName());
        dto.setCrtTime(new Date());
        dto.setUptTime(dto.getCrtTime());
        this.memberService.addOne(dto);
    }

    @PostMapping("/remove")
    @ResponseBody
    public void remove(@Valid @RequestBody List<Long> ids) {
        this.memberService.deleteByIds(ids);
    }

    @PostMapping("/update")
    @ResponseBody
    public void update(@Valid @RequestBody MemberVO vo) {
        MemberDTO dto = voConverter.toDto(vo);
        dto.setUptTime(new Date());
        this.memberService.update(dto);
    }
}
