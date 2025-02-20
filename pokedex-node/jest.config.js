// jest.config.js or jest.config.mjs

export const moduleNameMapper = {
  "^src/__tests__/(.*)$": "<rootDir>/src/__tests__/$1",
};

const config = {
  // Your Jest configuration options
  testEnvironment: 'node',
  transform: {
    '^.+\\.jsx?$': 'babel-jest', // Example of a transform option
  },
  moduleNameMapper, // Include the moduleNameMapper here
  // Add other Jest configurations as needed
  
    transform: {
      "^.+\\.jsx?$": "babel-jest",
      "^.+\\.m?js$": "babel-jest"
    },
    extensionsToTreatAsEsm: [".ts", ".tsx", ".jsx"],
    globals: {
      "ts-jest": {
        useESM: true
      }
    }
};

export default config;