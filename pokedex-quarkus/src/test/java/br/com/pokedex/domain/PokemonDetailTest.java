package br.com.pokedex.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokemonDetailTest {

    @Nested
    @DisplayName("When a PokemonDetail is created")
    class PokemonDetailCreationTest {

        @Test
        @DisplayName("Then it should return the correct values from the constructor")
        public void testPokemonDetailConstructorAndGetters() {
            // When
            PokemonDetail pokemonDetail = new PokemonDetail(
                    "Pikachu",
                    "http://example.com/pikachu.png",
                    112,
                    60,
                    4,
                    Arrays.asList("Static", "Lightning Rod"),
                    Arrays.asList("Electric")
            );

            // Then
            assertEquals("Pikachu", pokemonDetail.getName());
            assertEquals("http://example.com/pikachu.png", pokemonDetail.getImageUrl());
            assertEquals(112, pokemonDetail.getBaseExperience());
            assertEquals(60, pokemonDetail.getWeight());
            assertEquals(4, pokemonDetail.getHeight());
            assertEquals(Arrays.asList("Static", "Lightning Rod"), pokemonDetail.getAbilities());
            assertEquals(Arrays.asList("Electric"), pokemonDetail.getTypes());
        }
    }

    @Nested
    @DisplayName("When setting values for PokemonDetail")
    class PokemonDetailSettersTest {

        @Test
        @DisplayName("Then it should update the values correctly")
        public void testPokemonDetailSetters() {
            // Given
            PokemonDetail pokemonDetail = new PokemonDetail(
                    "Pikachu",
                    "http://example.com/pikachu.png",
                    112,
                    60,
                    4,
                    Arrays.asList("Static", "Lightning Rod"),
                    Arrays.asList("Electric")
            );

            // When
            pokemonDetail.setName("Charmander");
            pokemonDetail.setImageUrl("http://example.com/charmander.png");
            pokemonDetail.setBaseExperience(100);
            pokemonDetail.setWeight(85);
            pokemonDetail.setHeight(6);
            pokemonDetail.setAbilities(Arrays.asList("Blaze", "Solar Power"));
            pokemonDetail.setTypes(Arrays.asList("Fire"));

            // Then
            assertEquals("Charmander", pokemonDetail.getName());
            assertEquals("http://example.com/charmander.png", pokemonDetail.getImageUrl());
            assertEquals(100, pokemonDetail.getBaseExperience());
            assertEquals(85, pokemonDetail.getWeight());
            assertEquals(6, pokemonDetail.getHeight());
            assertEquals(Arrays.asList("Blaze", "Solar Power"), pokemonDetail.getAbilities());
            assertEquals(Arrays.asList("Fire"), pokemonDetail.getTypes());
        }
    }
}