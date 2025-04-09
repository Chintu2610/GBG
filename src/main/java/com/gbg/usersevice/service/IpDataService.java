//package com.gbg.usersevice.service;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//
//import com.gbg.usersevice.model.IpData;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//
//
//@Service
//public class IpDataService {
//
//    @Value("${ipinfo.token}")
//    private String ipInfoToken;
//
//    
//    @Autowired
//    private RestTemplate restTemplate;
//    
//    public IpData getIpDataFromIpInfo(HttpServletRequest request) {
//        String ipAddress = request.getRemoteAddr();
//        String apiUrl = "https://ipinfo.io/" + ipAddress + "?token=" + ipInfoToken;
//        System.out.println("url : "+apiUrl);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
//        
//        try {
//            ResponseEntity<IpData> response = restTemplate.exchange(apiUrl, HttpMethod.GET, requestEntity, IpData.class);
//            return response.getBody();
//        } catch (HttpClientErrorException e) {
//            throw new RuntimeException("Failed to fetch IP data", e);
//        }
//    }
//}
