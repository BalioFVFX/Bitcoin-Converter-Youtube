package com.example.network.dagger.component;

import com.example.network.Bitcoin;
import com.example.network.dagger.module.NetworkModule;
import com.example.network.dagger.scope.NetworkScope;

import dagger.Component;
import io.reactivex.Observable;
@Component(modules = {NetworkModule.class})
@NetworkScope
public interface NetworkComponent {

    Observable<Bitcoin> getObservable();
}
