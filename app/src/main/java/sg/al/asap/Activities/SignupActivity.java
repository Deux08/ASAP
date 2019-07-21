package sg.al.asap.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import sg.al.asap.R;

public class SignupActivity extends AppCompatActivity {

    ImageView imgUserPhoto;
    static int PReqCode = 1 ;
    static int REQUESTCODE = 1;
    Uri pickedImgUri;
    private TextView redirectToLogIn;


    private EditText userName, userEmail, userPassword, userConfirmPassword;
    private ProgressBar loadingProgressBar;
    private Button regBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        userName = findViewById(R.id.regName);
        userEmail = findViewById(R.id.regMail);
        userPassword = findViewById(R.id.regPassword);
        userConfirmPassword = findViewById(R.id.regPassword2);
        loadingProgressBar = findViewById(R.id.progressBar);
        regBtn = findViewById(R.id.regBtn);
        loadingProgressBar.setVisibility(View.INVISIBLE);

        //spannable string (parts of string can be clicked)
        redirectToLogIn = findViewById(R.id.clickableLogIn);
        String text =  "Already have an account? Log In";
        SpannableString ss = new SpannableString(text);
        ClickableSpan link = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
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
        ss.setSpan(link, 25, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        redirectToLogIn.setText(ss);
        redirectToLogIn.setMovementMethod(LinkMovementMethod.getInstance());
        //spannable string end

        mAuth = FirebaseAuth.getInstance();


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regBtn.setVisibility(View.INVISIBLE);
                loadingProgressBar.setVisibility(View.VISIBLE);

                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String confirmPass = userConfirmPassword.getText().toString();
                final String name= userName.getText().toString();

                //validation
                if(email.isEmpty() || password.isEmpty() || name.isEmpty()){

                    showMessage("Please fill in all required fields");
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgressBar.setVisibility(View.INVISIBLE);

                }
                else if(!password.equals(confirmPass)){
                    showMessage("Passwords don't match");
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgressBar.setVisibility(View.INVISIBLE);
                }
                else{
                    //all fields are filled and proceed on creating the user account

                    CreateUserAccount(email,name,password);

                }
            }
        });

        imgUserPhoto = findViewById(R.id.regUserPhoto);
        imgUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT >= 22){
                    checkAndRequestForPermission();
                }
                else{
                    openGallery();
                }

            }
        });

    }

    //will create the user if their email is valid
    private void CreateUserAccount(String email, final String name, String password) {

        //function of the method below is self explanatory
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            //user account is created
                            showMessage("Account is created");

                            //after creation name and profile pic must be updated
                            updateUserInfo(name, pickedImgUri, mAuth.getCurrentUser());
                        }
                        else{
                            //account creation failed
                            showMessage("account creation failed" + task.getException().getMessage());
                            regBtn.setVisibility(View.VISIBLE);
                            loadingProgressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });


    }

    //update user photo and name
    private void updateUserInfo(final String name, Uri pickedImgUri, final FirebaseUser currentUser) {

        //1. upload photo to firebase storage and get url
        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
        final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
        imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                //image uploaded successfully and get url
                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //url contains user's image url
                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                                                     .setDisplayName(name)
                                                                     .setPhotoUri(uri)
                                                                     .build();

                        currentUser.updateProfile(profileUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            //user info updated successfully
                                            showMessage("Registration successful");
                                            updateUI();
                                        }
                                    }
                                });
                    }
                });


            }
        });

    }

    private void updateUI() {

        Intent homeActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(homeActivity);
        finish();

    }

    //method shows toast message
    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void openGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUESTCODE);

    }

    private void checkAndRequestForPermission() {

        if (ContextCompat.checkSelfPermission(SignupActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            if (ActivityCompat.shouldShowRequestPermissionRationale(SignupActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)){

                Toast.makeText(SignupActivity.this, "Please allow permission", Toast.LENGTH_LONG).show();

            }

            else{
                ActivityCompat.requestPermissions(SignupActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }
        }

        else{
            openGallery();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESTCODE && data != null){
            // user has picked an image
            // need to save its reference to a uri variable

            pickedImgUri = data.getData();
            imgUserPhoto.setImageURI(pickedImgUri);
        }
    }
}
