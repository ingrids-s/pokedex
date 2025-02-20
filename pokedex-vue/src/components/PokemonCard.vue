<template>
    <div @click="$emit('openPokeDetails')" class="card">
        <div class="card_image">
            <div class="card_image_wrapper">
                <img :src="pokeImg" width="120" />
            </div>
        </div>
        <div class="card_content">
            <h2 class="card_title">{{ pokeName }}</h2>
        </div>
    </div>
</template>

<script>
export default {
    name: 'PokemonCard',
    props: {
        pokeImg: String,
        pokeName: String
    }
}
</script>

<style scoped>
.card {
  cursor: pointer; /* Indica que é clicável */
}
</style>