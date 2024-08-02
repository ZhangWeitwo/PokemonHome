package com.example.shixun.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.shixun.entity.TP;
import com.example.shixun.entity.Trainer;
import com.example.shixun.model.PokemonInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface TpMapper {
        // 通过ID查找训练师在背包中的宝可梦
    @Select("SELECT pokemon.Pname, pokemon.location, pokemon.Pid, pokemon.type1, pokemon.type2, pokemon.imageUrl, " +
            "tp.gender, tp.lev, tp.flag " +
            "FROM tp " +
            "JOIN pokemon ON tp.Pid = pokemon.Pid " +
            "WHERE tp.statement = 1 " +
            "AND tp.Tid = #{tid}")
    List<PokemonInfo> findPokemonInfoByTid1(@Param("tid") int tid);
    //查找仓库宝可梦
    @Select("SELECT pokemon.Pname, pokemon.location, pokemon.Pid, pokemon.type1, pokemon.type2, pokemon.imageUrl, " +
            "tp.gender, tp.lev, tp.flag " +
            "FROM tp " +
            "JOIN pokemon ON tp.Pid = pokemon.Pid " +
            "WHERE tp.statement = 0 " +
            "AND tp.Tid = #{tid}")
    List<PokemonInfo> findPokemonInfoByTid2(@Param("tid") int tid);
    //查找交换池宝可梦
    @Select("SELECT pokemon.Pname, pokemon.location, pokemon.Pid, pokemon.type1, pokemon.type2, pokemon.imageUrl, " +
            "tp.gender, tp.lev, tp.flag " +
            "FROM tp " +
            "JOIN pokemon ON tp.Pid = pokemon.Pid " +
            "WHERE tp.statement = 2 " +
            "AND tp.Tid = #{tid}")
    List<PokemonInfo> findPokemonInfoinpool(@Param("tid") int tid);
    //实现交换-----更改tp表中宝可梦的pid值
    @Update("UPDATE TP SET Tid = #{tid} WHERE flag = #{flag}")
    int exchangePokemon(@Param("tid") int tid, @Param("flag") int flag);
    //放到仓库


    //放入背包

}
