package example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.myapplication.models.WeatherResponse;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.input_et)
    EditText input;

    @BindView(R.id.submit_btn)
    Button submit;

    @BindView(R.id.api_btn)
    Button apiButton;

    private final String API_BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private final String API_KEY = "1e3eb299758e145d834592538aeaa498";

    private final String CITY_ID = "6455259";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        /*
        List<String> arr = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= 24) {
            arr.stream()
                    .collect(Collectors.toCollection());
        }
        */


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
            }
        });



        apiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                WeatherService service = retrofit.create(WeatherService.class);
                Call<WeatherResponse> response = service.getWeather(CITY_ID, API_KEY);
                response.enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                        WeatherResponse weatherResponse = response.body();
                        Toast.makeText(getApplicationContext(), response.body().getName(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {
                        System.out.println("ERROR");
                    }
                });
            }
        });
    }

    public void setData() {
        Bundle bundle = new Bundle();
        String content = input.getText().toString();
        bundle.putString("key", content);
        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
