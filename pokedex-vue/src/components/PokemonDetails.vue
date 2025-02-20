<template>
    <div class="modal">
        <div v-if="pokemon" class="modal-content">
          <img class="poke_image" :src="pokemon.image" width="120" />
        <span @click="$emit('close')" class="close">&times;</span>
        <h2 class="poke_name">{{ pokemon.name }}</h2>
        <div style="display: flex; justify-content: space-between;">
            <p class="card_text">Base Experience/Experiência</p>
            <p class="card_text">{{ pokemon.baseExperience }} XP</p>
        </div>
        <div style="display: flex; justify-content: space-between;">
            <p class="card_text">Weight/Peso</p>
            <p class="card_text poke_weight">{{ pokemon.weight }}kg</p>
        </div>
        <div style="display: flex; justify-content: space-between;">
            <p class="card_text">Height/Altura</p>
            <p class="card_text poke_height">{{ pokemon.height }}m</p>
        </div>
        <div>
            <p class="card_text">Pokemon Types/Tipos</p>
            <ul class="tags">
                <li
                    v-for="(type, index) in pokemon.types"
                    :key="index"
                    class="tag poke_types"
                    style="--color: #efd81d;"
                >
                    <a href="#">{{ type }}</a>
                </li>
            </ul>
        </div>
        <div>
            <p class="card_text">Abilities/Habilidades</p>
            <ul class="tags">
                <li
                    v-for="(ability, index) in pokemon.abilities"
                    :key="index"
                    class="tag poke_abilities"
                    style="--color: #efd81d;"
                >
                    <a href="#">{{ ability }}</a>
                </li>
            </ul>
        </div>
      </div>
    </div>
</template>

<script>
import { getPokemonDetails } from "../../src/services/pokeService.js"

export default {
    name: 'PokemonDetail',
    props: {
        pokemonName: String
    },
    data() {
        return {
            pokemon: null
        }
    },
    async created() {
        this.pokemon = await getPokemonDetails(this.pokemonName)
    }
}
</script>

<style scoped>
.modal {
  display: flex;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.5);
  overflow: hidden;
}

.modal-content {
  background-color: #fefefe;
  margin: 11% auto;
  padding: 20px;
  border: 1px solid #888;
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover, .close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

@media (min-width: 25rem) {
    .cards {
        -ms-flex-wrap: wrap;
        flex-wrap: wrap;
        -webkit-box-orient: horizontal;
        -webkit-box-direction: normal;
        -ms-flex-direction: row;
        flex-direction: row;
    }

    .modal-content {
        width: 80%;
        height: 60%;
    }
}

@media (min-width: 40rem) {
    .modal-content {
        width: 50%;
    }
}

@media (min-width: 56rem) {
    .modal-content {
        width: 35%;
    }
}
</style>