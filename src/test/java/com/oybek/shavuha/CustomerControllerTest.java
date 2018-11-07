package com.oybek.shavuha;

import com.oybek.shavuha.entities.AppId;
import com.oybek.shavuha.entities.Customer;
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

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void testCustomerSave() {
        Customer customer = new Customer(
                new AppId("app", "0")
                , "John"
                , "Smith"
        );

        HttpEntity<Customer> entity = new HttpEntity<>(customer, headers);

        ResponseEntity<Customer> response = restTemplate.exchange(
                createLocalURL("/customer/save")
                , HttpMethod.POST
                , entity
                , Customer.class
        );

        Customer returnedCustomer = response.getBody();

        assert(returnedCustomer != null);
        assert(customer.getAppId().equals(returnedCustomer.getAppId()));
        assert(customer.getFirstName().equals(returnedCustomer.getFirstName()));
        assert(customer.getLastName().equals(returnedCustomer.getLastName()));
    }

    @Test
    public void testCustomerFind() {
        // save
        {
            Customer customer = new Customer(
                    new AppId("app", "0")
                    , "John"
                    , "Smith"
            );

            HttpEntity<Customer> entity = new HttpEntity<>(customer, headers);

            ResponseEntity<Customer> response = restTemplate.exchange(
                    createLocalURL("/customer/save")
                    , HttpMethod.POST
                    , entity
                    , Customer.class
            );
        }

        // find
        {
            HttpEntity<Customer> entity = new HttpEntity<>(null, headers);

            ResponseEntity<Customer> response = restTemplate.exchange(
                    createLocalURL("/customer/find?app=app&id=0")
                    , HttpMethod.GET
                    , entity
                    , Customer.class
            );

            Customer returnedCustomer = response.getBody();

            assert (returnedCustomer != null);
            assert (new AppId("app", "0").equals(returnedCustomer.getAppId()));
            assert ("John".equals(returnedCustomer.getFirstName()));
            assert ("Smith".equals(returnedCustomer.getLastName()));
        }
    }

    @Test
    public void testNoOpenDealsForNewCustomer() {
        Customer customer = new Customer();
        customer.setAppId(new AppId("app", "0"));

        HttpEntity<Customer> entity = new HttpEntity<>(customer, headers);

        ResponseEntity<ArrayList> response = restTemplate.exchange(
                createLocalURL("/customer/getOpenDeals")
                , HttpMethod.POST
                , entity
                , ArrayList.class
        );

        assert(response.getBody() != null);
        assert(response.getBody().isEmpty());
    }

    private String createLocalURL(String uri) {
        return "http://localhost:" + port + uri;
    }
}
