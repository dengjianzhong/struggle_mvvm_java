package com.struggle.base.http.interceptor;

import android.text.TextUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/5 11:43
 * @Description 封装公共参数
 */
public class CommonInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder requestBuilder = request.newBuilder();
        RequestBody requestBody = request.body();
        if (requestBody instanceof FormBody) {
            FormBody.Builder newFormBody = new FormBody.Builder();
            FormBody oidFormBody = (FormBody) request.body();
            for (int i = 0; i < oidFormBody.size(); i++) {
                newFormBody.addEncoded(oidFormBody.encodedName(i), oidFormBody.encodedValue(i));
            }

            /**为请求体为(FormBody)配置公共参数*/
            {
                String token = "";
                if (!TextUtils.isEmpty(token)) {
                    newFormBody.add("token", token);
                }
                requestBuilder.method(request.method(), newFormBody.build());
            }
        } else if (requestBody instanceof MultipartBody) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);

            MultipartBody oldBodyMultipart = (MultipartBody) request.body();
            List<MultipartBody.Part> oldPartList = oldBodyMultipart.parts();
            for (MultipartBody.Part part : oldPartList) {
                builder.addPart(part);
            }

            /**为请求体为(MultipartBody)配置公共参数*/
            {
                String token = "";
                if (!TextUtils.isEmpty(token)) {
                    builder.addFormDataPart("token", token);
                }
                requestBuilder.method(request.method(), builder.build());
            }
        }

        return chain.proceed(requestBuilder.build());
    }
}