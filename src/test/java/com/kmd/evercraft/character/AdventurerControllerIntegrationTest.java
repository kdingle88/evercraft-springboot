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

import static com.kmd.evercraft.character.AdventurerAlignment.GOOD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdventurerControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    AdventurerRepository adventurerRepository;

    @Test
    public void createCharacter() throws Exception {

        Adventurer adventurer = new Adventurer(1L,"Cloud",GOOD);

        ResponseEntity<String> response = this.restTemplate.postForEntity("http://localhost:" + port + "/api/v1/characters", adventurer, String.class);
        JSONObject json = new JSONObject(response.getBody());
        System.out.println(response);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1",json.getString("id"));
        assertEquals("Cloud",json.getString("name"));
        assertEquals("GOOD",json.getString("alignment"));
        assertEquals("10",json.getString("armor"));
        assertEquals("5",json.getString("hitPoints"));
    }

    @Test
    public void charactersCanFight() throws Exception {
        Adventurer adventurer1 = new Adventurer(1L,"John",GOOD);
        Adventurer adventurer2 = new Adventurer(2L, "Kevin", GOOD);
        adventurerRepository.save(adventurer1);
        adventurerRepository.save(adventurer2);

        URI uri = new URI("http://localhost:" + port + "/api/v1/characters/fight?" + "attackingCharacterID=" + adventurer1.getId() + "&attackedCharacterID=" + adventurer2.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<Adventurer>> fightingCharactersRequest = new HttpEntity<>(List.of(adventurer1, adventurer2), headers);
        ResponseEntity<List<Adventurer>> responseEntity = this.restTemplate.exchange(
                uri,
                HttpMethod.PUT,
                fightingCharactersRequest,
                new ParameterizedTypeReference<>() {
                }
                );

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());

    }
}
