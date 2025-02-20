import express, { json } from 'express';
import cors from 'cors';
import pokemonRoutes from './src/gateway/http/pokemonRoutes.js';

const app = express();
app.use(cors());
app.use(json());
app.use('/pokemon', pokemonRoutes);

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`BFF rodando em http://localhost:${PORT}`));