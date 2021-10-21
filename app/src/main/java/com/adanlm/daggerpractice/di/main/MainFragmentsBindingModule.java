package com.adanlm.daggerpractice.di.main;

import com.adanlm.daggerpractice.ui.posts.PostsFragment;
import com.adanlm.daggerpractice.ui.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentsBindingModule {

    @ContributesAndroidInjector
    abstract ProfileFragment profileFragment();

    @ContributesAndroidInjector
    abstract PostsFragment postsFragment();
}
