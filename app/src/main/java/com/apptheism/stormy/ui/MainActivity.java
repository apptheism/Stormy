package com.apptheism.stormy.ui;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apptheism.stormy.R;
import com.apptheism.stormy.databinding.ActivityMainBinding;
import com.apptheism.stormy.weather.Current;
import com.apptheism.stormy.weather.Forecast;
import com.apptheism.stormy.weather.Hour;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Forecast mForecast;

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final  String API_KEY = "bb4f421b18989600cca6cf63d56c9520";

    final private double latitude = 47.6779496;
    final private double longitude = 9.1732384;

    private String forecastKonstanzURL;

    private OkHttpClient okHttpClient;

    private Call call;

    private TextView tvDegreeValue;
    private TextView darkSky;

    private ImageView ivRefresh;
    private ImageView ivIcon;

    private RotateAnimation rotateAnimation;

    private String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ivRefresh = findViewById(R.id.ivRefresh);

        rotateAnimation = new RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setDuration(1000);

        language = getResources().getString(R.string.language);

        getForecast(latitude, longitude, language);
    }

    private Forecast parseForecastData(String jsonData) throws JSONException{

        Forecast forecast = new Forecast();
        forecast.setCurrent(getCurrentDetails(jsonData));
        forecast.setHourlyForecast(getHourlyForecast(jsonData));
        return forecast;
    }

    private Hour[] getHourlyForecast(String jsonData) throws JSONException {

        JSONObject forecast = new JSONObject(jsonData);
        String timeZone = forecast.getString("timezone");
        JSONObject hourly = forecast.getJSONObject("hourly");
        JSONArray hourlyData = hourly.getJSONArray("data");

        Hour[] hours = new Hour[hourlyData.length()];

        for(int i = 0; i < hourlyData.length(); i++){

            JSONObject jsonHour = hourlyData.getJSONObject(i);
            Hour hour = new Hour();

            hour.setSummary(jsonHour.getString("summary"));
            hour.setIcon(jsonHour.getString("icon"));
            hour.setTemperature(jsonHour.getDouble("temperature"));
            hour.setTimezone(forecast.getString("timezone"));

            hours[i] = hour;

        }
        return hours;
    }

    private Current getCurrentDetails(String jsonData) throws  JSONException{

            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject currently = jsonObject.getJSONObject("currently");

            Current current = new Current();

            current.setHumidity(currently.getDouble("humidity"));
            current.setTime(currently.getLong("time"));
            current.setTimezone(jsonObject.getString("timezone"));
            current.setIcon(currently.getString("icon"));
            current.setLocationLabel("Alcatraz/California");
            current.setPrecipChance(currently.getDouble("precipProbability"));
            current.setSummary(currently.getString("summary"));
            current.setTemperature(currently.getDouble("temperature"));

            Log.d(TAG, current.getFormattedTime());

            return current;

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null){
            isAvailable = true;
        } else{
            Toast.makeText(this, R.string.network_state_toast_message, Toast.LENGTH_SHORT).show();
        }
        return isAvailable;
    }

    private void alertUserAboutError() {
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        alertDialogFragment.show(getFragmentManager(), "error_dialog");
    }

    private void getForecast(double latitude, double longitude, String lang){

        final ActivityMainBinding binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        tvDegreeValue = findViewById(R.id.tvTemperatureValue);

        //darkSky = findViewById(R.id.tvDarkSky);
        //darkSky.setMovementMethod(LinkMovementMethod.getInstance());

        ivIcon = findViewById(R.id.ivIcon);

        okHttpClient = new OkHttpClient();

        forecastKonstanzURL = "https://api.darksky.net/forecast/" + API_KEY + "/" + latitude + "," + longitude + "?lang=" + lang;

        if (isNetworkAvailable()) {


            Request request = new Request.Builder()
                    .url(forecastKonstanzURL)
                    .build();

            call = okHttpClient.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    try {

                        String jsonData = response.body().string();

                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {

                            mForecast = parseForecastData(jsonData);

                            Current mCurrent = mForecast.getCurrent();

                            final Current displayWeather = new Current(
                                    mCurrent.getLocationLabel(),
                                    mCurrent.getIcon(),
                                    mCurrent.getTime(),
                                    mCurrent.getTemperature(),
                                    mCurrent.getHumidity(),
                                    mCurrent.getPrecipChance(),
                                    mCurrent.getSummary(),
                                    mCurrent.getTimezone(),
                                    mCurrent.getIconId()
                            );

                            binding.setWeather(displayWeather);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Drawable drawable = getResources().getDrawable(displayWeather.getIconId());
                                    ivIcon.setImageDrawable(drawable);
                                }
                            });
                        } else {
                            alertUserAboutError();
                        }

                    } catch (IOException e) {
                        Log.e(TAG, "IO exception caught: ", e);
                    } catch (JSONException j){
                        Log.e(TAG, "JSON exception caught: ", j);

                    }

                }
            });
        }
    }

    public void refreshOnClick(View view){
        view.startAnimation(rotateAnimation);
        getForecast(latitude, longitude, language);
    }

    public void hourlyOnClick(View view){

        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            List<Hour> hours = Arrays.asList(mForecast.getHourlyForecast());

            getWindow().setExitTransition(new Explode());

            Intent intent = new Intent(this, HourlyForecastActivity.class);

            intent.putExtra("HourlyList", (Serializable) hours);

            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            // Swap without transition
        }
    }
}
