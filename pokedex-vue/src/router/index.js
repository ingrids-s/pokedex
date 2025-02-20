import Vue from "vue"
import VueRouter from "vue-router"
import PokemonList from "@/components/PokemonList.vue"
import PokemonDetails from "@/components/PokemonDetails.vue"

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        component: PokemonList
    },
    {
        path: '/details/:name',
        component: PokemonDetails
    },
]

export default new VueRouter({ routes })