package com.dem.weixin.web.controller;

import com.dem.weixin.utils.SHA1Utils;
import com.dem.weixin.utils.WeixinConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

/**
 * Created by DEM on 2016/12/31.
 */
@Controller
public class WeixinController {
    
    
    /**
     * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        timestamp	时间戳
         nonce	随机数
        echostr	随机字符串
 
     1）将token、timestamp、nonce三个参数进行字典序排序
 
     2）将三个参数字符串拼接成一个字符串进行sha1加密
 
     3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     * @param signature
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(String signature,String timestamp,String nonce,String echostr){
        String[] arr = new String[]{WeixinConstant.TOKEN,timestamp,nonce};
        Arrays.sort(arr);
        StringBuilder temp = new StringBuilder(80);
        for (String s : arr) {
            temp.append(s);
        }
        if(SHA1Utils.getSha1(temp.toString()).equals(signature)){
            return echostr;
        }
        return null;
    }
}
