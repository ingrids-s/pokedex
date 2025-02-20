import { shallowMount } from '@vue/test-utils'
import PokemonList from '@/components/PokemonList.vue'
import PokemonCard from '@/components/PokemonCard.vue'
import PokemonDetails from '@/components/PokemonDetails.vue'
import { getPokemonList } from '@/services/pokeService.js'

jest.mock('@/services/pokeService.js')

describe('When using the PokemonList component', () => {
    let wrapper

    beforeEach(async () => {
        getPokemonList.mockResolvedValue([
            { name: 'Pikachu', image: 'pikachu.png' },
            { name: 'Charmander', image: 'charmander.png' }
        ])
        wrapper = shallowMount(PokemonList)
        await wrapper.vm.$nextTick()
    })

    describe('And when the component is rendered', () => {
        it('Then it should render a logo and pokemon cards', async () => {
            expect(wrapper.find('img').attributes('src')).toBe('../assets/poke_logo.svg.png')
            expect(wrapper.findAll('.cards_item')).toHaveLength(2)
        })
    })

    describe('And when a PokemonCard is clicked', () => {
        it('Then it should open the modal', async () => {
            const pokemonCard = wrapper.findComponent(PokemonCard)
            await pokemonCard.vm.$emit('openPokeDetails', 'Pikachu')
            expect(wrapper.vm.showModal).toBe(true)
            expect(wrapper.vm.pokemonName).toBe('Pikachu')
        })
    })

    describe('And when the PokemonDetails emits close', () => {
        it('Then it should close the modal', async () => {
            wrapper.setData({ showModal: true, pokemonName: 'Pikachu' })
            await wrapper.vm.$nextTick()
            const pokemonDetails = wrapper.findComponent(PokemonDetails)
            await pokemonDetails.vm.$emit('close')
            expect(wrapper.vm.showModal).toBe(false)
        })
    })

    describe('And when the component is created', () => {
        it('Then it should fetch pokemons', async () => {
            expect(getPokemonList).toHaveBeenCalled()
            await wrapper.vm.$nextTick()
            expect(wrapper.vm.pokemons).toHaveLength(2)
            expect(wrapper.vm.loading).toBe(false)
        })
    })
})