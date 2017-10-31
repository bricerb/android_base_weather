package example.myapplication;

import example.myapplication.models.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Brice on 10/30/17.
 */

public interface WeatherService {

//    http://api.openweathermap.org/data/2.5/weather?id=6455259&appid=1e3eb299758e145d834592538aeaa498

    @GET("weather")
    Call<WeatherResponse> getWeather(@Query("id") String id, @Query("appid") String appid);
}
