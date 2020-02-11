package com.xych.bookkeeping.dao.dto;

import java.util.Date;

import com.xych.bookkeeping.dao.base.dto.BasePageDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BasePageDTO {
    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 用户代码
     */
    private String userCode;
    /**
     * 用户密码
     */
    private String userPwd;
    /**
     * 创建时间
     */
    private Date crtTime;
    /**
     * 修改时间
     */
    private Date uptTime;
}
