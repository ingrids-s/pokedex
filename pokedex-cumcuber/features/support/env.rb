# features/support/env.rb
require 'capybara/cucumber'
require 'site_prism'
require 'rspec/expectations'

Capybara.default_driver = :selenium_chrome
Capybara.app_host = 'http://localhost:8081'