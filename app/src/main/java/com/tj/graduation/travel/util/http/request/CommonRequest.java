package com.tj.graduation.travel.util.http.request;

import android.util.Log;

import com.google.gson.Gson;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 描述:     接收请求参数，为我们生成Request对象
 */

public class CommonRequest {

    /**
     * 创建Get请求的Request
     *
     * @param url
     * @param params
     * @return 通过传入的参数返回一个Get类型的请求
     */
    public static Request createGetRequest(String url, RequestParams params) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");

        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder
                        .append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
        }

        return new Request.Builder().url(urlBuilder.substring(0, urlBuilder.length() - 1))
                .get().build();
    }

    /**
     * 创建Post请求的Request
     *
     * @param url
     * @param params
     * @return 返回一个创建好的Request对象
     */
    public static Request createPostRequest(String url, RequestParams params) {

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        String json = new Gson().toJson(params.urlParams);

        Log.d("request", json);
        RequestBody requestBody = FormBody.create(mediaType, json);

        //表单提交
//        FormBody.Builder mFromBodyBuilder = new FormBody.Builder();
//
//        if (params != null) {
//            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
//                //将请求参数逐一遍历添加到我们的请求构建类中
//                mFromBodyBuilder.add(entry.getKey(), entry.getValue());
//            }
//        }

        //通过请求构建类的build方法获取到真正的请求体对象
//        FormBody mFormBody = mFromBodyBuilder.build();
        return new Request.Builder().url(url).addHeader("Content-Type", "application/json").post(requestBody).build();
    }

}
