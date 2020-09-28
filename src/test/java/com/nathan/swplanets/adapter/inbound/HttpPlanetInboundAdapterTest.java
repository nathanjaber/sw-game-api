package com.nathan.swplanets.adapter.inbound;

import com.nathan.swplanets.SwplanetsApplication;
import com.nathan.swplanets.domain.GetPlanetByNameResponse;
import com.nathan.swplanets.domain.Planet;
import com.nathan.swplanets.domain.PlanetDTO;
import com.nathan.swplanets.exception.ExceptionDTO;
import com.nathan.swplanets.mock.PlanetMock;
import com.nathan.swplanets.ports.inbound.PlanetInboundPort;
import com.nathan.swplanets.repository.PlanetRepository;
import lombok.var;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.array;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SwplanetsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HttpPlanetInboundAdapterTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PlanetRepository planetRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlanetInboundPort planetInboundPort;

    @Before
    public void setup() {
        this.planetRepository.deleteAll();
    }

//    @Test
    public void shouldReturnBadRequestWhenIdContentTypeIsInvalid() throws Exception{
        Planet planet = PlanetMock.success();

        String uri = UriComponentsBuilder
                .fromUriString("/swplanets/v1/planets")
                .build().encode()
                .toUriString();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        var entity = new HttpEntity<>(headers);

        ResponseEntity<ExceptionDTO> response = restTemplate.exchange(uri, HttpMethod.POST,
                entity, ExceptionDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.BAD_REQUEST.value());

        assertThat(response.getBody().getMessage()).isEqualTo("id não pode estar em branco");
    }

    @Test
    public void shouldReturnAnEmptyList() throws Exception{
        String uri = UriComponentsBuilder
                .fromUriString("/swplanets/v1/planets")
                .build().encode()
                .toUriString();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var entity = new HttpEntity<>(headers);

        ResponseEntity<List<PlanetDTO>> response = restTemplate.exchange(uri,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<PlanetDTO>>() {});

        assertThat(Objects.requireNonNull(response.getBody()).size()).isEqualTo(0);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

//    @Test
    public void shouldReturnAPlanet() throws Exception{

        Planet planet = PlanetMock.success();
        String planetId = this.planetInboundPort.createPlanet(planet).get_id();

        String uri = UriComponentsBuilder
                .fromUriString("/swplanets/v1/planets/" + planetId)
                .build().encode()
                .toUriString();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var entity = new HttpEntity<>(headers);

        ResponseEntity<Planet> response = restTemplate.exchange(uri,
                HttpMethod.GET,
                entity,
                Planet.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);

        assertThat(response.getBody().getName()).isEqualTo("Tatooine");
        assertThat(response.getBody().getName()).isEqualTo("arid");
        assertThat(response.getBody().getName()).isEqualTo("desert");
        assertThat(response.getBody().getName()).isEqualTo(5);
    }
}
