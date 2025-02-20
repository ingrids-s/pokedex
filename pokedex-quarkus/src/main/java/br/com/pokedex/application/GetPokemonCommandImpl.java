package br.com.pokedex.application;


import br.com.pokedex.domain.Pokemon;
import br.com.pokedex.domain.PokemonDetail;
import br.com.pokedex.infrastructure.PokemonGateway;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.List;

@Singleton
public class GetPokemonCommandImpl implements GetPokemonCommand {
    @Inject
    PokemonGateway pokemonGateway;

    @Override
    public List<Pokemon> execute() {
        return pokemonGateway.fetchAllPokemons();
    }

    @Override
    public PokemonDetail execute(String name) {
        return pokemonGateway.fetchPokemonByName(name);
    }
}