package example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherActivity extends AppCompatActivity {

    @BindView(R.id.data_tv)
    TextView textView;

    private String API_URL = "http://api.openweathermap.org/data/2.5/weather?id=6455259&appid=1e3eb299758e145d834592538aeaa498";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ButterKnife.bind(this);

        System.out.println("Getting key: " + getIntent().getExtras().getString("key"));

        String content = getIntent().getExtras().getString("key");
        textView.setText(content);
    }
}
