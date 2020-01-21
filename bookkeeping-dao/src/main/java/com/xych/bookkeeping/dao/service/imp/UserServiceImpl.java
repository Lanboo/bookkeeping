package com.xych.bookkeeping.dao.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.dto.UserDTO;
import com.xych.bookkeeping.dao.entity.User;
import com.xych.bookkeeping.dao.mapper.UserMapper;
import com.xych.bookkeeping.dao.mapstruct.UserConverter;
import com.xych.bookkeeping.dao.service.UserServcie;

import tk.mybatis.mapper.entity.Example;

@Service("UserServcie")
public class UserServiceImpl implements UserServcie {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private UserConverter converter;

    @Override
    public UserDTO findOne(UserDTO dto) {
        return converter.toDto(mapper.selectOne(converter.toEntity(dto)));
    }

    @Override
    public List<UserDTO> findList(UserDTO dto) {
        return converter.toDtoList(mapper.select(converter.toEntity(dto)));
    }

    @Override
    public Integer addOne(UserDTO dto) {
        return mapper.insert(converter.toEntity(dto));
    }

    @Override
    public Integer addList(List<UserDTO> dtos) {
        return mapper.insertList(converter.toEntityList(dtos));
    }

    @Override
    public Integer update(UserDTO dto) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.updateByExampleSelective(converter.toEntity(dto), example);
    }

    @Override
    public Integer delete(UserDTO dto) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.deleteByExample(example);
    }
}
