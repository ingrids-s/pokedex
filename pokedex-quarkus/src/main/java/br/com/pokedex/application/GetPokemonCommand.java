package br.com.pokedex.application;

import br.com.pokedex.domain.Pokemon;
import br.com.pokedex.domain.PokemonDetail;

import java.util.List;

public interface GetPokemonCommand {
    List<Pokemon> execute();
    PokemonDetail execute(String name);
}