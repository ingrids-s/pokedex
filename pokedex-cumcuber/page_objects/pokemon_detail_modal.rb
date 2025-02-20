class PokemonDetailModal < SitePrism::Page
  element :pokemon_name, '.poke_name'
  element :pokemon_types, '.poke_types'
  element :pokemon_abilities, '.poke_abilities'
  element :pokemon_height, '.poke_height'
  element :pokemon_weight, '.poke_weight'
  element :pokemon_image, '.poke_image'

  # Método para verificar se o modal está visível
  def modal_visible?
    has_selector?('.modal') # Altere para o seletor que identifica seu modal
  end

  # Método para fechar o modal
  def close_modal
    find('.close').click # Supondo que você tenha um botão de fechar
  end
end