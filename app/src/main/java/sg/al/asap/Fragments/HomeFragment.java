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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sg.al.asap.R;

public class HomeFragment extends Fragment {

    public HomeFragment() {

    }

    ListView listView;
    View view;
    String itemList[] = {"shirt", "shoe", "hat", "bag", "apple"};
    int img [] = {R.drawable.capybara1, R.drawable.capybara2, R.drawable.capybara3, R.drawable.capybara4, R.drawable.capybara5};
    List<Product> productslist = new ArrayList<>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = view.findViewById(R.id.galleryLV);


        productslist.add(new Product("bag", 3, R.drawable.capybara1));
        productslist.add(new Product("soft toy", 5, R.drawable.capybara2));
        productslist.add(new Product("bag2", 7, R.drawable.capybara3));
        productslist.add(new Product("rat 2", 100, R.drawable.capybara4));
        productslist.add(new Product("rat bag", 99, R.drawable.capybara5));
        productslist.add(new Product("cute rat for sale", 30, R.drawable.capybara5));



        ProductAdapter adapter = new ProductAdapter(view.getContext(), productslist);
        listView.setAdapter(adapter);
        return view;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
//

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Product product = productslist.get(position);
                Log.i("testy", "I Clicked on Row " + position + " and it shows ");

                Fragment fragment = new ProductFragment();
                Bundle bundle = new Bundle();
                bundle.putString("Name", product.getName());
                bundle.putInt("Cost", product.getCost());
                bundle.putInt("Image", product.getImageFilePath());
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

    }
}

