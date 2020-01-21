package com.xych.bookkeeping.dao.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.dto.AccountDTO;
import com.xych.bookkeeping.dao.entity.Account;
import com.xych.bookkeeping.dao.mapper.AccountMapper;
import com.xych.bookkeeping.dao.mapstruct.AccountConverter;
import com.xych.bookkeeping.dao.service.AccountServcie;

import tk.mybatis.mapper.entity.Example;

@Service("accountServcie")
public class AccountServiceImpl implements AccountServcie {
    @Autowired
    private AccountMapper mapper;
    @Autowired
    private AccountConverter converter;

    @Override
    public AccountDTO findOne(AccountDTO dto) {
        return converter.toDto(mapper.selectOne(converter.toEntity(dto)));
    }

    @Override
    public List<AccountDTO> findList(AccountDTO dto) {
        return converter.toDtoList(mapper.select(converter.toEntity(dto)));
    }

    @Override
    public Integer addOne(AccountDTO dto) {
        return mapper.insert(converter.toEntity(dto));
    }

    @Override
    public Integer addList(List<AccountDTO> dtos) {
        return mapper.insertList(converter.toEntityList(dtos));
    }

    @Override
    public Integer update(AccountDTO dto) {
        Example example = new Example(Account.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.updateByExampleSelective(converter.toEntity(dto), example);
    }

    @Override
    public Integer delete(AccountDTO dto) {
        Example example = new Example(Account.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.deleteByExample(example);
    }
}
