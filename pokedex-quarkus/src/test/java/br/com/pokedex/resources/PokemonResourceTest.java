package br.com.pokedex.resources;

import br.com.pokedex.application.GetPokemonCommand;
import br.com.pokedex.domain.Pokemon;
import br.com.pokedex.domain.PokemonDetail;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PokemonResourceTest {

    @Mock
    GetPokemonCommand getPokemonCommand;

    @InjectMocks
    PokemonResource pokemonResource;

    public PokemonResourceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("When fetching all pokemons")
    class FetchAllPokemonsTest {

        @Test
        @DisplayName("Then it should return a list of pokemons")
        public void testGetAllPokemons() {
            Pokemon pokemon1 = new Pokemon("Pikachu", "url1");
            Pokemon pokemon2 = new Pokemon("Charmander", "url2");
            List<Pokemon> pokemons = Arrays.asList(pokemon1, pokemon2);

            when(getPokemonCommand.execute()).thenReturn(pokemons);

            List<Pokemon> result = pokemonResource.getAllPokemons();

            assertEquals(2, result.size());
            assertEquals("Pikachu", result.get(0).getName());
            assertEquals("Charmander", result.get(1).getName());
        }
    }

    @Nested
    @DisplayName("When fetching a pokemon by name")
    class FetchPokemonByNameTest {

        @Test
        @DisplayName("Then it should return the pokemon details")
        public void testGetPokemonByName() {
            String name = "Pikachu";
            PokemonDetail pokemonDetail = new PokemonDetail(
                    "Pikachu",
                    "imageUrl",
                    112,
                    60,
                    4,
                    Arrays.asList("Static", "Lightning Rod"),
                    Arrays.asList("Electric")
            );

            when(getPokemonCommand.execute(name)).thenReturn(pokemonDetail);

            PokemonDetail result = pokemonResource.getPokemonByName(name);

            assertEquals("Pikachu", result.getName());
            assertEquals("imageUrl", result.getImageUrl());
            assertEquals(112, result.getBaseExperience());
            assertEquals(60, result.getWeight());
            assertEquals(4, result.getHeight());
            assertEquals(Arrays.asList("Static", "Lightning Rod"), result.getAbilities());
            assertEquals(Arrays.asList("Electric"), result.getTypes());
        }
    }
}