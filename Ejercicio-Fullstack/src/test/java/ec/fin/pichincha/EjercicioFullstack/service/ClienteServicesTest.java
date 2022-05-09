package ec.fin.pichincha.EjercicioFullstack.service;

import ec.fin.pichincha.EjercicioFullstack.model.Movimientos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ClienteServicesTest {

//    private TestRestTemplate restTemplate;
//    @Autowired
//    private RestTemplateBuilder restTemplateBuilder;
//
//    @LocalServerPort
//    private int port;
//
//    @BeforeEach
//    void setUp() {
//        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
//        restTemplate = new TestRestTemplate(restTemplateBuilder);
//    }
//
//    @Test
//    void listar() {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//        String json = "";
//
//        ResponseEntity<Movimientos> response = restTemplate.getForEntity("/api/movimientos/listar/1", Movimientos.class);
//        response.getStatusCode();
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals("123", response.getBody());
//
//    }
}