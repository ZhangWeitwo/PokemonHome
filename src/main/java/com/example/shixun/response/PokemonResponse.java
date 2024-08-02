package com.example.shixun.response;

import com.example.shixun.model.PokemonInfo;

import java.util.List;

public class PokemonResponse {
    private List<PokemonInfo> pokemons;

    public List<PokemonInfo> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<PokemonInfo> pokemons) {
        this.pokemons = pokemons;
    }

}
