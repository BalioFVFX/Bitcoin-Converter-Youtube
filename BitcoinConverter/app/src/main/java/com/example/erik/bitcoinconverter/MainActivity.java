package com.example.erik.bitcoinconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.network.dagger.component.DaggerNetworkComponent;
import com.example.network.dagger.component.NetworkComponent;

import io.reactivex.disposables.Disposable;
public class MainActivity extends AppCompatActivity {

    private TextView btcTextView;
    private Button btcButton;
    private Disposable disposable;
    private NetworkComponent networkComponent;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
        this.setupListeners();

    }


    private void init(){
        this.networkComponent = DaggerNetworkComponent.create();
        this.btcButton = (Button)this.findViewById(R.id.btn_btc);
        this.btcTextView = (TextView)this.findViewById(R.id.tv_btc);
        this.progressBar = (ProgressBar)this.findViewById(R.id.progress_circular);
    }

    private void setupListeners(){
        this.btcButton.setOnClickListener(v -> {
            this.disposable = networkComponent.getObservable().doOnSubscribe(d -> {
                this.btcTextView.setVisibility(View.INVISIBLE);
                this.btcButton.setVisibility(View.INVISIBLE);
                this.progressBar.setVisibility(View.VISIBLE);
            }).subscribe(bitcoin -> {
                this.btcTextView.setText(String.format("Currently 1 BTC is %s USD", bitcoin.getPrice()));
                this.btcTextView.setVisibility(View.VISIBLE);
                this.btcButton.setVisibility(View.VISIBLE);
                this.progressBar.setVisibility(View.INVISIBLE);
            });
        });
    }

    @Override
    protected void onDestroy() {
        this.disposable.dispose();
        super.onDestroy();
    }
}
