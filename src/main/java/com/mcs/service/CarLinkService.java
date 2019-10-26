package com.mcs.service;

import com.mcs.entity.CarLink;
import com.mcs.utils.Result;


/**
 * 回收单处理类
 */
public interface CarLinkService {
    /**
     * 绑定手机
     *
     * @param carLink
     * @return
     */
    Result<Boolean> bind(CarLink carLink);

    /**
     * 解绑
     *
     * @param carLink
     * @return
     */
    Result<Boolean> unite(CarLink carLink);

    /**
     * 查询绑定信息
     *
     * @param carNo
     * @return
     */
    Result<CarLink> getCatLinkInfo(String carNo);

    void axUnbindNumber();
}
