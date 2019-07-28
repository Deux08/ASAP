package sg.al.asap.Fragments;

import android.content.Context;
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
import android.widget.Toast;

import com.github.tbouron.shakedetector.library.ShakeDetector;

import sg.al.asap.R;

public class CartFragment extends Fragment {
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
                Toast.makeText(getContext(), "Device shaken!", Toast.LENGTH_SHORT).show();
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

    }

    public void onViewCreated(final View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

    }
}
