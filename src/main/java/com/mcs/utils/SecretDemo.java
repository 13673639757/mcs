package com.mcs.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @ClassName SecretDemo
 * @Description TODO
 * @Author szq
 * @Date 2019/9/29 9:21
 * @Version 1.0
 */
public class SecretDemo {

    //产品名称:云通信隐私保护产品,开发者无需替换
    static final String product = "Dyplsapi";
    //产品域名,开发者无需替换
    static final String domain = "dyplsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAI5A2lvuDKgy8z";
    static final String accessKeySecret = "Wi8y802xXEmHD4aBIeVO175TfBklbd";
    /**
     * 订购关系解绑示例(解绑接口不区分AXB、AXN)
     * @return
     * @throws ClientException
     */
    public static BuySecretNoResponse buySecretNo(String poolKey, Long specId, String city, String secretNo)
            throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        BuySecretNoRequest request = new BuySecretNoRequest();
        //必填:对应的号池Key
        request.setPoolKey(poolKey);
        //必填:1:170/171虚商号码 2:运营商号码 3:95号码
        request.setSpecId(specId);
        //必填:号码归属地
        request.setCity(city);
        //可选-指定X号码购买，支持模糊匹配，例如secretNo=138,则购买138号段号码
        request.setSecretNo(secretNo);

        BuySecretNoResponse response = acsClient.getAcsResponse(request);

        return response;
    }

    public static void main(String[] args) throws ClientException, InterruptedException {

        //号码购买
        String poolKey = "FC100000076710104";
        Long specId = 3L;
        String city = "杭州";
        String secretNo = "";
        BuySecretNoResponse response = buySecretNo(poolKey, specId, city, secretNo);
        System.out.println("buySecretNo接口返回的结果----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("SecretNo=" + response.getSecretBuyInfoDTO().getSecretNo());

//        //号码释放
//        String poolKey2 = "";
//        String secretNo2 = "";
//        ReleaseSecretNoResponse response2 = releaseSecretNo(poolKey2, secretNo2);
//        System.out.println("releaseSecretNo接口返回的结果----------------");
//        System.out.println("Code=" + response2.getCode());
//        System.out.println("Message=" + response2.getMessage());
//        System.out.println("RequestId=" + response2.getRequestId());

    }
}
