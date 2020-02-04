package com.user.songratingsystem.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.user.songratingsystem.R;
import com.user.songratingsystem.model.RegisteredUsers;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView, recyclerView1, recyclerView2;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.dataRecycler);
        recyclerView1 = view.findViewById(R.id.artistRecycler);
        recyclerView2 = view.findViewById(R.id.featuredRecycler);

       List<RegisteredUsers> viewData = new ArrayList<>();

//       viewData.add(new RegisteredUsers("Rock", R.drawable.artist));
//       viewData.add(new RegisteredUsers("Metal", R.drawable.logo));
//       viewData.add(new RegisteredUsers("Heavy Metal", R.drawable.artist));
//       viewData.add(new RegisteredUsers("Rap", R.drawable.logo));

//       ViewAdapter viewAdapter = new ViewAdapter(getActivity(), viewData);
//        recyclerView.setAdapter(viewAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
//
//        recyclerView1.setAdapter(viewAdapter);
//        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
//
//        recyclerView2.setAdapter(viewAdapter);
//        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        return view;


    }

}
