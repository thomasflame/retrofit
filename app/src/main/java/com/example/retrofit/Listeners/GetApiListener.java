package com.example.retrofit.Listeners;

import com.example.retrofit.Model.GetApiBean;

public interface GetApiListener {

    void onLoadCompleted(GetApiBean getApiBean);

    void onLoadFailed(String error);
}
