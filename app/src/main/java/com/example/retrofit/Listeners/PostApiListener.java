package com.example.retrofit.Listeners;

import com.example.retrofit.Model.PostApiBean;

public interface PostApiListener {

    void onLoadCompleted(PostApiBean postApiBean);

    void onLoadFailed(String error);
}
