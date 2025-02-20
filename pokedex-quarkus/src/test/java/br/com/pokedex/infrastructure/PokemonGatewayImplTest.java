package br.com.pokedex.infrastructure;

import br.com.pokedex.domain.Pokemon;
import br.com.pokedex.domain.PokemonDetail;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PokemonGatewayImplTest {

    @Mock
    private Client client;

    @InjectMocks
    private PokemonGatewayImpl pokemonGateway;

    @Nested
    @DisplayName("When fetching all pokemons")
    class FetchAllPokemonsTest {

        @Test
        @DisplayName("Then it should return a list of pokemons")
        void testFetchAllPokemons() {
            String jsonResponse = "{\"results\":[{\"name\":\"bulbasaur\",\"url\":\"url1\"},{\"name\":\"charmander\",\"url\":\"url2\"}]}";

            WebTarget webTarget = mock(WebTarget.class);
            Invocation.Builder builder = mock(Invocation.Builder.class);

            when(client.target(PokemonGatewayImpl.API_URL)).thenReturn(webTarget);
            when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
            when(builder.get(String.class)).thenReturn(jsonResponse);

            List<Pokemon> pokemons = pokemonGateway.fetchAllPokemons();

            assertEquals(2, pokemons.size());
            assertEquals("bulbasaur", pokemons.get(0).getName());
            assertEquals("url1", pokemons.get(0).getUrl());
            assertEquals("charmander", pokemons.get(1).getName());
            assertEquals("url2", pokemons.get(1).getUrl());
        }

        @Test
        @DisplayName("Then it should return an empty list if no pokemons are found")
        void testFetchAllPokemonsEmpty() {
            String jsonResponse = "{\"results\":[]}";

            WebTarget webTarget = mock(WebTarget.class);
            Invocation.Builder builder = mock(Invocation.Builder.class);

            when(client.target(PokemonGatewayImpl.API_URL)).thenReturn(webTarget);
            when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
            when(builder.get(String.class)).thenReturn(jsonResponse);

            List<Pokemon> pokemons = pokemonGateway.fetchAllPokemons();

            assertEquals(0, pokemons.size());
        }
    }

    @Nested
    @DisplayName("When fetching a pokemon by name")
    class FetchPokemonByNameTest {

        @Test
        @DisplayName("Then it should return the pokemon details")
        void testFetchPokemonByName() {
            String jsonResponse = "{\"name\":\"Pikachu\",\"sprites\":{\"front_default\":\"imageUrl\"},\"base_experience\":100,\"weight\":60,\"height\":40,\"abilities\":[{\"ability\":{\"name\":\"static\"}}],\"types\":[{\"type\":{\"name\":\"electric\"}}]}";
            WebTarget webTarget = mock(WebTarget.class);
            Invocation.Builder builder = mock(Invocation.Builder.class);

            when(client.target(anyString())).thenReturn(webTarget);
            when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
            when(builder.get(String.class)).thenReturn(jsonResponse);

            PokemonDetail pokemon = pokemonGateway.fetchPokemonByName("pikachu");

            assertNotNull(pokemon);
            assertEquals("Pikachu", pokemon.getName());
            assertEquals("imageUrl", pokemon.getImageUrl());
            assertEquals(100, pokemon.getBaseExperience());
            assertEquals(60, pokemon.getWeight());
            assertEquals(40, pokemon.getHeight());
            assertEquals(1, pokemon.getAbilities().size());
            assertEquals("static", pokemon.getAbilities().get(0));
            assertEquals(1, pokemon.getTypes().size());
            assertEquals("electric", pokemon.getTypes().get(0));
        }

        @Test
        @DisplayName("Then it should return null if the pokemon is not found")
        void testFetchPokemonByNameNotFound() {
            String jsonResponse = "{}"; // Empty response for not found
            WebTarget webTarget = mock(WebTarget.class);
            Invocation.Builder builder = mock(Invocation.Builder.class);

            when(client.target(anyString())).thenReturn(webTarget);
            when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
            when(builder.get(String.class)).thenReturn(jsonResponse);

            PokemonDetail pokemon = pokemonGateway.fetchPokemonByName("pikachu");

            assertNull(pokemon);
        }

        @Test
        @DisplayName("Then it should return null if there is an error fetching the pokemon")
        void testFetchPokemonByNameError() {
            WebTarget webTarget = mock(WebTarget.class);
            Invocation.Builder builder = mock(Invocation.Builder.class);

            when(client.target(anyString())).thenReturn(webTarget);
            when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
            when(builder.get(String.class)).thenThrow(new RuntimeException("API Error"));

            PokemonDetail pokemon = pokemonGateway.fetchPokemonByName("pikachu");

            assertNull(pokemon);
        }
    }
}