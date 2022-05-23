package com.example.playandroid.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.playandroid.R;
import com.example.playandroid.entities.HomeTextItem;
import com.example.playandroid.entities.KnowledgeHierarchy;
import com.example.playandroid.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;


public class KnowledgeHierarchyFragment extends Fragment implements IView,IView2{


    private View rootView;
    private RecyclerView recyclerView;
    private List<KnowledgeHierarchy> mList=new ArrayList<>();
    Presenter presenter;

    public KnowledgeHierarchyFragment() {
        // Required empty public constructor
    }


    public static KnowledgeHierarchyFragment newInstance() {
        KnowledgeHierarchyFragment fragment = new KnowledgeHierarchyFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView==null){
            rootView =inflater.inflate(R.layout.fragment_knowledge_hierarchy, container, false);
        }
        recyclerView=(RecyclerView) rootView.findViewById(R.id.knowledge_hierarchy_recycler_view);
        presenter=new Presenter(this,this);
        presenter.fetchGetKnowledgeHierarchyData();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        return rootView;
    }

    @Override
    public void showData(ArrayList<?> list) {
        mList=(ArrayList<KnowledgeHierarchy>)list;
        KnowledgeHierarchyAdapter knowledgeHierarchyAdapter=new KnowledgeHierarchyAdapter(mList);
        recyclerView.setAdapter(knowledgeHierarchyAdapter);
        knowledgeHierarchyAdapter.setOnItemClickListener(new HomeRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    @Override
    public void showData2(ArrayList<?> list) {

    }
}