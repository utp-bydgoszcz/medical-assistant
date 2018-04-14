package pl.edu.utp.medicalassistant.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.edu.utp.medicalassistant.model.interceptors.HeaderRequestInterceptor;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AndroidPushNotificationService {

    @Value("${firebase.server.key}")
    private String FIREBASE_SERVER_KEY;

    @Value("${firebase.api.url}")
    private String FIREBASE_API_URL;

    @Async
    public CompletableFuture<String> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }

}
