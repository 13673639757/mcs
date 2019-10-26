package com.mcs.dao;

import com.mcs.entity.SecretPhone;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by szq on 2019/7/8.
 */
@Repository
public interface SecretPhoneDao {

    List<SecretPhone> getAllPhone();

}
