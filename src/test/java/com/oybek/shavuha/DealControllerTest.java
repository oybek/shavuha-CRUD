package com.oybek.shavuha;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oybek.shavuha.entities.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DealControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void testDealSave() {
        Customer customer = createCustomer();
        Provider provider = createProvider();
        Deal savedDeal = null;

        // save deal
        {
            Deal deal = new Deal();
            deal.setCustomer(customer);
            deal.setProvider(provider);
            deal.setProduct(provider.getProducts());
            HttpEntity<Deal> entity = new HttpEntity<>(deal, headers);

            ResponseEntity<Deal> response = restTemplate.exchange(
                    createLocalURL("/deal/save")
                    , HttpMethod.POST
                    , entity
                    , Deal.class
            );

            savedDeal = response.getBody();
            assert (savedDeal != null);
        }

        // customer dealList check
        {
            HttpEntity<Customer> entity = new HttpEntity<>(customer, headers);

            ResponseEntity<ArrayList> response = restTemplate.exchange(
                    createLocalURL(String.format("/customer/getOpenDeals"))
                    , HttpMethod.POST
                    , entity
                    , ArrayList.class
            );

            assert(response.getBody() != null);
            assert(response.getBody().size() == 1);
            List<Deal> responseList = new ObjectMapper().convertValue(response.getBody()
                    , new TypeReference<List<Deal>>(){});
            assert(savedDeal.equals(responseList.get(0)));
        }

        // provider dealList check
        {
            HttpEntity<Provider> entity = new HttpEntity<>(provider, headers);

            ResponseEntity<ArrayList> response = restTemplate.exchange(
                    createLocalURL(String.format("/provider/getOpenDeals"))
                    , HttpMethod.POST
                    , entity
                    , ArrayList.class
            );

            assert(response.getBody() != null);
            assert(response.getBody().size() == 1);
            List<Deal> responseList = new ObjectMapper().convertValue(response.getBody()
                    , new TypeReference<List<Deal>>(){});
            assert(savedDeal.equals(responseList.get(0)));
        }
    }

    private Provider findProvider(AppId appId) {
        HttpEntity<Provider> entity = new HttpEntity<>(null, headers);

        ResponseEntity<Provider> response = restTemplate.exchange(
                createLocalURL(String.format("/provider/find?app=%s&id=%s", appId.getApp(), appId.getId()))
                , HttpMethod.GET
                , entity
                , Provider.class
        );

        return response.getBody();
    }

    private Customer createCustomer() {
        Customer customer = new Customer(
                new AppId("app", "0")
                , "test"
                , "test"
        );

        HttpEntity<Customer> entity = new HttpEntity<>(customer, headers);

        ResponseEntity<Customer> response = restTemplate.exchange(
                createLocalURL("/customer/save")
                , HttpMethod.POST
                , entity
                , Customer.class
        );

        return response.getBody();
    }

    private Provider createProvider() {
        Provider provider = new Provider(
                new AppId("app", "1")
                , "test"
                , "test"
                , "test"
                , 0.0f
                , 0.0f
                , null
                , new HashSet<>(Collections.singletonList(new Product("test", 100)))
                , null
        );

        HttpEntity<Provider> entity = new HttpEntity<>(provider, headers);

        ResponseEntity<Provider> response = restTemplate.exchange(
                createLocalURL("/provider/save")
                , HttpMethod.POST
                , entity
                , Provider.class
        );

        return response.getBody();
    }

    private String createLocalURL(String uri) {
        return "http://localhost:" + port + uri;
    }
}
