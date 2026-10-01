package com.example.salesforce.controller;

import com.example.salesforce.model.DependencyQueryResponse;
import com.example.salesforce.model.DependencyRecord;
import com.example.salesforce.service.SalesforceAuthService;
import tools.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AccountController {

    @Value("${salesforce.login-url}")
    private String loginUrl;

    private final SalesforceAuthService authService;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AccountController(SalesforceAuthService authService, RestTemplate restTemplate) {
        this.authService = authService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/dependencies/search")
    public List<DependencyRecord> searchByField(@RequestParam String field) throws Exception {
        String token = authService.getAccessToken();

        String query = "SELECT+MetadataComponentName,MetadataComponentType,RefMetadataComponentName,RefMetadataComponentType+FROM+MetadataComponentDependency+LIMIT+2000";
        String url = loginUrl + "/services/data/v62.0/tooling/query/?q=" + query;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        DependencyQueryResponse parsed = objectMapper.readValue(response.getBody(), DependencyQueryResponse.class);

        return parsed.getRecords().stream()
                .filter(r -> r.getRefMetadataComponentName() != null &&
                        r.getRefMetadataComponentName().equalsIgnoreCase(field))
                .collect(Collectors.toList());
    }
}