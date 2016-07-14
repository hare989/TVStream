package com.example.sinan.tvstream;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.sinan.tvstream.Helpers.HttpHelper;
import com.example.sinan.tvstream.Utils.PrefsUtil;

import org.json.JSONException;
import org.json.JSONObject;

import flexjson.JSON;

public class LoginActivity extends AppCompatActivity implements Button.OnClickListener{
    private String TAG = "LoginActivity";
    private JSONObject response;
    private AppMenager app = AppMenager.getInstance();
    private EditText etxtPassword;
    private EditText etxtUsername;
    private Button btnLogin;
    private ProgressBar progress;
    private JSONObject loginData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        app.setCurrentActivity(this);

        etxtPassword = (EditText) findViewById(R.id.etxtPassword);
        etxtUsername = (EditText) findViewById(R.id.etxtUsername);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        progress = (ProgressBar) findViewById(R.id.progressBarLogin);

        if(app.getIdentify()!=null){
            Log.e("LoginActivity", "SubscriberId"+app.getIdentify().getSubscriberId());
            Log.e("Login url", app.getLoginUrl());

        }

    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    @Override
    public void onClick(View v) {
        Log.e("onClick",v.toString());
        if(v.getId()==R.id.btnLogin){

            showHideProgressbar();

            String username = etxtUsername.getText().toString();
            String password = etxtPassword.getText().toString();
            Log.e("Username and password", username+" "+password);


            try {
                if(username.isEmpty() || password.isEmpty()) {
                    showHideProgressbar();
                    Toast.makeText(this, "Popunite sve podatke!", Toast.LENGTH_LONG).show();
                }
                else{

                    loginData = new JSONObject();
                    loginData.put("username", username);
                    loginData.put("password", password);


                    HttpHelper httpHelper = new HttpHelper();
                     httpHelper.doLogin(loginData, new HttpHelper.VolleyCallback() {
                         @Override
                         public void onSuccessJSON(JSONObject result) {
                             //app.setLoginData(result);
                             PrefsUtil.saveToPrefs(PrefsUtil.PREFS_LOGIN_USER_DATA_KEY, loginData.toString());
                             Log.e(TAG, "onSuccess callback "+result.toString());

                         }

                         @Override
                         public void onSuccessString(String result) {

                         }

                         @Override
                         public void onError(VolleyError error) {
                             showHideProgressbar();
                             Log.e(TAG, "onError callback "+error.getMessage());
                             try {
                                 JSONObject result = new JSONObject(error.getMessage());
                                 try {
                                     if(result!=null) {
                                         Log.e(TAG, result.toString());
                                         if (result.getJSONObject("error").getInt("status")==9) {
                                             Toast.makeText(LoginActivity.this, "Pogrešan email/korisničko ime ili lozinka!", Toast.LENGTH_LONG).show();
                                             etxtUsername.setText("");
                                             etxtPassword.setText("");
                                         }
                                     }else{
                                         Log.e("LoginActivity", "response is null");
                                     }
                                 } catch (JSONException e) {
                                     e.printStackTrace();
                                 }
                             } catch (JSONException e) {
                                 e.printStackTrace();
                             }
                         }
                     });


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void showHideProgressbar(){
        if(progress.getVisibility()==View.VISIBLE){
            progress.setVisibility(View.GONE);
        }else{
            progress.setVisibility(View.VISIBLE);
        }
    }
}
