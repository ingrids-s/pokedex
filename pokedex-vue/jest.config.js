module.exports = {
  moduleNameMapper: {
    "^src/__tests__/(.*)$": "<rootDir>/src/__tests__/$1",
  },
  transformIgnorePatterns: ["/node_modules/(?!axios)"],
  preset: '@vue/cli-plugin-unit-jest'
}
