package com.example.testauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testauth.model.User;
import com.example.testauth.remote.ApiClient;
import com.example.testauth.remote.ApiService;
import com.example.testauth.remote.BaseApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    EditText i_username, i_password;
    Button btn_l;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        i_username = findViewById(R.id.ed_username);
        i_password = findViewById(R.id.ed_password);

        btn_l = findViewById(R.id.b_login);



        btn_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String username = i_username.getText().toString();
                String password = i_password.getText().toString();

                goLogin(username, password);

            }
        });

    }

    private void goLogin(final String username, final String password) {

        ApiService apiService;

        apiService = BaseApi.getDataService();

        Call<ResponseBody> call = apiService.goLogin(username, password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.isSuccessful()){
                    try {
                        JSONArray obj = new JSONArray(response.body().string());
                        String nama = obj.getJSONObject(0).getString("username");
                        String level = obj.getJSONObject(0).getString("level");
                        Toast.makeText(MainActivity.this, nama, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, SuccessLogin.class);
                        intent.putExtra("T_USERNAME", nama);
                        intent.putExtra("T_LEVEL", level);
                        startActivity(intent);

                        finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(MainActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("ERROR_login", t.getMessage());
                Toast.makeText(MainActivity.this, "Fail Request", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
