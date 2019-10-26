package com.mcs.controller;

import com.google.common.collect.Maps;
import com.mcs.entity.CarLink;
import com.mcs.service.CarLinkService;
import com.mcs.utils.CheckUtil;
import com.mcs.utils.Result;
import com.mcs.utils.SmsUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName RecycleController
 * @Description 回收单处理类
 * @Author szq
 * @Date 2019/7/8 15:40
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/api/carLink")
public class CarLinkController {
    private static final Logger log= LoggerFactory.getLogger(CarLinkController.class);
    private Map<String,String> smsMap= Maps.newHashMap();
    @Resource
    private CarLinkService carLinkService;

    /**
     * 绑定
     * @param carLink message
     * @return
     */
    @RequestMapping(value = "/bind", method = {RequestMethod.GET, RequestMethod.POST})
    public Result<Boolean> bind(CarLink carLink,String message) {
        log.info("bind:carNo="+carLink.getCarNo()+"mobilePhone="+carLink.getMobilePhone()+"message="+message);
        Result<Boolean> result = Result.createFailResult();
        if (!CheckUtil.testPhone(carLink.getMobilePhone())){
            result.setMsg("手机号格式不正确！");
            return result;
        }
        if (StringUtils.isBlank(message)||!message.equals(smsMap.get(carLink.getMobilePhone()))){
            result.setMsg("验证码校验失败");
            return result;
        }
        result=carLinkService.bind(carLink);
        smsMap.remove(carLink.getMobilePhone());
        return result;
    }

    /**
     * 解绑
     * @param carLink message
     * @return
     */
    @RequestMapping(value = "/unite", method = {RequestMethod.GET, RequestMethod.POST})
    public Result<Boolean>unite(CarLink carLink,String message) {
        log.info("unite:carNo="+carLink.getCarNo()+"mobilePhone="+carLink.getMobilePhone()+"message="+message);
        Result<Boolean> result = Result.createFailResult();
        if (!CheckUtil.testPhone(carLink.getMobilePhone())){
            result.setMsg("手机号格式不正确！");
            return result;
        }
        if (StringUtils.isBlank(message)||!message.equals(smsMap.get(carLink.getMobilePhone()))){
            result.setMsg("验证码校验失败");
            return result;
        }
        result=carLinkService.unite(carLink);
        smsMap.remove(carLink.getMobilePhone());
        return result;
    }

    /**
     * 发送短信验证码
     * @param mobilePhone
     * @return
     */
    @RequestMapping(value = "/sentMessage", method = {RequestMethod.GET, RequestMethod.POST})
    public Result<Boolean> sentMessage(String mobilePhone) {
        Result<Boolean>result=Result.createFailResult();
        String message= SmsUtil.getMsgCode();
        if (!CheckUtil.testPhone(mobilePhone)){
            result.setMsg("手机号格式不正确！");
            return result;
        }
        if (StringUtils.isNotBlank(mobilePhone)&& CheckUtil.testPhone(mobilePhone)){
            result=SmsUtil.sendSms(mobilePhone,message);
            if (result.isSuccess()){
                smsMap.put(mobilePhone,message);
            }
        }
        return result;
    }
    /**
     * 解绑
     * @param carNo
     * @return
     */
    @RequestMapping(value = "/getCarLinkInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public Result<CarLink>getCarLinkInfo(String carNo) {
        log.info("getCarLinkInfo:carNo="+carNo);
        Result<CarLink>result=Result.createFailResult();
        if (StringUtils.isBlank(carNo)){
            result.setMsg("车牌号不能为空");
            return  result;
        }
        return  carLinkService.getCatLinkInfo(carNo);

    }
}
