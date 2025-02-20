package br.com.pokedex.infrastructure;

import br.com.pokedex.domain.Pokemon;
import br.com.pokedex.domain.PokemonDetail;

import java.util.List;

public interface PokemonGateway {
    List<Pokemon> fetchAllPokemons();
    PokemonDetail fetchPokemonByName(String name);
}
