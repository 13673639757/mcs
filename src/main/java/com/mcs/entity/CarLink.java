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
public class CarLink  implements Serializable {

    private static final long serialVersionUID = -3589932668079687958L;

    //主键id
    private Long id;
    //车牌号
    private String carNo;
    //手机号
    private String mobilePhone;
    //状态
    private String status;

    private String createDate;

    private String modifiedDate;

    private String encMobilePhone;

}
