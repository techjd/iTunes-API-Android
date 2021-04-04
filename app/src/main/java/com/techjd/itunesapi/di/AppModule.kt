package com.techjd.itunesapi.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.techjd.itunesapi.R
import com.techjd.itunesapi.api.iTunesApi
import com.techjd.itunesapi.data.artists.ArtistsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(iTunesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideiTunesApi(retrofit: Retrofit): iTunesApi =
        retrofit.create(iTunesApi::class.java)

    @Provides
    @Singleton
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) =
        Glide.with(context).setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_image)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
        )

    @Provides
    @Singleton
    fun provideDatabase(app: Application): ArtistsDatabase =
        Room.databaseBuilder(app, ArtistsDatabase::class.java, "artists_database")
            .allowMainThreadQueries()
            .build()


}