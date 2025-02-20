package br.com.pokedex.application;

import br.com.pokedex.domain.Pokemon;
import br.com.pokedex.domain.PokemonDetail;
import br.com.pokedex.infrastructure.PokemonGateway;
import org.junit.jupiter.api.BeforeEach;
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

public class GetPokemonCommandImplTest {

    @InjectMocks
    private GetPokemonCommandImpl getPokemonCommand;

    @Mock
    private PokemonGateway pokemonGateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("When method execute is called successfully")
    class ExecuteMethodTest {

        @Test
        @DisplayName("Then it should fetch all pokemons")
        public void testExecuteFetchAllPokemons() {
            Pokemon pokemon1 = new Pokemon("Pikachu", "https://pokeapi.co/api/v2/pokemon/25/");
            Pokemon pokemon2 = new Pokemon("Bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/");
            List<Pokemon> expectedPokemons = Arrays.asList(pokemon1, pokemon2);

            when(pokemonGateway.fetchAllPokemons()).thenReturn(expectedPokemons);

            List<Pokemon> actualPokemons = getPokemonCommand.execute();

            assertEquals(expectedPokemons, actualPokemons);
        }

        @Nested
        @DisplayName("And fetching a pokemon by name")
        class FetchPokemonByNameTest {

            @Test
            @DisplayName("Then it should return the pokemon details")
            public void testExecuteFetchPokemonByName() {
                String pokemonName = "Pikachu";
                PokemonDetail expectedDetail = new PokemonDetail(
                        pokemonName,
                        "https://pokeapi.co/media/sprites/pokemon/25.png",
                        112,
                        60,
                        40,
                        Arrays.asList("Static", "Lightning Rod"),
                        Arrays.asList("Electric")
                );

                when(pokemonGateway.fetchPokemonByName(pokemonName)).thenReturn(expectedDetail);

                PokemonDetail actualDetail = getPokemonCommand.execute(pokemonName);

                assertEquals(expectedDetail, actualDetail);
            }

            @Test
            @DisplayName("Then it should return null if the pokemon is not found")
            public void testExecuteFetchPokemonByNameNotFound() {
                String pokemonName = "Unknown";

                when(pokemonGateway.fetchPokemonByName(pokemonName)).thenReturn(null);

                PokemonDetail actualDetail = getPokemonCommand.execute(pokemonName);

                assertEquals(null, actualDetail);
            }
        }
    }
}