package com.example.nftmarket.response;


import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import lombok.Data;

@Data
public class RestBean<T> {
    int code;
    T data;
    String Message;

    //一定要静止外部进行调用，放置劫持攻击
    private RestBean(int code, T data, String message) {
        this.code = code;
        this.data = data;
        Message = message;
    }
    public static <T> RestBean<T> success(T data){
        return new RestBean<>(200,data,"后端获取参数成功");
    }
    public static <T> RestBean<T> failure(int code,String message) {
        return new RestBean<>(code,null,message);
    }
    public static<T> RestBean<T> failure(int code){
        return failure(code,"请求失败");
    }
    //转换JSON传递给前端进行操作
    public String asJsonString(){
        return JSONObject.toJSONString(this, JSONWriter.Feature.WriteNulls);
    }
}
