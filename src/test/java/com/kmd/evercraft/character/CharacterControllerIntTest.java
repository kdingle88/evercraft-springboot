package com.kmd.evercraft.character;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CharacterControllerIntTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createCharacter() throws Exception {

        Character character = new Character(1L,"Cloud","GOOD");

        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/api/v1/characters",character,String.class))
                .isEqualTo("{\"id\":1,\"name\":\"Cloud\",\"alignment\":\"GOOD\"}");
    }
}
