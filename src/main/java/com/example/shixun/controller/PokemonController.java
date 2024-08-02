package com.example.shixun.controller;

import com.example.shixun.mapper.PokemonMapper;
import com.example.shixun.model.PokemonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
public class PokemonController {
        @Autowired
        private PokemonMapper pokemonMapper;
        @GetMapping("/allPokemon")
        public Map<String, List<PokemonInfo>> getAllPokemon() {
            List<PokemonInfo> pokemonInfos = pokemonMapper.findAllPokemons();
            System.out.println(pokemonInfos);
            Map<String, List<PokemonInfo>> response = new HashMap<>();
            response.put("pokemons", pokemonInfos);
            return response;
        }
}
