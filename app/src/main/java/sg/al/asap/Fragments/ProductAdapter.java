package sg.al.asap.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sg.al.asap.R;

public class ProductAdapter extends BaseAdapter {

    Context context;
    List<Product> productsList = new ArrayList<>();

    ProductAdapter(Context context, List<Product> products){
        this.context = context;
        this.productsList = products;
    }

    @Override
    public int getCount() {
        return productsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;

        itemView = LayoutInflater.from(context).inflate(R.layout.product_row,parent,false);
//        product name
        TextView productName = itemView.findViewById(R.id.pName);
        productName.setText(productsList.get(position).getName());
//        product price
        TextView productPrice = itemView.findViewById(R.id.pPrice);
        productPrice.setText(String.valueOf(productsList.get(position).getCost()));
//        product image
        ImageView productimage =itemView.findViewById(R.id.ppic);
        productimage.setImageResource(R.drawable.capybara1);



        return itemView;
    }
}





//public class ProductAdapter extends ArrayAdapter<Product> {
//    private Context mContext;
//    int mResource;
//
//    public ProductAdapter(Context context, int resource, List<Product> objects) {
//        super(context, resource, objects);
//    }
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent)
//    {
//        String name =getItem(position).getName();
//        int cost = getItem(position).getCost();
//
////        creating the object
//
//        Product product = new Product(name, cost);
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        convertView = inflater.inflate(mResource, parent, false);
//
//        TextView tvname = (TextView) convertView.findViewById(R.id.songTitle);
//        TextView tvcost = (TextView) convertView.findViewById(R.id.songSubtitle);
//        ImageView ivAlbumArt = (ImageView) convertView.findViewById(R.id.albumArt);
//
//        return  convertView;
//
//    }
//
//}