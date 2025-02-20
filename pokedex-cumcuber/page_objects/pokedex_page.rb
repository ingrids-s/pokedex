# page_objects/pokedex_page.rb
class PokedexPage < SitePrism::Page
  set_url 'http://localhost:8081/pokedex'  # Ajuste para a URL da sua Pokédex

  elements :pokemon_list, '.main'  # Seletor para cada Pokémon na lista
  element :load, '.loading'  # Seletor para um indicador de carregamento, se existir
end