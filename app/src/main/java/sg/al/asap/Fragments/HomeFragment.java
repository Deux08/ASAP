package sg.al.asap.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import sg.al.asap.R;

public class HomeFragment extends Fragment {

    ListView listViewgalley;
    String itemList[] = {"shirt", "shoe", "hat", "bag", "apple"};
//    int images  [] = {R.drawable.shirt, R.drawable.shoe, R.drawable.hat, R.drawable.bag, R.drawable.apple};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listViewgalley = getActivity().findViewById(R.id.galleryLV);

        return inflater.inflate(R.layout.fragment_home, container,false);

    }

//    class Myadapter extends ArrayAdapter<String>{
//
//        Context context;
//        String rproductname [];
//        String rcost[];
//        int rimg [] ;
//
//        Myadapter (Context c, String productname[], String cost[], int img[]){
//            super(c, R.layout.customgallery, R.id.productname, productname);
//            this.context = c;
//            this.rproductname = productname;
//            this.rcost = cost;
//            this.rimg = img;
//
//        }
//
//        @NonNull
//        @Override
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().get
//            return super.getView(position, convertView, parent);
//        }
//    }



}
