package com.example.retrofit.ApiCalls;

import com.example.retrofit.Model.GetApiBean;
import com.example.retrofit.Model.MultipartPostApiBean;
import com.example.retrofit.Model.PostApiBean;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    //PASS THE INPUT PARAMETERS IF YOU HAVE ANY IN GET, POST, MULTIPARTPOST RESPECTIVELY

    @GET("/get")
    Call<GetApiBean> fetchGetApi();

    @POST("/post")
    Call<PostApiBean> performPostApi();

    @POST("/multipartpost")
    Call<MultipartPostApiBean> performMultiPartPostApi();

    /*@Multipart
    @POST("/multipartpost")
    Call<MultipartPostApiBean> performMultiPartPostApi(@Part MultipartBody.Part filePart);*/

}
