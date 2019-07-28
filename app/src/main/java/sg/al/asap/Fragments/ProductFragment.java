package sg.al.asap.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import sg.al.asap.R;

public class ProductFragment extends Fragment {

    TextView Name;
    TextView Cost;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // do your variables initialisations here except Views!!!
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Name = (TextView) view.findViewById(R.id.productname);
        Cost = (TextView) view.findViewById(R.id.productcost);
        ImageView image = (ImageView) view.findViewById(R.id.productimg);
        Button addBtn = (Button) view.findViewById(R.id.addbtn);

        Bundle bundle = this.getArguments();

        final String getName = bundle.getString("Name", "Test");
        final int getCost = bundle.getInt("Cost");
        int getImageFilePath = bundle.getInt("Image");
//        Uri imgUri = Uri.parse(getImageFilePath);


        Name.setText(getName);
        Cost.setText("$"  + getCost);
//        image.setImageURI(imgUri);
        image.setImageResource(getImageFilePath);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CartFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString("Name", getName);
                bundle1.putInt("Cost", getCost);
                fragment.setArguments(bundle1);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

}