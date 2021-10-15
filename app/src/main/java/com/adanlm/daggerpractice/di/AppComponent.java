package com.adanlm.daggerpractice.di;

import android.app.Application;

import com.adanlm.daggerpractice.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Este es un componente usado por {BaseApplication}
 * {@link AndroidSupportInjectionModule} es el modulo de Dagger.Android
 * que ayuda a la generacion y localizacion de subcomponentes
 */
@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBindingModule.class,
                AppModule.class,
                ViewModelFactoryModule.class,
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    // Esto nos sirve para poder hacer DaggerAppComponent.builder().application(this).build().inject(this);
    // sin tener que instaciar ningun modulo o decir que modulo le estamos pasando a la aplicacion
    //Application solo se proporcionara en nuestro grafico de aplicacion
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}
