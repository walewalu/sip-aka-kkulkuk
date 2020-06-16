package com.baekdev.sip;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.baekdev.sip.ui.itemlist.ItemListFragment3;

public class SearchFragment extends Fragment {
    public SearchFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        final EditText searchInput = (EditText) view.findViewById(R.id.searchInput);
        ImageButton searchButton = (ImageButton) view.findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemListFragment3 newFragment = ItemListFragment3.newInstance();
                Bundle bundle = new Bundle();
                String s = searchInput.getText().toString();
                bundle.putString("Value", s);
                newFragment.setArguments(bundle);
                ((MainActivity)getActivity()).replaceFragment(newFragment);
            }
        });

        return view;
    }
}



