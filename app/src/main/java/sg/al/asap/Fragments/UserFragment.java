package sg.al.asap.Fragments;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import sg.al.asap.Activities.MainActivity;
import sg.al.asap.Activities.SignupActivity;
import sg.al.asap.R;

public class UserFragment extends Fragment {

    FirebaseAuth mAuth;
    FirebaseUser currentuser;

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        return view;

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // do your variables initialisations here except Views!!!
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        // initialise your views
        ImageView profilePic = (ImageView) view.findViewById(R.id.profileCircleImageView);
        TextView userName = (TextView) view.findViewById(R.id.usernameTextView);
        TextView userEmail = (TextView) view.findViewById(R.id.userEmail);

        Switch darkModeSwitch = (Switch) view.findViewById(R.id.darkModeSwitch);
        TextView editProfile = (TextView) view.findViewById(R.id.editProf);
        TextView changePass = (TextView) view.findViewById(R.id.changePassword);
        TextView notification = (TextView) view.findViewById(R.id.notification);
        TextView logOut = (TextView) view.findViewById(R.id.logOut);

        mAuth = FirebaseAuth.getInstance();
        currentuser = mAuth.getCurrentUser();

        ((MainActivity)getActivity()).update();
    }



}
