import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import { getPokemonList, getPokemonDetails } from '@/services/pokeService.js';

const mock = new MockAdapter(axios);
const API_URL = "http://localhost:3000";

describe('When testing the Pokemon API Service', () => {
    afterEach(() => {
        mock.reset();
    });

    describe('And when fetching the list of pokemons', () => {
        it('Then it should return a list of pokemons', async () => {
            const mockResponse = [
                { name: 'bulbasaur', image: 'http://example.com/bulbasaur.png' },
                { name: 'ivysaur', image: 'http://example.com/ivysaur.png' },
            ];

            mock.onGet(`${API_URL}/pokemon`).reply(200, mockResponse);

            const data = await getPokemonList();
            expect(data).toEqual(mockResponse);
        });

        it('Then it should handle errors gracefully', async () => {
            mock.onGet(`${API_URL}/pokemon`).reply(500);

            await expect(getPokemonList()).rejects.toThrow();
        });
    });

    describe('And when fetching details of a pokemon', () => {
        it('Then it should return details of the pokemon', async () => {
            const mockResponse = {
                name: 'bulbasaur',
                id: 1,
                abilities: [{ ability: { name: 'overgrow' } }],
                types: [{ type: { name: 'grass' } }],
            };

            mock.onGet(`${API_URL}/pokemon/bulbasaur`).reply(200, mockResponse);

            const data = await getPokemonDetails('bulbasaur');
            expect(data).toEqual(mockResponse);
        });

        it('Then it should handle errors gracefully', async () => {
            mock.onGet(`${API_URL}/pokemon/unknown`).reply(404);

            await expect(getPokemonDetails('unknown')).rejects.toThrow();
        });
    });
});