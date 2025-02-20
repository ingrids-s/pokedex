import Pokemon from '../../../src/domain/models/pokemonModel';

describe('When creating a Pokemon model instance', () => {
    it('Then it should have the provided properties', () => {
        const name = 'Pikachu';
        const image = 'pikachu_sprite_url';
        const baseExperience = 112;
        const weight = 6.0;
        const height = 4.0;
        const abilities = ['static'];
        const types = ['electric'];

        const pikachu = new Pokemon(name, image, baseExperience, weight, height, abilities, types);

        expect(pikachu.name).toBe(name);
        expect(pikachu.image).toBe(image);
        expect(pikachu.baseExperience).toBe(baseExperience);
        expect(pikachu.weight).toBe(weight);
        expect(pikachu.height).toBe(height);
        expect(pikachu.abilities).toEqual(abilities);
        expect(pikachu.types).toEqual(types);
    });
});