package br.com.pokedex.infrastructure;

import br.com.pokedex.domain.Pokemon;
import br.com.pokedex.domain.PokemonDetail;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@ApplicationScoped
public class PokemonGatewayImpl implements PokemonGateway {

    public static final String API_URL = "https://pokeapi.co/api/v2/pokemon?limit=500&offset=0";
    private final Client client = ClientBuilder.newClient();

    @Override
    public List<Pokemon> fetchAllPokemons() {
        String jsonResponse;
        try {
            jsonResponse = client.target(API_URL)
                    .request(MediaType.APPLICATION_JSON)
                    .get(String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode resultsNode = rootNode.path("results");

            return StreamSupport.stream(resultsNode.spliterator(), false)
                    .map(pokemonNode -> new Pokemon(
                            pokemonNode.path("name").asText(),
                            pokemonNode.path("url").asText()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public PokemonDetail fetchPokemonByName(String name) {
        String apiUrl = "https://pokeapi.co/api/v2/pokemon/" + name.toLowerCase();
        String jsonResponse;
        try {
            jsonResponse = client.target(apiUrl)
                    .request(MediaType.APPLICATION_JSON)
                    .get(String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            String fetchedName = rootNode.path("name").asText();
            if (!fetchedName.equals(name.toLowerCase())) {
                return null;
            }

            String imageUrl = rootNode.path("sprites").path("front_default").asText();
            int baseExperience = rootNode.path("base_experience").asInt();
            int weight = rootNode.path("weight").asInt();
            int height = rootNode.path("height").asInt();

            List<String> abilities = StreamSupport.stream(rootNode.path("abilities").spliterator(), false)
                    .map(abilityNode -> abilityNode.path("ability").path("name").asText())
                    .collect(Collectors.toList());

            List<String> types = StreamSupport.stream(rootNode.path("types").spliterator(), false)
                    .map(typeNode -> typeNode.path("type").path("name").asText())
                    .collect(Collectors.toList());

            return new PokemonDetail(
                    fetchedName,
                    imageUrl,
                    baseExperience,
                    weight,
                    height,
                    abilities,
                    types
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}