import axios from 'axios';
const QUARKUS_API = process.env.QUARKUS_API || 'http://localhost:8080';

class PokemonRepository {
    async fetchPokemonList() {
        const response = await axios.get(`${QUARKUS_API}/pokemon`);
        return response.data;
    }

    async fetchPokemonDetails(name) {
        const response = await axios.get(`${QUARKUS_API}/pokemon/${name}`);
        return response.data;
    }
}

export default new PokemonRepository();