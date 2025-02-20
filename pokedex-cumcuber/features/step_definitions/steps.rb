Dado("que eu estou na tela da Pokédex") do
  @pokedex_page = PokedexPage.new
  #@pokedex_page.load # Carrega a URL definida na classe PokedexPage
end

Quando("a lista de Pokémon é carregada") do
    puts page.html
  expect(@pokedex_page).to have_pokemon_list(wait: 10) # Espera até que a lista de Pokémon esteja carregada
end

Então("eu devo ver uma lista de Pokémon") do
  expect(@pokedex_page.pokemon_list.size).to be > 0 # Verifica se há Pokémon na lista
end

Então("cada Pokémon deve exibir o nome e uma imagem") do
  all('.cards_item').each do |item|
    expect(item).to have_css('h2.card_title')
    expect(item).to have_css('.card_image')
  end
end

Então("a lista deve ser paginável ou rolável") do
  # Aqui você pode verificar se há elementos de paginação ou se a lista pode ser rolada
  # expect(page).to have_css('.pagination') # Ajuste conforme necessário
end

Dado("eu vejo a lista de Pokémon") do
  expect(@pokedex_page).to have_pokemon_list(wait: 10) # Verifica se a lista está visível
end

Quando("eu clico em um Pokémon específico") do
  @pokedex_page.cards_item.first.click # Clica no primeiro Pokémon da lista
  sleep 1 # Adicione uma pausa para garantir que o modal tenha tempo de abrir
  puts page.html # Imprime o HTML da página atual para verificação
  @pokemon_detail_modal = PokemonDetailModal.show # Inicializa a página do modal de detalhes
end

Então("eu devo ser redirecionado para a página de detalhes desse Pokémon") do
  expect(@pokemon_detail_modal).to have_css('.modal', wait: 10)
end

Então("a página deve mostrar o nome do Pokémon") do
  expect(@pokemon_detail_modal.pokemon_name.text).not_to be_empty # Verifica se o nome do Pokémon está presente
end

Então("a página deve mostrar os tipos do Pokémon") do
  expect(@pokemon_detail_modal.pokemon_types).to have_css('li') # Verifica se os tipos estão presentes
end

Então("a página deve mostrar as habilidades especiais") do
  expect(@pokemon_detail_modal.pokemon_abilities).to have_css('li') # Verifica se as habilidades estão presentes
end

Então("a página deve mostrar as estatísticas do Pokémon") do
  expect(@pokemon_detail_modal.pokemon_weight.text).not_to be_empty # Verifica se o peso está presente
  expect(@pokemon_detail_modal.pokemon_height.text).not_to be_empty # Verifica se a altura está presente
end

Então("a página deve conter uma breve descrição do Pokémon") do
  expect(@pokemon_detail_modal).to have_css('.poke_description') # Verifica se a descrição está presente
end

Então("a página deve mostrar uma imagem maior do Pokémon") do
  expect(@pokemon_detail_modal.pokemon_image).to be_visible # Verifica se a imagem está visível
end

