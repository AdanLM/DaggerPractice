package com.adanlm.daggerpractice.di;

import com.adanlm.daggerpractice.di.modules.GlideModule;
import com.adanlm.daggerpractice.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Aqui declaramos las dependencias que no cambiaran durante la ejecucion de la aplicacion.
 * Por ejemplo, el contexto de la aplicaciones, instacias de retrofit, room, glide, etc.
 */
@Module(includes = GlideModule.class)
public class AppModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
