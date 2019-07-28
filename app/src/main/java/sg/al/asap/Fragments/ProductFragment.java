package sg.al.asap.Fragments;

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
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import sg.al.asap.R;

public class ProductFragment extends Fragment {
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

        TextView Name = (TextView) view.findViewById(R.id.productname);
        TextView Cost = (TextView) view.findViewById(R.id.productcost);
        ImageView image = (ImageView) view.findViewById(R.id.productimg);

        Bundle bundle = this.getArguments();

        String getName = bundle.getString("Name", "Test");
        int getCost = bundle.getInt("Cost");
        int getImageFilePath = bundle.getInt("Image");
//        Uri imgUri = Uri.parse(getImageFilePath);



        Name.setText(getName);
        Cost.setText("$"  + getCost);
//        image.setImageURI(imgUri);
        image.setImageResource(getImageFilePath);
    }

}