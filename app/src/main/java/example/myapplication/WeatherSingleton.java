package example.myapplication;

/**
 * Created by Brice on 10/30/17.
 */

public class WeatherSingleton {
    private static final WeatherSingleton ourInstance = new WeatherSingleton();

    public static WeatherSingleton getInstance() {
        return ourInstance;
    }

    private WeatherSingleton() {
    }
}
