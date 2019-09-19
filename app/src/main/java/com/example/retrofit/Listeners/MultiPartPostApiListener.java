package com.example.retrofit.Listeners;

import com.example.retrofit.Model.MultipartPostApiBean;

public interface MultiPartPostApiListener {

    void onLoadCompleted(MultipartPostApiBean multipartPostApiBean);

    void onLoadFailed(String error);
}
