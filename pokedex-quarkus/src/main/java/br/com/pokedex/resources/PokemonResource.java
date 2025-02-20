package br.com.pokedex.resources;

import br.com.pokedex.application.GetPokemonCommand;
import br.com.pokedex.domain.Pokemon;
import br.com.pokedex.domain.PokemonDetail;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/pokemon")
public class PokemonResource {

    @Inject
    GetPokemonCommand getPokemonCommand;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pokemon> getAllPokemons() {
        return getPokemonCommand.execute();
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public PokemonDetail getPokemonByName(@PathParam("name") String name) {
        return getPokemonCommand.execute(name);
    }
}