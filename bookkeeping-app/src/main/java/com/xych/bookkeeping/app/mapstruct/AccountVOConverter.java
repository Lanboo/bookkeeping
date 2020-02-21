package com.xych.bookkeeping.app.mapstruct;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

import com.xych.bookkeeping.app.vo.account.AccountSaveVO;
import com.xych.bookkeeping.app.vo.account.AccountUpdateVO;
import com.xych.bookkeeping.app.vo.account.AccountVO;
import com.xych.bookkeeping.dao.dto.AccountDTO;

@Mapper(componentModel = "spring")
public interface AccountVOConverter {

    AccountVO toVo(AccountDTO dto);

    @InheritConfiguration
    List<AccountVO> toVoList(List<AccountDTO> dtoList);

    AccountDTO toDto(AccountVO vo);

    AccountDTO toDto(AccountUpdateVO vo);

    AccountDTO toDto(AccountSaveVO vo);

    @InheritConfiguration
    List<AccountDTO> toDtoList(List<AccountVO> voList);
}
