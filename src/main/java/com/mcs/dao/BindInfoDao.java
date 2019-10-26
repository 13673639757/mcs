package com.mcs.dao;

import com.mcs.entity.BindInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by szq on 2019/7/8.
 */
@Repository
public interface BindInfoDao {

    Integer insertBindInfo(BindInfo bindInfo);

    void deleteBindInfo(String bindDate);

    BindInfo getBindInfo(String cardNo);

    List<BindInfo>getAllTimeOutBindInfo(String bindDate);

}
