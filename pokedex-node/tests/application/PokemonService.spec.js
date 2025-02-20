import PokemonService from '../../src/application/pokemonService.js';
import PokemonRepository from '../../src/infrastructure/repositories/pokemonRepository.js';
import Pokemon from '../../src/domain/models/pokemonModel.js';

jest.mock('../../src/infrastructure/repositories/pokemonRepository.js');

describe('When using PokemonService', () => {
    beforeEach(() => {
        jest.clearAllMocks();
    });

    describe('And when getting all pokemons', () => {
        it('Then it should return a list of Pokemon instances', async () => {
            const mockPokemonList = [{ name: 'pikachu' }, { name: 'bulbasaur' }];
            const mockPokemonDetails = {
                name: 'pikachu',
                imageUrl: 'url_to_pikachu',
            };
            PokemonRepository.fetchPokemonList.mockResolvedValue(mockPokemonList);
            PokemonRepository.fetchPokemonDetails.mockResolvedValue(mockPokemonDetails);

            const pokemons = await PokemonService.getAllPokes();

            expect(PokemonRepository.fetchPokemonList).toHaveBeenCalled();
            expect(PokemonRepository.fetchPokemonDetails).toHaveBeenCalledTimes(mockPokemonList.length);
            expect(pokemons).toEqual([
                new Pokemon('pikachu', 'url_to_pikachu'),
                new Pokemon('pikachu', 'url_to_pikachu')
            ]);
        });
    });

    describe('And when getting a pokemon by name', () => {
        it('Then it should return a Pokemon instance with detailed information', async () => {
            const mockDetails = {
                name: 'bulbasaur',
                imageUrl: 'url_to_bulbasaur',
                baseExperience: 64,
                weight: 69,
                height: 7,
                abilities: ['overgrow', 'chlorophyll'],
                types: ['grass', 'poison']
            };
            PokemonRepository.fetchPokemonDetails.mockResolvedValue(mockDetails);

            const pokemon = await PokemonService.getPokeByName('bulbasaur');

            expect(PokemonRepository.fetchPokemonDetails).toHaveBeenCalledWith('bulbasaur');
            expect(pokemon).toEqual(new Pokemon(
                'bulbasaur',
                'url_to_bulbasaur',
                64,
                6.9,
                0.7,
                ['overgrow', 'chlorophyll'],
                ['grass', 'poison']
            ));
        });
    });
});