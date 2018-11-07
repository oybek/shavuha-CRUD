package com.oybek.shavuha.services;

import com.oybek.shavuha.entities.AppId;
import com.oybek.shavuha.entities.Deal;
import com.oybek.shavuha.entities.Provider;
import com.sun.javafx.geom.Vec2f;

import java.util.List;
import java.util.Optional;

public interface ProviderService {
    Provider save(Provider provider);
    Optional<Provider> findById(AppId appId);
    Iterable<Provider> findAll();
    List<Provider> findNearest(Vec2f pos, int n);
    List<Deal> getDeals(AppId appId);
}
