package com.adanlm.daggerpractice.ui.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adanlm.daggerpractice.R;
import com.adanlm.daggerpractice.models.Post;
import com.adanlm.daggerpractice.ui.Resource;
import com.adanlm.daggerpractice.util.VerticalSpaceItemDecoration;
import com.adanlm.daggerpractice.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostsFragment extends DaggerFragment {

    private static final String TAG = "PostsFragment";

    private PostsViewModel viewModel;
    private RecyclerView recyclerView;

    @Inject
    PostRecyclerAdapter adapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), TAG, Toast.LENGTH_SHORT).show();

        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: PostsFragment was created...");
        recyclerView = view.findViewById(R.id.recycler_view);

        viewModel = new ViewModelProvider(this, providerFactory).get(PostsViewModel.class);

        recyclerView.setAdapter(adapter);
        initRecyclerView();
        subscribeObservers();
    }

    private void subscribeObservers() {
        viewModel.observerPosts().removeObservers(getViewLifecycleOwner());
        viewModel.observerPosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if (listResource != null) {
                    Log.d(TAG, "onChanged: " + listResource.data);
                    if(listResource.status == Resource.Status.SUCCESS) {
                        adapter.setPosts(listResource.data);
                    } else {
                        Log.e(TAG, "onChanged: " + listResource.message);
                    }
                }
            }
        });
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpaceItemDecoration itemDecoration = new VerticalSpaceItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
    }
}
