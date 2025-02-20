import { Router } from 'express';
const router = Router();
import PokemonController from './pokemonController.js';

router.get('/', PokemonController.getAllPokemons.bind(PokemonController));
router.get('/:name', PokemonController.getPokemonByName.bind(PokemonController));

export default router;