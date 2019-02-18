package com.example.user.retrofitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user.retrofitapp.model.Items;
import com.example.user.retrofitapp.model.Jadwal;
import com.example.user.retrofitapp.rest.ApiClient;
import com.example.user.retrofitapp.rest.ApiInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tvSubuh) TextView tvShubuh;
    @BindView(R.id.tvZuhur) TextView tvZuhur;
    @BindView(R.id.tvAshar) TextView tvAshar;
    @BindView(R.id.tvMaghrib) TextView tvMaghrib;
    @BindView(R.id.tvIsya) TextView tvIsya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnLoadData)
    void actionLoadData(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Items> call = apiService.getJadwalSholat("jakarta");

        call.enqueue(new Callback<Items>() {
            @Override
            public void onResponse(Call<Items> call, Response<Items> response) {
                List<Jadwal> jadwal = response.body().getItems();
                loadData(jadwal);
            }

            @Override
            public void onFailure(Call<Items> call, Throwable t) {

            }
        });

    }

    private void loadData(List<Jadwal> jadwal) {
    for(Jadwal data : jadwal){
        tvShubuh.setText(data.getSubuh());
        tvZuhur.setText(data.getZuhur());
        tvAshar.setText(data.getAshar());
        tvMaghrib.setText(data.getMaghrib());
        tvIsya.setText(data.getIsya());
    }
    }

}
