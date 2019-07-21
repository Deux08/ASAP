package sg.al.asap.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import sg.al.asap.R;

public class LoginActivity extends AppCompatActivity {

    private EditText userEmail, userPassword;
    private Button loginBtn;
    private ProgressBar loginProgressBar;
    private FirebaseAuth mAuth;
    private Intent MainActivity;
    private ImageView loginPhoto;
    private TextView redirectToSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmail = findViewById(R.id.login_email);
        userPassword = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.loginBtn);
        loginProgressBar = findViewById(R.id.login_progressBar);
        mAuth = FirebaseAuth.getInstance();
        MainActivity = new Intent(this, sg.al.asap.Activities.MainActivity.class);

        //spannable string (parts of string can be clicked)
        redirectToSignUp = findViewById(R.id.clickableSignup);
        String text =  "Don't have an account ? Sign up";
        SpannableString ss = new SpannableString(text);
        ClickableSpan link = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent loginActivity = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(loginActivity);
                finish();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.rgb(119, 17, 150));
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(link, 24, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        redirectToSignUp.setText(ss);
        redirectToSignUp.setMovementMethod(LinkMovementMethod.getInstance());
        //spannable string end

        loginPhoto = findViewById(R.id.login_photo);
        loginPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signupActivity = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(signupActivity);
                finish();

            }
        });

        loginProgressBar.setVisibility(View.INVISIBLE);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProgressBar.setVisibility(View.VISIBLE);
                loginBtn.setVisibility(View.INVISIBLE);

                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()){
                    showMessage("Please fill in required fields");
                    loginBtn.setVisibility(View.VISIBLE);
                    loginProgressBar.setVisibility(View.INVISIBLE);
                }
                else{
                    signIn(email,password);
                }
            }
        });


    }

    private void signIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    loginProgressBar.setVisibility(View.INVISIBLE);
                    loginBtn.setVisibility(View.VISIBLE);
                    UpdateUI();

                }
                else{
                    showMessage(task.getException().getMessage());
                    loginProgressBar.setVisibility(View.INVISIBLE);
                    loginBtn.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    private void UpdateUI() {

        startActivity(MainActivity);
        finish();

    }

    private void showMessage(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null){
            //user is already connected so we have to redirect them to home page
            UpdateUI();
        }
    }
}
