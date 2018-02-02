package com.jude.beamdemo.glide;

import java.io.InputStream;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2018/2/1.
 */

public class RetrofitUtils {
    /**
     * 获取OkHttpClient
     * 设置允许HTTPS
     * */
    public static OkHttpClient getOkHttpClient(InputStream... certificates)
    {
        SSLSocketFactory sslSocketFactory = HttpsUtils.getSslSocketFactory(certificates, null, null);
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder = builder.sslSocketFactory(sslSocketFactory);
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session)
            {
                return true;
            }
        });
        return builder.build();
    }
}
