<template>
    <div class="main">
        
        <div class="card_list">
            <div class="loading" v-if="loading">Carregando...</div>
            <div v-else style="display: flex; justify-content: center;">
                <img src="../assets/poke_logo.svg.png" width="400"/>
            </div>
            
            <ul class="cards">
                <li
                    v-for="pokemon in pokemons"
                    :key="pokemon.name"
                    class="cards_item"
                >
                    <PokemonCard
                        @openPokeDetails="openModal(pokemon.name)"
                        :poke-name="pokemon.name"
                        :poke-img="pokemon.image"
                    >
                    </PokemonCard>
                </li>
            </ul>
            <PokemonDetails
                v-if="showModal"
                :pokemonName="pokemonName"
                @close="showModal = false"
            >
            </PokemonDetails>
        </div>
    </div>
</template>

<script>
import { getPokemonList } from "../../src/services/pokeService.js"
import PokemonCard from "../../src/components/PokemonCard.vue"
import PokemonDetails from "../../src/components/PokemonDetails.vue"

export default {
    name: 'PokemonList',
    components: {
        PokemonCard,
        PokemonDetails
    },
    data() {
        return {
            pokemons: [],
            loading: true,
            showModal: false,
            pokemonName: ''
        }
    },
    methods: {
        openModal(name) {
            this.pokemonName = name;
            this.showModal = true;
        },
    },
    async created() {
        this.pokemons = await getPokemonList()
        this.loading = false
    }
}
</script>