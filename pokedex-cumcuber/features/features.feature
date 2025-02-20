Feature: Visualização da Pokédex

  Scenario: Listar todos os Pokémon
    Given que eu estou na tela da Pokédex
    When a lista de Pokémon é carregada
    Then eu devo ver uma lista de Pokémon
    And cada Pokémon deve exibir o nome e uma imagem
    And a lista deve ser paginável ou rolável

  Scenario: Visualizar detalhes de um Pokémon
    Given que eu estou na tela da Pokédex
    And eu vejo a lista de Pokémon
    When eu clico em um Pokémon específico
    Then eu devo ser redirecionado para a página de detalhes desse Pokémon
    And a página deve mostrar o nome do Pokémon
    And a página deve mostrar os tipos do Pokémon
    And a página deve mostrar as habilidades especiais
    And a página deve mostrar as estatísticas do Pokémon
    And a página deve conter uma breve descrição do Pokémon
    And a página deve mostrar uma imagem maior do Pokémon