Dado("que eu estou na tela da Pokédex") do
  visit '/pokedex'
end

Quando("a lista de Pokémon é carregada") do
  expect(page).to have_selector('.loading', visible: true)
  expect(page).to have_selector('.cards_item', minimum: 1, wait: 10)
  expect(page).to have_no_selector('.loading', visible: true)
end

Então("eu devo ver uma lista de Pokémon") do
  expect(page).to have_selector('.cards_item')
end

Então("cada Pokémon deve exibir o nome e uma imagem") do
  all('.cards_item').each do |item|
    expect(item).to have_selector('.card_title')
    expect(item).to have_selector('img')
  end
end

Então("a lista deve ser paginável ou rolável") do
  # expect(page).to have_selector('.pagination')
  # Ou verifique se a rolagem funciona
end

Dado("eu vejo a lista de Pokémon") do
  expect(page).to have_selector('.loading', visible: true)
  expect(page).to have_selector('.cards_item', minimum: 1, wait: 10)
  expect(page).to have_no_selector('.loading', visible: true)
end

Quando("eu clico em um Pokémon específico") do
  first('.cards_item').click
end

Então("eu devo ser redirecionado para a página de detalhes desse Pokémon") do
  expect(page).to have_selector('.modal-content', visible: true)
end

Então("a página deve mostrar o nome do Pokémon") do
  expect(page).to have_selector('.poke_name')
end

Então("a página deve mostrar os tipos do Pokémon") do
  expect(page).to have_selector('.poke_types')
end

Então("a página deve mostrar as habilidades especiais") do
  expect(page).to have_selector('.poke_abilities')
end

Então("a página deve mostrar as estatísticas do Pokémon") do
  expect(page).to have_selector('.poke_statistics')
end

Então("a página deve conter uma breve descrição do Pokémon") do
  expect(page).to have_selector('.poke_description')
end

Então("a página deve mostrar uma imagem maior do Pokémon") do
  expect(page).to have_selector('.poke_image', visible: true)
end