package com.jesse.sample.networkchat;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jesse.sample.networkchat.util.CommonUtil;
import com.jesse.sample.networkchat.util.PermissionHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionHelper.checkPermission(this, Manifest.permission.RECORD_AUDIO);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }

    public void login(View view) throws JSONException {
        String usernamevalue = username.getText().toString();
        String passwordValue = password.getText().toString();
        if (CommonUtil.isStringNotEmpty(usernamevalue) && CommonUtil.isStringNotEmpty(passwordValue)) {
            OkHttpClient client = new OkHttpClient();
            JSONObject json = new JSONObject();
            json.putOpt("username", usernamevalue);
            json.putOpt("password", passwordValue);
//            String json = "{username:"+usernamevalue+",password:"+passwordValue+"}";
            RequestBody requestBody = RequestBody.create(CommonUtil.JSONMEDIATYPE, json.toString());
            Request request = new Request.Builder()
                    .url("http://10.5.34.92:3000/login")
                    .post(requestBody)
                    .build();
            final Activity context = this;
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Toast.makeText(context, "登陆失败", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.i(TAG, "onResponse: "+response.body().string());
                }
            });

        } else {
            Toast.makeText(this, "用户名密码为空", Toast.LENGTH_LONG).show();
        }

    }
}