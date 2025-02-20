import axios from "axios"

const API_URL = "http://localhost:3000"

export async function getPokemonList() {
    const response = await axios.get(`${API_URL}/pokemon`)
    return response.data
}

export async function getPokemonDetails(name) {
    const response = await axios.get(`${API_URL}/pokemon/${name}`)
    return response.data
}