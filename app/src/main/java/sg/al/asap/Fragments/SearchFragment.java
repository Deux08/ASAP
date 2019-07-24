package sg.al.asap.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import sg.al.asap.R;

public class SearchFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<String> avengers = new ArrayList<>();
        avengers.add("Iron Man");
        avengers.add("Captain America");
        avengers.add("Vision");
        avengers.add("Hawk Eye");
        avengers.add("Ant-Man");
        avengers.add("Spider-Man");
        avengers.add("Captain Marvel");
        avengers.add("Doctor Strange");
        avengers.add("Black Panther");
        avengers.add("Hulk");
        avengers.add("Star Lord");
        avengers.add("Rocket");
        avengers.add("Groot");
        avengers.add("Thor");
        avengers.add("Quicksilver");
        avengers.add("Scarlet Witch");
        avengers.add("Falcon");
        avengers.add("War Machine");

        SearchView searchBar = (SearchView) view.findViewById(R.id.searchBar);
        ListView searchList = (ListView) view.findViewById(R.id.searchList);


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), R.layout.search_listview, R.id.search_listview, avengers);
        searchList.setAdapter(adapter);

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }


}
