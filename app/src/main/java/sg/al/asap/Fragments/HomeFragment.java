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


        productslist.add(new Product("Gucci bag", 3,"drawable/capybara1.jpg"));
        productslist.add(new Product("Nike shoes", 10,"drawable/capybara1.jpg"));
        productslist.add(new Product("FIla bag", 9,"drawable/capybara1.jpg"));
        productslist.add(new Product("Chanel perfume", 6,"drawable/capybara1.jpg"));
        productslist.add(new Product("Unik shirt", 5,"drawable/capybara1.jpg"));
        productslist.add(new Product("dog", 11,"drawable/capybara1.jpg"));


        ProductAdapter adapter = new ProductAdapter(view.getContext(), productslist);
        listView.setAdapter(adapter);
        return view;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
//    void showlistview (){
//
//        Intent intent = new Intent(HomeFragment.this);
//        startActivity(intent);
//
//    }


//    public View onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState, @NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
//        super.onViewCreated(view, savedInstanceState);
//        listView = getActivity().findViewById(R.id.galleryLV);
//        final ArrayList<Product> productslist = new ArrayList<>();
//        productslist.add(new Product("gucci bag", 3));
//        productslist.add(new Product("bag", 5));
//        view =inflater.inflate(R.layout.fragment_home, container, false);
//
//        final ProductAdapter adapter = new ProductAdapter(view.getContext(), R.layout.product_row, productslist);
//        listView.setAdapter(adapter);
//        return view;
//
////    }
//
//    //    int images  [] = {R.drawable.shirt, R.drawable.shoe, R.drawable.hat, R.drawable.bag, R.drawable.apple};
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_home, container, false);
//    }



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
                bundle.putString("Image", product.getImageFilePath());
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

