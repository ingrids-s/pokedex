import { mount } from '@vue/test-utils'
import PokemonCard from '@/components/PokemonCard.vue'

describe('When using the PokemonCard component', () => {
    let wrapper;

    beforeEach(() => {
        wrapper = mount(PokemonCard, {
            propsData: {
                pokeImg: 'http://example.com/image.png',
                pokeName: 'Pikachu'
            }
        });
    });

    describe('And when rendering the component', () => {
        it('Then it should render the correct image', () => {
            const img = wrapper.find('img');
            expect(img.attributes('src')).toBe('http://example.com/image.png');
        });

        it('Then it should render the correct poke name', () => {
            const title = wrapper.find('.card_title');
            expect(title.text()).toBe('Pikachu');
        });
    });

    describe('And when the card is clicked', () => {
        it('Then it should emit openPokeDetails', async () => {
            await wrapper.trigger('click');
            expect(wrapper.emitted().openPokeDetails).toBeTruthy();
        });
    });
});