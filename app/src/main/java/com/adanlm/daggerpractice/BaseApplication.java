package com.adanlm.daggerpractice;

import com.adanlm.daggerpractice.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Creamos una clase customizada {Application} que extiende de {DaggerApplication}
 * Sustituimos applicationInjector que le dice a Dagger como usar nuestro component @Singleton
 * Nunca tenemos que llamar a 'component.inject(this)' ya que DaggerApplication lo hara por nosotros
 */
public class BaseApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
