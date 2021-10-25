package com.adanlm.daggerpractice.di.main;

import com.adanlm.daggerpractice.network.main.MainApi;
import com.adanlm.daggerpractice.ui.posts.PostRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }

    @Provides
    static PostRecyclerAdapter provideAdapter(){
        return new PostRecyclerAdapter();
    }
}
