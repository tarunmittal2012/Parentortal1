package com.example.tarunmittal.myapplication.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tarunmittal.myapplication.Networking.NetworkUtils;
import com.example.tarunmittal.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    String verification_code;

    EditText e1, e2;

    TextInputLayout e3;

    Button b1, b2;

    String json;

    URL url;

    @Override
    public void onBackPressed() {

        finish();
    }

    public int fetchData(String jsonResponse) throws JSONException {

        final String KEY_RES = "res";
        final String KEY_REG = "reg_no";
        final String KEY_SECTION = "section";
        final String KEY_STREAM = "stream";
        final String KEY_NAME = "name";
        final String KEY_FNAME = "fname";
        final String KEY_EMAIL = "email";
        final String KEY_MOBILE = "mobile_no";
        final String KEY_FMOBILE = "fmobile_no";
        final String KEY_MENTOR = "mentor";
        final String KEY_MENTOR_REG = "mentor_reg";
        final String KEY_STATE = "state";
        final String KEY_DOB = "dob";
        final String KEY_ADDRESS = "address";
        JSONObject studentJson = new JSONObject(jsonResponse);
        JSONArray studentArray = studentJson.getJSONArray(KEY_RES);
        for (int i = 0; i < studentArray.length(); i++) {
            JSONObject object = studentArray.getJSONObject(i);
            String regNo = "" + object.getString(KEY_REG);
            String name = "" + object.getString(KEY_NAME);
            String section = "" + object.getString(KEY_SECTION);
            String fatherName = "" + object.getString(KEY_FNAME);
            String email = "" + object.getString(KEY_EMAIL);
            String stream = "" + object.getString(KEY_STREAM);
            String state = "" + object.getString(KEY_STATE);
            String dob = "" + object.getString(KEY_DOB);
            String address = "" + object.getString(KEY_ADDRESS);
            String mobile = "" + object.getString(KEY_MOBILE);
            String fatherMobile = "" + object.getString(KEY_FMOBILE);
            String mentorName = "" + object.getString(KEY_MENTOR);
            String mentorUid = "" + object.getString(KEY_MENTOR_REG);

            SharedPreferences preferences = getSharedPreferences("STUDENT", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("ID", regNo);
            editor.putString("NAME", name);
            editor.putString("SECTION", section);
            editor.putString("FNAME", fatherName);
            editor.putString("EMAIL", email);
            editor.putString("STREAM", stream);
            editor.putString("STATE", state);
            editor.putString("DOB", dob);
            editor.putString("ADDRESS", address);
            editor.putString("MOBILE", mobile);
            editor.putString("FMOBILE", fatherMobile);
            editor.putString("MENTOR", mentorName);
            editor.putString("MENTORREG", mentorUid);
            editor.apply();
        }

        Log.e("SIZ", studentArray.length() + "");

        if (studentArray.length() > 0)
            return 1;
        else if (studentArray.length() <= 0) {
            return 2;
        }
        return 3;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e1 = findViewById(R.id.num);
        e2 = findViewById(R.id.otp);
        e3 = findViewById(R.id.text_input_layout1);
        b1 = findViewById(R.id.login);
        b2 = findViewById(R.id.verify);
        auth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                super.onCodeSent(s, forceResendingToken);
                verification_code = s;
                if (verification_code != null) {
                    Toast.makeText(LoginActivity.this, "Code sent to the number", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "not send", Toast.LENGTH_SHORT).show();
                }
            }
        };

    }

    public void generateotp(View view) {

        String number = "+91" + e1.getText().toString();
        e3.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Sending Code", Toast.LENGTH_SHORT).show();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number, 60, TimeUnit.SECONDS, this, mCallbacks
        );

    }

    public void signInWIthNumber(PhoneAuthCredential credential) {

        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "User signed in Sucessfully", Toast.LENGTH_SHORT).show();

                            try {
                                String loginString = "https://parentsportal.000webhostapp.com/himLogin.php?Password=" + e1.getText().toString();
                                Log.e("onComplete: ", loginString);
                                url = new URL(loginString);
                                StudentTask studentTask = new StudentTask(getApplicationContext());

                                json = studentTask.execute(loginString).get();

                                switch (fetchData(json)) {
                                    case 1: {

                                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        break;
                                    }
                                    case 2: {
                                        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                        builder.setTitle("Warning!").setMessage("Invalid Credential").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                            public void onClick(DialogInterface dialog, int which) {

                                                b2.setBackgroundColor(Color.parseColor("#FFF59D"));
                                                e2.setText("");
                                                dialog.cancel();
                                            }
                                        }).setIcon(R.drawable.no_user)
                                                .show();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getApplicationContext(), "Invalid OTP", Toast.LENGTH_LONG).show();
                            }
                        }

                    }

                });
    }

    public void verify(View view) {

        String inputcode = e2.getText().toString();

        if(inputcode.length()==6) {

            if (verification_code != null) {
                b2.setBackgroundColor(Color.DKGRAY);
                verifyPhoneNumber(verification_code, inputcode);

            }
        }
    }

    public void verifyPhoneNumber(String verification_code1, String inputcode1) {

        final ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {

            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verification_code1, inputcode1);
            signInWIthNumber(credential);
        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setTitle("Warning!")
                    .setMessage("No Internet Connection")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            dialog.cancel();
                            b2.setBackgroundColor(Color.parseColor("#FFF59D"));

                        }
                    }).setIcon(R.drawable.no_internet)
                    .show();
        }
    }

    class StudentTask extends android.os.AsyncTask<String, Void, String> {

        Context mContext;

        StudentTask(Context loginActivity) {

            this.mContext = loginActivity;
        }

        @Override
        protected String doInBackground(String... strings) {

            json = null;
            try {
                Log.e("backgroundd", "doInBackground: ");
                json = NetworkUtils.httpConnection(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
        }

    }
}
