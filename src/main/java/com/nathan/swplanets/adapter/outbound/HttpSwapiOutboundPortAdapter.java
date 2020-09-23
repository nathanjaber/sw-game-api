package com.nathan.swplanets.adapter.outbound;

import com.nathan.swplanets.domain.GetPlanetByNameResponse;
import com.nathan.swplanets.ports.outbound.SwapiOutboundPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;



@Service
public class HttpSwapiOutboundPortAdapter implements SwapiOutboundPort {

    private final RestTemplate restTemplate;
    @Value("${swapi.api.host}")
    private String SWAPI_BASE_URL;
    @Value("${swapi.api.planet.get}")
    private String GET_PLANET_PATH;

    public HttpSwapiOutboundPortAdapter(final RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public GetPlanetByNameResponse getPlanetByName(String name) {
        String url = UriComponentsBuilder
                .fromUriString(SWAPI_BASE_URL)
//                .fromUriString("https://swapi-test.free.beeceptor.com")
                .path(GET_PLANET_PATH)
                .queryParam("search", name)
                .build().toUriString();

        ResponseEntity<GetPlanetByNameResponse> exchange = restTemplate.exchange(url, HttpMethod.GET, null, GetPlanetByNameResponse.class);
        return exchange.getBody();
    }

}
