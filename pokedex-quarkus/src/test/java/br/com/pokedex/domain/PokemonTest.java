package br.com.pokedex.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokemonTest {

    @Nested
    @DisplayName("When a Pokemon is created")
    class PokemonCreationTest {

        @Test
        @DisplayName("Then it should return the correct name and URL")
        public void testPokemonConstructorAndGetters() {
            Pokemon pokemon = new Pokemon("Pikachu", "http://example.com/pikachu");

            assertEquals("Pikachu", pokemon.getName(), "The name should match the constructor input");
            assertEquals("http://example.com/pikachu", pokemon.getUrl(), "The URL should match the constructor input");
        }
    }
}