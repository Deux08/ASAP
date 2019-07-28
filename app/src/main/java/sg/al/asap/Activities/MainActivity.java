package sg.al.asap.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import sg.al.asap.Fragments.CartFragment;
import sg.al.asap.DarkModePrefManager;
import sg.al.asap.Fragments.HomeFragment;
import sg.al.asap.R;
import sg.al.asap.Fragments.SearchFragment;
import sg.al.asap.Fragments.UserFragment;

import static sg.al.asap.Activities.Constants.ERROR_DIALOG_REQUEST;
import static sg.al.asap.Activities.Constants.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;
import static sg.al.asap.Activities.Constants.PERMISSIONS_REQUEST_ENABLE_GPS;

public class MainActivity extends AppCompatActivity /*implements NavigationView.OnNavigationItemSelectedListener*/ {

    private FirebaseAuth mAuth;
    private BottomNavigationView bottomNavigationView;
    FirebaseUser currentuser;


    ImageView profilePic;
    TextView userName ;
    TextView userEmail ;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigationMyProfile:
                    selectedFragment = new UserFragment();
                    break;
                case R.id.navigationSearch:
                    selectedFragment = new SearchFragment();
                    break;
                case R.id.navigationHome:
                    selectedFragment = new HomeFragment();
                    break;
                case  R.id.navigationCart:
                    selectedFragment = new CartFragment();
                    break;
//                case  R.id.navigationMenu:
//                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//                    drawer.openDrawer(GravityCompat.START);
//                    selectedFragment = new MenuFragment();
//                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        currentuser = mAuth.getCurrentUser();

        if(new DarkModePrefManager(this).isNightMode()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        bottomNavigationView.setSelectedItemId(R.id.navigationHome);
    }



    public void update(){
         profilePic = (ImageView) findViewById(R.id.profileCircleImageView);
         userName = (TextView) findViewById(R.id.usernameTextView);
         userEmail = (TextView) findViewById(R.id.userEmail);

//        if(currentuser.getEmail() == null || currentuser.getDisplayName() == null){
//          userName.setText("User not found");
//        }
//        else{
            userEmail.setText(currentuser.getEmail());
            userName.setText(currentuser.getDisplayName());
//        }

        //use glide(library) to load user image
        Glide.with(this).load(currentuser.getPhotoUrl()).into(profilePic);

    }

    public void signOut(){
        mAuth.signOut();
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }


}
