// @ts-check
/** @type {import('@stryker-mutator/api/core').PartialStrykerOptions} */
const config = {
  _comment:
    "This config was generated using 'stryker init'. Please see the guide for more information: https://stryker-mutator.io/docs/stryker-js/guides/vuejs",
  testRunner: "vitest",
  reporters: ["progress", "clear-text", "html"],

  // Include the test file patterns
  testFilePatterns: ['**/*.test.js'],
  
  // Specify your source files
  mutate: ['src/**/*.js'],
};
export default config;
