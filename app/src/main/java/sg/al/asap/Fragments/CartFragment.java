package sg.al.asap.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.tbouron.shakedetector.library.ShakeDetector;

import java.util.ArrayList;
import java.util.List;

import sg.al.asap.Activities.CheckOutActivity;
import sg.al.asap.R;

public class CartFragment extends Fragment {

    TextView Name;
    TextView Cost;
//    List<String> cartList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container,false);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ShakeDetector.create(getActivity(), new ShakeDetector.OnShakeListener() {
            @Override
            public void OnShake() {
                Toast.makeText(getContext(), "You have checked out and will receive your item tomorrow.", Toast.LENGTH_SHORT).show();
                Log.d("Shake", "OnShake: shake");

                Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    v.vibrate(500);
                }
            }
        });

//        ListView cartLV = getActivity().findViewById(R.id.cartLV);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.cart_listview, cartList);
//
//        Bundle bundle = this.getArguments();
//        String getName = bundle.getString("Name", "Test");
//        int getCost = bundle.getInt("Cost");
//
//        cartList.add(getName + " " + getCost);
//        cartLV.setAdapter(adapter);

    }

    public void onViewCreated(final View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Name = view.findViewById(R.id.item_name);
        Cost = view.findViewById(R.id.item_price);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            String getName = bundle.getString("Name", "Test");
            int getCost = bundle.getInt("Cost");
            Name.setText(getName);
            Cost.setText("$"  + getCost);
        }

        Button checkOutBtn = (Button)view.findViewById(R.id.checkoutBtn);
        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CheckOutActivity.class);
                startActivity(intent);
            }
        });

    }
}
