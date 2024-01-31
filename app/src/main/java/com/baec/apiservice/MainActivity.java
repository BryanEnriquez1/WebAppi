package com.baec.apiservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baec.apiservice.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    EditText et_num1,et_num2;

    TextView et_resul;
    Button bt_enviar;
    String a="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et_num1 = findViewById(R.id.txtNum1);
        et_num2 = findViewById(R.id.txtNum2);
        bt_enviar = findViewById(R.id.btnRes);
        et_resul = findViewById(R.id.txtResul);
        bt_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LeerWS();
                // Toast.makeText(MainActivity.this,"El resultado es:",Toast.LENGTH_LONG).show();

            }
        });

    }


    public void LeerWS(){

        String url="https://ejemplo2apimovil20240128220859.azurewebsites.net/api/Operaciones?a="+et_num1.getText()+"&b="+et_num2.getText();
        OkHttpClient client = new OkHttpClient();

        Request get = new Request.Builder()
                .url(url)
                .build();

        client.newCall(get).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {

                    ResponseBody responseBody = response.body();
                    if (response.isSuccessful()) {
                        a=responseBody.string();
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                et_resul.setText(a);
                            }
                        });



                    }else{

                        throw new IOException("Unexpected code " + response);
                    }





                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



    }
}