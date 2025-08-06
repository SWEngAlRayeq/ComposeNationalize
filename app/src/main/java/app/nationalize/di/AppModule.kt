package app.nationalize.di

import app.nationalize.data.remote.NationalizeApi
import app.nationalize.data.repo_impl.NationalizeRepositoryImpl
import app.nationalize.domain.repo.NationalizeRepository
import app.nationalize.domain.usecase.GetNationalityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = "https://api.nationalize.io/"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideApi(baseUrl: String, client: OkHttpClient): NationalizeApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NationalizeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: NationalizeApi): NationalizeRepository =
        NationalizeRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideUseCase(repo: NationalizeRepository): GetNationalityUseCase =
        GetNationalityUseCase(repo)


}
