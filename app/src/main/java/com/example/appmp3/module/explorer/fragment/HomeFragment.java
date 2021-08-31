package com.example.appmp3.module.explorer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmp3.R;
import com.example.appmp3.event.IEventClick;
import com.example.appmp3.module.explorer.adapter.HomeAdapter;
import com.example.appmp3.model.Banner;
import com.example.appmp3.model.Category;
import com.example.appmp3.model.Selection;
import com.example.appmp3.module.home.DetailCategoryActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IEventClick {

    private final List<Banner> bannerList = new ArrayList<>();
    private final List<Selection> selectionList = new ArrayList<>();
    private final List<Category> categories = new ArrayList<>();
    private HomeAdapter homeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);


        RecyclerView recyclerViewHome = view.findViewById(R.id.recyclerViewHome);
        homeAdapter = new HomeAdapter(getActivity(), categories, bannerList, selectionList, this);
        recyclerViewHome.setAdapter(homeAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewHome.setLayoutManager(layoutManager);
        recyclerViewHome.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onClick(Category category) {
        Intent intent = new Intent(getContext(), DetailCategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("detail1", category);
        intent.putExtras(bundle);
//        intent.putExtra("detail1", category);
        startActivity(intent);
    }
}
