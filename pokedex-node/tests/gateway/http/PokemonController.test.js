import PokemonController from '../../../src/gateway/http/pokemonController.js';
import PokemonService from '../../../src/application/pokemonService.js';

jest.mock('../../../src/application/pokemonService.js');

describe('When using PokemonController', () => {
    let req, res;

    beforeEach(() => {
        req = {};
        res = {
            send: jest.fn(),
            status: jest.fn().mockReturnThis(),
            json: jest.fn(),
        };
    });

    describe('And when getting all pokemons', () => {
        it('Then it should send all pokemons', async () => {
            const pokemons = [{ name: 'Pikachu' }, { name: 'Bulbasaur' }];
            PokemonService.getAllPokes.mockResolvedValue(pokemons);
            await PokemonController.getAllPokemons(req, res);
            expect(res.send).toHaveBeenCalledWith(pokemons);
        });

        it('Then it should handle errors gracefully', async () => {
            PokemonService.getAllPokes.mockRejectedValue(new Error('Service error'));
            await PokemonController.getAllPokemons(req, res);
            expect(res.status).toHaveBeenCalledWith(500);
            expect(res.json).toHaveBeenCalledWith({ error: "Erro ao obter Pokémon" });
        });
    });

    describe('And when getting a pokemon by name', () => {
        it('Then it should send pokemon by name', async () => {
            req.params = { name: 'Pikachu' };
            const pokemon = { name: 'Pikachu' };
            PokemonService.getPokeByName.mockResolvedValue(pokemon);
            await PokemonController.getPokemonByName(req, res);
            expect(res.send).toHaveBeenCalledWith(pokemon);
        });

        it('Then it should handle errors gracefully', async () => {
            req.params = { name: 'Pikachu' };
            PokemonService.getPokeByName.mockRejectedValue(new Error('Service error'));
            await PokemonController.getPokemonByName(req, res);
            expect(res.status).toHaveBeenCalledWith(500);
            expect(res.json).toHaveBeenCalledWith({ error: "Erro ao obter Pokémon" });
        });
    });
});