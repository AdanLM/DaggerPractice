package com.adanlm.daggerpractice.ui.posts;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.adanlm.daggerpractice.SessionManager;
import com.adanlm.daggerpractice.network.main.MainApi;

import javax.inject.Inject;

public class PostsViewModel extends ViewModel {

    private static final String TAG = "PostsViewModel";

    private final SessionManager sessionManager;
    private final MainApi mainApi;

    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi mainApi) {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
        Log.d(TAG, "PostsViewModel: viewModel is ready");
    }
}
