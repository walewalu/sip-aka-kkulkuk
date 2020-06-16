package com.baekdev.sip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signUp).setOnClickListener(onClickListener);
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.signUp:
                    Log.v("알림", "사용자가 가입하기 버튼을 클릭하였습니다.");
                    signUp();
                    break;
            }
        }
    };

    private void signUp(){
        String email = ((EditText)findViewById(R.id.signUpEmail)).getText().toString();
        String password = ((EditText)findViewById(R.id.signUpPass)).getText().toString();
        String passwordconf = ((EditText)findViewById(R.id.signUpPassConf)).getText().toString();

        if (email.length() > 0 && password.length() > 0 && passwordconf.length() > 0) {
            if (!(password.equals(passwordconf))) {
                Log.e("EmailPassword", "비밀번호가 일치하지 않습니다.");
                Toast.makeText(SignUpActivity.this, "비밀번호가 일치하지 않습니다.",
                        Toast.LENGTH_SHORT).show();
            } else {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("EmailPassword", "회원가입에 성공하였습니다.");
                                    Toast.makeText(SignUpActivity.this, "가입 성공. 로그인해주세요",
                                            Toast.LENGTH_SHORT).show();
                                    // FirebaseUser user = mAuth.getCurrentUser();
                                    Log.v("알림", "로그인 액티비티로 이동합니다.");
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("EmailPassword", "회원가입에 실패하였습니다.", task.getException());
                                    Toast.makeText(SignUpActivity.this, "인증 실패",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        } else {
            Log.e("EmailPassword", "이메일과 비밀번호를 입력해주세요.");
            Toast.makeText(SignUpActivity.this, "이메일과 비밀번호를 입력해주세요.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}

