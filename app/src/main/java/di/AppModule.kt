package di

import com.dendron.easyweather.domain.WeatherRepository
import com.dendron.easyweather.remote.RemoteWeatherRepository
import com.dendron.easyweather.remote.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {

        val moviesClient = OkHttpClient().newBuilder()
            .build()

        return Retrofit.Builder()
            .client(moviesClient)
            .baseUrl("https://api.open-meteo.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(api: WeatherApi): WeatherRepository {
        return RemoteWeatherRepository(api)
    }
}