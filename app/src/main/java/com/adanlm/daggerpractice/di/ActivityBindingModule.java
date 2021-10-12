package com.adanlm.daggerpractice.di;

import com.adanlm.daggerpractice.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Aqui hacemos que Dagger.Android nos cree un subcomponente de los modulos escritos aqui {AcitivtyBindingModule},
 * estos tendran un componente padre {AppComponent}
 *
 */
@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract AuthActivity authActivity();
}
