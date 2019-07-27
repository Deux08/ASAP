package sg.al.asap.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import sg.al.asap.R;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText userEmail;
    private ProgressBar forgotBar;
    private Button resetBtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        userEmail = findViewById(R.id.reset_email);
        forgotBar = findViewById(R.id.reset_progressBar);
        resetBtn = findViewById(R.id.resetBtn);

        mAuth = FirebaseAuth.getInstance();

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.sendPasswordResetEmail(userEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(ResetPasswordActivity.this, "Password reset email has been sent", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(ResetPasswordActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }
}
