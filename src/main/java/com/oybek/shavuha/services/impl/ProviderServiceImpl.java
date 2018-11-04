package com.oybek.shavuha.services.impl;

import com.oybek.shavuha.entities.AppId;
import com.oybek.shavuha.entities.Provider;
import com.oybek.shavuha.repositories.ProviderRepository;
import com.oybek.shavuha.services.ProviderService;
import com.sun.javafx.geom.Vec2f;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProviderServiceImpl implements ProviderService {

    private ProviderRepository providerRepository;

    ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public Provider save(Provider provider) {
        return providerRepository.save(provider);
    }

    public Optional<Provider> findById(AppId appId) {
        return providerRepository.findById(appId);
    }

    public Iterable<Provider> findAll() {
        return providerRepository.findAll();
    }

    public List<Provider> findNearest(Vec2f pos, int n) {
        return StreamSupport.stream(providerRepository.findAll().spliterator(), false)
                .map(provider -> new Pair<>(
                                pos.distanceSq(new Vec2f(provider.getLatitude(), provider.getLongitude())),
                                provider))
                .sorted(Comparator.comparing(Pair<Float, Provider>::getKey))
                .map(Pair::getValue)
                .limit(n)
                .collect(Collectors.toList());
    }
}
