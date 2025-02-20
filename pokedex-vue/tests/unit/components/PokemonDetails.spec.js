import { shallowMount } from '@vue/test-utils'
import PokemonDetail from '@/components/PokemonDetails.vue'
import { getPokemonDetails } from '@/services/pokeService.js'

jest.mock('@/services/pokeService.js')

describe('When using the PokemonDetail component', () => {
    let wrapper

    beforeEach(async () => {
        const mockPokemon = {
            name: 'Pikachu',
            image: 'pikachu.png',
            baseExperience: 112,
            weight: 6,
            height: 0.4,
            types: ['Electric'],
            abilities: ['Static']
        }
        
        getPokemonDetails.mockResolvedValue(mockPokemon)

        wrapper = shallowMount(PokemonDetail, {
            propsData: { pokemonName: 'pikachu' }
        })
        
        await wrapper.vm.$nextTick()
    })

    afterEach(() => {
        wrapper.destroy()
    })

    describe('And when a pokemon is fetched', () => {
        it('Then it renders correctly', async () => {
            await wrapper.vm.$nextTick()
    
            expect(wrapper.find('.poke_name').text()).toBe('Pikachu')
            expect(wrapper.find('img').attributes('src')).toBe('pikachu.png')
            expect(wrapper.findAll('.card_text').at(1).text()).toBe('112 XP')
            expect(wrapper.findAll('.card_text').at(3).text()).toBe('6kg')
            expect(wrapper.findAll('.card_text').at(5).text()).toBe('0.4m')
    
            expect(wrapper.findAll('.tags .tag').length).toBe(2)
            expect(wrapper.findAll('.tags .tag').at(0).text()).toBe('Electric')
            expect(wrapper.findAll('.tags .tag').at(1).text()).toBe('Static')
        })
    })

    describe('And when the close button is clicked', () => {
        it('Then it emits a close event', async () => {
            const closeButton = wrapper.find('.close')
            await closeButton.trigger('click')
            expect(wrapper.emitted().close).toBeTruthy()
        })
    })

    describe('And when a pokemon is not fetched', () => {
        it('Then it displays nothing', async () => {
            getPokemonDetails.mockResolvedValueOnce(null)

            wrapper = shallowMount(PokemonDetail, {
                propsData: { pokemonName: 'invalid_name' }
            })

            await wrapper.vm.$nextTick()

            expect(wrapper.find('.modal-content').exists()).toBe(false)
        })
    })
})