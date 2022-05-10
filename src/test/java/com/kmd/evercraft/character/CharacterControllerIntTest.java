package com.kmd.evercraft.character;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CharacterControllerIntTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createCharacter() throws Exception {

        Character character = new Character(1L,"Cloud","GOOD");

        ResponseEntity<String> response = this.restTemplate.postForEntity("http://localhost:" + port + "/api/v1/characters", character, String.class);

        JSONObject json = new JSONObject(response.getBody());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertEquals("1",json.getString("id"));
        assertEquals("Cloud",json.getString("name"));
        assertEquals("GOOD",json.getString("alignment"));
        assertEquals("10",json.getString("armor"));
        assertEquals("5",json.getString("hitPoints"));
    }

    @Test
    public void charactersCanFight() throws Exception {
        Character character1 = new Character();
        Character character2 = new Character();

        URI uri = new URI("http://localhost:" + port + "/api/v1/characters/fight");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<Character>> fightingCharactersRequest = new HttpEntity<>(List.of(character1, character2), headers);


        ResponseEntity<List<Character>> responseEntity = this.restTemplate.exchange(
                uri,
                HttpMethod.PUT,
                fightingCharactersRequest,
                new ParameterizedTypeReference<>() {
                }
                );

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());

    }
}
