package foit.product.loranalyst.di

import android.content.Context
import foit.product.loranalyst.BuildConfig
import foit.product.loranalyst.api.ApiHelper
import foit.product.loranalyst.api.ApiHelperImpl
import foit.product.loranalyst.api.ApiService
import foit.product.loranalyst.utils.BASE_URL
import foit.product.loranalyst.utils.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    single { provideOkHttpClient() }
    single { retrofit() }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }
    single<ApiHelper> { return@single ApiHelperImpl(get()) }
}

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .build()
} else OkHttpClient
    .Builder()
    .connectTimeout(2, TimeUnit.MINUTES)
    .writeTimeout(2, TimeUnit.MINUTES)
    .readTimeout(2, TimeUnit.MINUTES)
    .build()

private fun retrofit() = Retrofit.Builder()
    .callFactory(OkHttpClient.Builder().build())
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)