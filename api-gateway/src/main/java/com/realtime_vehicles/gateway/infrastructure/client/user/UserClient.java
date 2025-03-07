package com.realtime_vehicles.gateway.infrastructure.client.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public User getUser(Long id) {
        String url = "http://users-service/user-app/api/v1/user/" + id;  

        ResponseEntity<User> response = restTemplate.exchange(
                url, 
                HttpMethod.GET, 
                null, 
                new ParameterizedTypeReference<User>() {}
        );

        return response.getBody();  
    }

    public User createUser(User user) {
        String url = "http://users-service/user-app/api/v1/user/";  

        HttpEntity<User> requestEntity = new HttpEntity<>(user);

        ResponseEntity<User> response = restTemplate.exchange(
                url, 
                HttpMethod.POST, 
                requestEntity,  
                new ParameterizedTypeReference<User>() {}
        );

        return response.getBody();  
    }

    public User updateUser(User user) {
        String url = "http://users-service/user-app/api/v1/user/";  

        HttpEntity<User> requestEntity = new HttpEntity<>(user);

        ResponseEntity<User> response = restTemplate.exchange(
                url, 
                HttpMethod.PUT, 
                requestEntity,  
                new ParameterizedTypeReference<User>() {}
        );

        return response.getBody();  
    }

    public void deleteUser(Long id) {
        String url = "http://users-service/user-app/api/v1/user/" + id;  

        restTemplate.exchange(
                url, 
                HttpMethod.DELETE, 
                null,  
                Void.class
        );
    }

}
