import axios from 'axios';
import PokemonRepository from '../../../src/infrastructure/repositories/pokemonRepository.js';

jest.mock('axios');

describe('When using the PokemonRepository', () => {
    describe('And when fetching the list of pokemons', () => {
        it('Then it should return the list of pokemons', async () => {
            const pokemons = [ { name: 'Pikachu' }, { name: 'Bulbasaur' } ];
            axios.get.mockResolvedValue({ data: pokemons });
            const result = await PokemonRepository.fetchPokemonList();
            expect(result).toEqual(pokemons); // Change expected output
            expect(axios.get).toHaveBeenCalledWith('http://localhost:8080/pokemon');
        });

        it('Then it should handle errors gracefully', async () => {
            axios.get.mockRejectedValue(new Error('Network error'));
            await expect(PokemonRepository.fetchPokemonList()).rejects.toThrow('Network error');
        });
    });

    describe('And when fetching pokemon details by name', () => {
        it('Then it should return pokemon details', async () => {
            const name = 'Pikachu';
            const pokemonDetails = { name: 'Pikachu', id: 25 };
            axios.get.mockResolvedValue({ data: pokemonDetails });
            const result = await PokemonRepository.fetchPokemonDetails(name);
            expect(result).toEqual(pokemonDetails);
            expect(axios.get).toHaveBeenCalledWith('http://localhost:8080/pokemon/Pikachu');
        });

        it('Then it should handle errors gracefully', async () => {
            const name = 'Pikachu';
            axios.get.mockRejectedValue(new Error('Network error'));
            await expect(PokemonRepository.fetchPokemonDetails(name)).rejects.toThrow('Network error');
        });
    });
});