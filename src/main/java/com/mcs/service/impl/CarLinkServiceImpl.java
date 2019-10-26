package com.mcs.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mcs.dao.BindInfoDao;
import com.mcs.dao.CarLinkDao;
import com.mcs.dao.SecretPhoneDao;
import com.mcs.entity.BindInfo;
import com.mcs.entity.CarLink;
import com.mcs.entity.SecretPhone;
import com.mcs.enums.StatusEnum;
import com.mcs.service.AxService;
import com.mcs.service.CarLinkService;
import com.mcs.utils.Result;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName CarLinkServiceImpl
 * @Description TODO
 * @Author szq
 * @Date 2019/9/27 9:42
 * @Version 1.0
 */
@Service("carLinkService")
public class CarLinkServiceImpl implements CarLinkService {
    @Resource
    private CarLinkDao carLinkDao;
    @Resource
    private BindInfoDao bindInfoDao;
    @Resource
    private SecretPhoneDao secretPhoneDao;
    @Resource
    private AxService axService;
    /**
     * 绑定手机
     *
     * @param carLink
     * @return
     */
    @Override
    public Result<Boolean> bind(CarLink carLink) {
        Result<Boolean>result=Result.createFailResult();
        if (carLinkDao.getCatLinkInfo(carLink.getCarNo(),null)!=null ){
            result.setMsg("绑定失败:该车牌号已绑定过手机号！");
            return result;
        }
        if (carLinkDao.getCatLinkInfo(null,carLink.getMobilePhone())!=null ){
            result.setMsg("绑定失败:该手机号已被使用！");
            return result;
        }
        carLink.setStatus(StatusEnum.ENABLE.getCode());
        Integer flag=carLinkDao.insertCarLink(carLink);
        if (flag==0){
            return result;
        }
        result.value(true);
        return result;
    }

    /**
     * 解绑
     *
     * @param carLink
     * @return
     */
    @Override
    public Result<Boolean> unite(CarLink carLink) {
        Result<Boolean>result=Result.createFailResult();
        if (carLinkDao.getCatLinkInfo(carLink.getCarNo(),carLink.getMobilePhone())==null ){
            result.setMsg("解绑失败:查不到该车牌对应该手机号的绑定记录！");
            return result;
        }
        carLinkDao.deleteCarLink(carLink);
        result.value(true);
        return result;
    }

    /**
     * 查询绑定信息
     *
     * @param carNo
     * @return
     */
    @Override
    public synchronized Result<CarLink> getCatLinkInfo(String carNo) {
        Result<CarLink>result=Result.createFailResult();
        CarLink carLink=carLinkDao.getCatLinkInfo(carNo,null);
        if (carLink==null){
            result.setMsg("该车牌暂未绑定");
            return result;
        }
        BindInfo bindInfo=bindInfoDao.getBindInfo(carNo);
        if (bindInfo!=null){
            carLink.setEncMobilePhone(bindInfo.getSecretPhone());
        }else {
            List<SecretPhone>list=secretPhoneDao.getAllPhone();
            if (CollectionUtils.isEmpty(list)){
                result.setMsg("暂无可用隐私号，请稍后再试");
                return result;
            }
            Collections.shuffle(list);
            String res=axService.axBindNumber(carLink.getMobilePhone(),list.get(0).getPhoneNumber(),"0");
            JSONObject json=JSONObject.parseObject(res);
            if (!json.getString("resultcode").equals("0")){
                result.setMsg(json.getString("resultdesc"));
                return result;
            }
            BindInfo bindInfo1=new BindInfo();
            bindInfo1.setCarNo(carLink.getCarNo());
            bindInfo1.setMobilePhone(carLink.getMobilePhone());
            bindInfo1.setSecretPhone(list.get(0).getPhoneNumber());
            bindInfo1.setStatus(StatusEnum.ENABLE.getCode());
            bindInfo1.setBindDate(String.format("%010d", System.currentTimeMillis()/1000));
            carLink.setEncMobilePhone(list.get(0).getPhoneNumber());
            bindInfoDao.insertBindInfo(bindInfo1);
        }
        result.value(carLink);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void axUnbindNumber() {
        String bindDate=String.format("%010d", (System.currentTimeMillis()-600000)/1000);
        List<BindInfo>bindInfoList=bindInfoDao.getAllTimeOutBindInfo(bindDate);
        if (CollectionUtils.isNotEmpty(bindInfoList)){

        }
    }
}
