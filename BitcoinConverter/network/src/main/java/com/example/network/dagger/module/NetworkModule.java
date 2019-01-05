package com.example.network.dagger.module;

import com.example.network.Bitcoin;
import com.example.network.Ticker;
import com.example.network.dagger.scope.NetworkScope;
import com.google.gson.Gson;


import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Module
public class NetworkModule {

    @Provides
    @NetworkScope
    OkHttpClient getOkHttpClient(){
        return new OkHttpClient();
    }

    @Provides
    @NetworkScope
    Observable<Bitcoin> getObservable(final OkHttpClient client, final Gson gson){
        return Observable.create(new ObservableOnSubscribe<Bitcoin>() {
            @Override
            public void subscribe(ObservableEmitter<Bitcoin> emitter) throws Exception {
                Request req = new Request.Builder().url("https://api.cryptonator.com/api/ticker/btc-usd").build();
                Response res = client.newCall(req).execute();
                Ticker ticker = gson.fromJson(res.body().string(), Ticker.class);
                emitter.onNext(ticker.getBitcoin());
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Provides
    @NetworkScope
    Gson getGson(){
        return new Gson();
    }


}
