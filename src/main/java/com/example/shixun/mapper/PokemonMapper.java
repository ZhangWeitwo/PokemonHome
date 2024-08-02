package com.example.shixun.mapper;

import com.example.shixun.model.PokemonInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PokemonMapper {
    //查图鉴的所有宝可梦
    @Select("SELECT * FROM pokemon ORDER BY Pid")
    List<PokemonInfo> findAllPokemons();
}
