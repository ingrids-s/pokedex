import request from 'supertest';
import express from 'express';
import pokemonRouter from '../../../src/gateway/http/pokemonRoutes.js';
import PokemonController from '../../../src/gateway/http/pokemonController.js';

jest.mock('../../../src/gateway/http/pokemonController.js');

const app = express();
app.use(express.json());
app.use('/pokemon', pokemonRouter);

describe('When testing Pokemon Routes', () => {
    describe('And when sending a GET request to /pokemon', () => {
        it('Then it should return a list of pokemons', async () => {
            const pokemons = [{ name: 'Pikachu' }, { name: 'Bulbasaur' }];
            PokemonController.getAllPokemons.mockImplementation((req, res) => res.send(pokemons));

            const response = await request(app).get('/pokemon');
            expect(response.status).toBe(200);
            expect(response.body).toEqual(pokemons);
            expect(PokemonController.getAllPokemons).toHaveBeenCalled();
        });
        
        it('Then it should handle errors gracefully', async () => {
            PokemonController.getAllPokemons.mockImplementation((req, res) => res.status(500).json({ error: "Erro ao obter Pokémon" }));

            const response = await request(app).get('/pokemon');
            expect(response.status).toBe(500);
            expect(response.body).toEqual({ error: "Erro ao obter Pokémon" });
        });
    });

    describe('And when sending a GET request to /pokemon/:name', () => {
        it('Then it should return a pokemon by name', async () => {
            const pokemon = { name: 'Pikachu', image: 'pikachu_sprite_url' };
            PokemonController.getPokemonByName.mockImplementation((req, res) => res.send(pokemon));

            const response = await request(app).get('/pokemon/Pikachu');
            expect(response.status).toBe(200);
            expect(response.body).toEqual(pokemon);
        });

        it('Then it should handle errors gracefully', async () => {
            PokemonController.getPokemonByName.mockImplementation((req, res) => res.status(500).json({ error: "Erro ao obter Pokémon" }));

            const response = await request(app).get('/pokemon/Pikachu');
            expect(response.status).toBe(500);
            expect(response.body).toEqual({ error: "Erro ao obter Pokémon" });
        });
    });
});