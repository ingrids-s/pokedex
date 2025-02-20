import PokemonService from '../../application/pokemonService.js';

class PokemonController {
    async getAllPokemons(req, res) {
        try {
            const pokemons = await PokemonService.getAllPokes();
            res.send(pokemons);
        } catch (error) {
            console.error("Erro ao buscar Pokemon: ", error);
            res.status(500).json({ error: "Erro ao obter Pokémon" });
        }
    }

    async getPokemonByName(req, res) {
        try {
            const { name } = req.params;
            const pokemon = await PokemonService.getPokeByName(name);
            res.send(pokemon);
        } catch (error) {
            console.error("Erro ao buscar Pokemon: ", error);
            res.status(500).json({ error: "Erro ao obter Pokémon" });
        }
    }
}

export default new PokemonController();