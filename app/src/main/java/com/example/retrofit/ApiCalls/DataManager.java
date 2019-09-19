package com.example.retrofit.ApiCalls;


import android.util.Log;

import com.example.retrofit.App.App;
import com.example.retrofit.Listeners.GetApiListener;
import com.example.retrofit.Listeners.MultiPartPostApiListener;
import com.example.retrofit.Listeners.PostApiListener;
import com.example.retrofit.Model.GetApiBean;
import com.example.retrofit.Model.MultipartPostApiBean;
import com.example.retrofit.Model.PostApiBean;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManager {

    private static final String TAG = "DATAMANAGER";
    private static Api api;
    private CompositeDisposable mCompositeDisposable;

    public static Api getAPI() {
        if (api == null) {
            api = ApiClient.getClient().create(Api.class);
        }
        return api;
    }

    public static void fetchGetApi(HashMap<String, String> urlParams, final GetApiListener getApiListener) {

        //RETROFIT GET METHOD

        Call<GetApiBean> call = getAPI().fetchGetApi();
        call.enqueue(new Callback<GetApiBean>() {
            @Override
            public void onResponse(Call<GetApiBean> call, Response<GetApiBean> response) {

                GetApiBean bean = response.body();

                Log.i(TAG, "onResponse: >>>>>>>>>>>>>>>>>> " + new Gson().toJson(bean));

                if (response == null || response.body() == null)
                    getApiListener.onLoadFailed("Unable to connect to the server");
                else {
                    getApiListener.onLoadCompleted(bean);
                }
            }

            @Override
            public void onFailure(Call<GetApiBean> call, Throwable t) {

                Log.i(TAG, "onResponse: <<<<<<<<<<<<<<<<<< " + t.getCause());

            }
        });
    }

    public static void performPostApi(JSONObject postData, final PostApiListener postApiListener) {

        //RETROFIT POST METHOD

        Call<PostApiBean> call = getAPI().performPostApi();
        call.enqueue(new Callback<PostApiBean>() {
            @Override
            public void onResponse(Call<PostApiBean> call, Response<PostApiBean> response) {

                PostApiBean bean = response.body();

                Log.i(TAG, "onResponse: >>>>>>>>>>>>>>>>>> " + new Gson().toJson(bean));

                if (response == null || response.body() == null)
                    postApiListener.onLoadFailed("Unable to connect to the server");
                else {
                    postApiListener.onLoadCompleted(bean);
                }
            }

            @Override
            public void onFailure(Call<PostApiBean> call, Throwable t) {

                Log.i(TAG, "onResponse: <<<<<<<<<<<<<<<<<< " + t.getCause());

            }
        });

    }

    public static void performMultiPartPostApi(JSONObject postData, List<String> fileList, final MultiPartPostApiListener multiPartPostApiListener) {

        //RETROFIT MULTIPARTPOST METHOD

        File file = null;
        MultipartBody.Part filePart = null;

        if (fileList.size() != 0) {
            file = new File(fileList.get(0));
            filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        }

        //PASS THE FILEPART IN PERFORMMULTIPARTPOST API

        Call<MultipartPostApiBean> call = getAPI().performMultiPartPostApi();
        call.enqueue(new Callback<MultipartPostApiBean>() {
            @Override
            public void onResponse(Call<MultipartPostApiBean> call, Response<MultipartPostApiBean> response) {

                MultipartPostApiBean bean = response.body();

                Log.i(TAG, "onResponse: >>>>>>>>>>>>>>>>>> " + new Gson().toJson(bean));

                if (response == null || response.body() == null)
                    multiPartPostApiListener.onLoadFailed("Unable to connect to the server");
                else {
                    multiPartPostApiListener.onLoadCompleted(bean);
                }
            }

            @Override
            public void onFailure(Call<MultipartPostApiBean> call, Throwable t) {

                multiPartPostApiListener.onLoadFailed("Unable to connect to the server");

                Log.i(TAG, "onResponse: <<<<<<<<<<<<<<<<<< " + t.getCause());

            }
        });
    }

    /*public static void performRX(HashMap<String, String> urlParams, final GetApiListener getApiListener) {

        Observable<GetApiBean> call = getAPI().getCoinData();
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetApiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetApiBean getApiBean) {

                        Log.i(TAG, "onNext: RESULT " + new Gson().toJson(getApiBean));
                        getApiListener.onLoadCompleted(getApiBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }*/
}
