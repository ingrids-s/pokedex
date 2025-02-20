package br.com.pokedex.domain;

import java.util.List;

public class PokemonDetail {
    private String name;
    private String imageUrl;
    private int baseExperience;
    private int weight;
    private int height;
    private List<String> abilities;
    private List<String> types;

    public PokemonDetail(
            String name,
            String imageUrl,
            int baseExperience,
            int weight,
            int height,
            List<String> abilities,
            List<String> types
    ) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.baseExperience = baseExperience;
        this.weight = weight;
        this.height = height;
        this.abilities = abilities;
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
