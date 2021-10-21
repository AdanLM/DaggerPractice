package com.adanlm.daggerpractice.di;

import com.adanlm.daggerpractice.di.auth.AuthModule;
import com.adanlm.daggerpractice.di.auth.AuthViewModelModule;
import com.adanlm.daggerpractice.di.main.MainFragmentsBindingModule;
import com.adanlm.daggerpractice.di.main.MainModule;
import com.adanlm.daggerpractice.di.main.MainViewModelModule;
import com.adanlm.daggerpractice.ui.auth.AuthActivity;
import com.adanlm.daggerpractice.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Aqui hacemos que Dagger.Android nos cree un subcomponente de los modulos escritos aqui {AcitivtyBindingModule},
 * estos tendran un componente padre {AppComponent}
 */
@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {AuthViewModelModule.class, AuthModule.class})
    abstract AuthActivity authActivity();

    @ContributesAndroidInjector(modules = {MainFragmentsBindingModule.class, MainViewModelModule.class, MainModule.class})
    abstract MainActivity mainActivity();
}
