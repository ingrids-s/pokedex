# features/support/env.rb
require 'capybara/cucumber'
require 'site_prism'
require 'rspec/expectations'

# Carregar as classes de página
Dir[File.join(File.dirname(__FILE__), '..', '..', 'page_objects', '*.rb')].each { |file| require file }

Capybara.default_driver = :selenium_chrome
Capybara.app_host = 'http://localhost:8081' # URL base da sua aplicação