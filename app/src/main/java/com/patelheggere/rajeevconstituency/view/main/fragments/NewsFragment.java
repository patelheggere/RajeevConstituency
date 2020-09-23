package com.patelheggere.rajeevconstituency.view.main.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.patelheggere.rajeevconstituency.R;
import com.patelheggere.rajeevconstituency.RajeevApplication;
import com.patelheggere.rajeevconstituency.adapter.NewsAdapter;
import com.patelheggere.rajeevconstituency.base.BaseFragment;
import com.patelheggere.rajeevconstituency.model.NewsModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsFragment extends BaseFragment {

    private OnFragmentInteractionListener mListener;

    private static final String TAG = "NewsFragment";
    private View mView;
    private RecyclerView mRecyclerViewNews;
    private DatabaseReference databaseReference;
    private List<NewsModel> newsModelList;
    private NewsAdapter mNewsAdapter;

    public NewsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
       // args.putString(ARG_PARAM1, param1);
       // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           // mParam1 = getArguments().getString(ARG_PARAM1);
           // mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_news_layout, container, false);
        initViews();
        getData();
        return mView;
    }

    private void getData() {
        databaseReference = RajeevApplication.getFireBaseRef();
        databaseReference.child("news").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                newsModelList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    try {
                        NewsModel model = snapshot.getValue(NewsModel.class);
                        newsModelList.add(model);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                Collections.reverse(newsModelList);
                mNewsAdapter = new NewsAdapter(mActivity, newsModelList);
                mRecyclerViewNews.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
                mRecyclerViewNews.setAdapter(mNewsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initViews() {
        newsModelList = new ArrayList<>();
        mRecyclerViewNews = mView.findViewById(R.id.recyclerViewNews);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
