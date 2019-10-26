package com.mcs.dao;

import com.mcs.entity.CarLink;
import org.springframework.stereotype.Repository;

/**
 * Created by szq on 2019/7/8.
 */
@Repository
public interface CarLinkDao {

    Integer insertCarLink(CarLink carLink);

    void deleteCarLink(CarLink carLink);

    CarLink getCatLinkInfo(String carNo,String mobilePhone);
}
