package com.example.retrofit.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.retrofit.ApiCalls.DataManager;
import com.example.retrofit.Listeners.GetApiListener;
import com.example.retrofit.Listeners.MultiPartPostApiListener;
import com.example.retrofit.Listeners.PostApiListener;
import com.example.retrofit.Model.GetApiBean;
import com.example.retrofit.Model.MultipartPostApiBean;
import com.example.retrofit.Model.PostApiBean;
import com.example.retrofit.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView txtResponse;
    private Button btnGetMethod;
    private Button btnPostMethod;
    private ProgressBar pb;
    private Button btnMultiPartPostMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {

        txtResponse = (TextView) findViewById(R.id.txt_activity_main_response);
        btnGetMethod = (Button) findViewById(R.id.btn_activity_main_get_method);
        btnPostMethod = (Button) findViewById(R.id.btn_activity_main_post_method);
        btnMultiPartPostMethod = (Button) findViewById(R.id.btn_activity_main_multipartpost_method);

        pb = (ProgressBar) findViewById(R.id.pb_activity_main);

        btnGetMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //API USING RETROFIT FOR GET METHOD
                fetchGetApi();
            }
        });

        btnPostMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //API USING RETROFIT FOR POST METHOD
                performPostApi();
            }
        });

        btnMultiPartPostMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //API USING RETROFIT FOR MULTIPARTPOST METHOD
                performMultiPartPostApi();
            }
        });
    }

    private void fetchGetApi() {

        pb.setVisibility(View.VISIBLE);
        btnGetMethod.setEnabled(false);

        HashMap<String, String> urlParams = new HashMap<>();

        DataManager.fetchGetApi(urlParams, new GetApiListener() {

            @Override
            public void onLoadCompleted(GetApiBean getApiBean) {

                pb.setVisibility(View.GONE);
                btnGetMethod.setEnabled(true);

                txtResponse.setText("Response:" + getApiBean.toString());

                Log.i(TAG, "onLoadCompleted: BEAN " + new Gson().toJson(getApiBean));
            }

            @Override
            public void onLoadFailed(String error) {

                pb.setVisibility(View.GONE);
                btnGetMethod.setEnabled(true);

                txtResponse.setText(error);
            }
        });
    }

    private void performPostApi() {

        pb.setVisibility(View.VISIBLE);
        btnPostMethod.setEnabled(false);

        JSONObject postData = getPostApiJSObj();

        DataManager.performPostApi(postData, new PostApiListener() {

            @Override
            public void onLoadCompleted(PostApiBean postApiBean) {

                pb.setVisibility(View.GONE);
                btnPostMethod.setEnabled(true);

                txtResponse.setText("Response:" + postApiBean.toString());

                Log.i(TAG, "onLoadCompleted: BEAN " + new Gson().toJson(postApiBean));
            }

            @Override
            public void onLoadFailed(String error) {

                pb.setVisibility(View.GONE);
                btnPostMethod.setEnabled(true);

                txtResponse.setText(error);
            }
        });
    }

    private JSONObject getPostApiJSObj() {

        JSONObject postData = new JSONObject();

        /*try {
            postData.put("postdata", "");

        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        return postData;
    }

    private void performMultiPartPostApi() {

        pb.setVisibility(View.VISIBLE);
        btnMultiPartPostMethod.setEnabled(false);

        JSONObject postData = getMultiPartPostApiJSObj();

        List<String> fileList = getFileList();

        DataManager.performMultiPartPostApi(postData, fileList, new MultiPartPostApiListener() {

            @Override
            public void onLoadCompleted(MultipartPostApiBean multipartPostApiBean) {

                pb.setVisibility(View.GONE);
                btnMultiPartPostMethod.setEnabled(true);

                txtResponse.setText("Response:" + multipartPostApiBean.toString());

                Log.i(TAG, "onLoadCompleted: BEAN " + new Gson().toJson(multipartPostApiBean));
            }

            @Override
            public void onLoadFailed(String error) {

                pb.setVisibility(View.GONE);
                btnMultiPartPostMethod.setEnabled(true);
            }
        });
    }

    private JSONObject getMultiPartPostApiJSObj() {

        JSONObject postData = new JSONObject();

        /*try {
            postData.put("postdata", "");

        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        return postData;
    }

    private List<String> getFileList() {

        List<String> fileList = new ArrayList<>();

        /*if (displayPicImage != null && !displayPicImage.equals(""))
            fileList.add(displayPicImage);*/

        return fileList;
    }
}
