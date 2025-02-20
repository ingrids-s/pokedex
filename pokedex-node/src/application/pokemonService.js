import PokemonRepository from '../infrastructure/repositories/pokemonRepository.js';
import Pokemon from '../domain/models/pokemonModel.js';

class PokemonService {
    async getAllPokes() {
        const pokemonList = await PokemonRepository.fetchPokemonList();
        const detailedPokemons = await Promise.all(
            pokemonList.map(async (pokemon) => {
                const details = await PokemonRepository.fetchPokemonDetails(pokemon.name);
                return new Pokemon(
                    details.name,
                    details.imageUrl
                );
            })
        );
        return detailedPokemons;
    }

    async getPokeByName(name) {
        const details = await PokemonRepository.fetchPokemonDetails(name);
        return new Pokemon(
            details.name,
            details.imageUrl,
            details.baseExperience,
            details.weight / 10,
            details.height / 10,
            details.abilities,
            details.types
        );
    }
}

export default new PokemonService();