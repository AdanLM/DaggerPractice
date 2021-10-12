package com.adanlm.daggerpractice.di;

import com.adanlm.daggerpractice.di.modules.GlideModule;
import com.bumptech.glide.request.RequestOptions;

import dagger.Module;
import dagger.Provides;

/**
 * Aqui declaramos las dependencias que no cambiaran durante la ejecucion de la aplicacion.
 * Por ejemplo, el contexto de la aplicaciones, instacias de retrofit, room, glide, etc.
 */
@Module(includes = GlideModule.class)
public class AppModule {

}
