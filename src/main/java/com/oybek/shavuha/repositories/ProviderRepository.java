package com.oybek.shavuha.repositories;

import com.oybek.shavuha.entities.AppId;
import com.oybek.shavuha.entities.Provider;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProviderRepository extends CrudRepository<Provider, AppId> {
    List<Provider> findByLastName(String lastName);
}


