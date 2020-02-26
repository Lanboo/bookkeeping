package com.xych.bookkeeping.app.controller;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xych.bookkeeping.app.common.support.UserSupport;
import com.xych.bookkeeping.app.mapstruct.CategoryVOConverter;
import com.xych.bookkeeping.app.vo.CategoryVO;
import com.xych.bookkeeping.app.vo.base.PageVO;
import com.xych.bookkeeping.dao.dto.CategoryDTO;
import com.xych.bookkeeping.dao.service.CategoryServcie;
import com.xych.uid.UidGenerator;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private UidGenerator defaultUidGenerator;
    @Autowired
    private UserSupport userSupport;
    @Autowired
    private CategoryServcie categoryService;
    @Autowired
    private CategoryVOConverter voConverter;

    @PostMapping("/query")
    @ResponseBody
    public PageVO<CategoryVO> query(@Valid @RequestBody CategoryVO vo) {
        CategoryDTO temp = voConverter.toDto(vo);
        temp.setUserCode(userSupport.getUser().getUserCode());
        List<CategoryDTO> dtoList = this.categoryService.findList(temp);
        List<CategoryVO> voList = voConverter.toVoList(dtoList);
        List<CategoryVO> voTreeList = treeSort(voList);
        return new PageVO<>(1, 1, dtoList == null ? 0 : dtoList.size(), voTreeList);
    }

    @PostMapping("/query/list")
    @ResponseBody
    public List<CategoryVO> queryList(@Valid @RequestBody CategoryVO vo) {
        CategoryDTO temp = voConverter.toDto(vo);
        temp.setUserCode(userSupport.getUser().getUserCode());
        List<CategoryDTO> dtoList = this.categoryService.findList(temp);
        return voConverter.toVoList(dtoList);
    }

    @PostMapping("/save")
    @ResponseBody
    public void save(@Valid @RequestBody CategoryVO vo) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(defaultUidGenerator.getUID());
        dto.setUserCode(userSupport.getUser().getUserCode());
        dto.setCategoryName(vo.getCategoryName());
        dto.setParentId(Long.parseLong(vo.getParentId()));
        dto.setCrtTime(new Date());
        dto.setUptTime(dto.getCrtTime());
        this.categoryService.addOne(dto);
    }

    @PostMapping("/remove")
    @ResponseBody
    public void remove(@Valid @RequestBody List<Long> ids) {
        this.categoryService.deleteByIds(ids);
    }

    @PostMapping("/update")
    @ResponseBody
    public void update(@Valid @RequestBody CategoryVO vo) {
        CategoryDTO dto = voConverter.toDto(vo);
        dto.setUptTime(new Date());
        this.categoryService.update(dto);
    }

    private List<CategoryVO> treeSort(List<CategoryVO> voList) {
        List<CategoryVO> voTreeList = voList.stream().filter(vo -> Objects.isNull(vo.getParentId())).sorted(Comparator.comparing(CategoryVO::getId)).collect(Collectors.toList());
        if(CollectionUtils.isNotEmpty(voTreeList)) {
            for(CategoryVO vo : voTreeList) {
                subTreeSort(vo, voList);
            }
        }
        return voTreeList;
    }

    private void subTreeSort(final CategoryVO vo, List<CategoryVO> voList) {
        List<CategoryVO> voSubTreeList = voList.stream().filter(voTemp -> vo.getId().equals(voTemp.getParentId())).sorted(Comparator.comparing(CategoryVO::getId)).collect(Collectors.toList());
        if(CollectionUtils.isNotEmpty(voSubTreeList)) {
            vo.setChildren(voSubTreeList);
            for(CategoryVO subVo : voSubTreeList) {
                subTreeSort(subVo, voList);
            }
        }
    }
}
