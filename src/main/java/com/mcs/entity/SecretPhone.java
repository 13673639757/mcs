package com.mcs.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description 账号表
 * @Author szq
 * @Date 2019/7/8 9:03
 * @Version 1.0
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class SecretPhone implements Serializable {

    private static final long serialVersionUID = -3589932668079687958L;

    //主键id
    private Long id;
    //隐私号
    private String phoneNumber;

}
