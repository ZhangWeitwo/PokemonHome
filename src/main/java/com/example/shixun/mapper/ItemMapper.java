package com.example.shixun.mapper;

import com.example.shixun.entity.ItemInfo;
import com.example.shixun.entity.TradeItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ItemMapper {
    @Select("SELECT * FROM item ORDER BY propid")
    List<ItemInfo> findAllItemInfo();
    // 根据ID查询道具
    @Select("SELECT * FROM item WHERE propid = #{pid}")
    List<ItemInfo> findById(@Param("pid") int pid);

    // 根据名称查询道具
    @Select("SELECT * FROM item WHERE name LIKE CONCAT('%', #{pname}, '%')")
    List<ItemInfo> findByName(@Param("pname") String pname);

    @Select("SELECT points FROM trainer WHERE Tid = #{tid}")
    int getUserPoints(@Param("tid") int Tid);


    @Select("SELECT price FROM commodity WHERE propid = #{pid}")
    int findPrice(@Param("pid") int pid);

    @Select("SELECT item.propid, item.name, item.description, own.num " +
            "FROM item, own " +
            "WHERE own.ownerId = #{tid} " +
            "AND item.propid = own.propid " +
            "ORDER BY item.propid")
    List<ItemInfo> getItem(@Param("tid") int Tid);

    @Select("SELECT propid, propName, ownerName, price, special, num FROM trade")
    List<TradeItem> findAllTradeItem();

    @Insert("INSERT INTO trade (propid, propname, ownerId, ownerName, price, special, num) " +
            "VALUES (#{propid}, #{propname}, #{ownerId}, #{ownerName}, #{price}, #{special}, #{num})")
    int insertTradeItem(
            @Param("propid") int propid,
            @Param("propname") String propname,
            @Param("ownerId") int ownerId,
            @Param("ownerName") String ownerName,
            @Param("price") int price,
            @Param("special") String special,
            @Param("num") int num
    );

    @Select("SELECT num from own where propid = #{propid} AND ownerId = #{ownerId}")
    int Getnum(@Param("propid") int propid,@Param("ownerId") int ownerId);

    @Update("UPDATE own SET num = #{num} WHERE propid = #{propid} AND ownerId = #{ownerId}")
    int decrease(@Param("propid") int propid,@Param("ownerId") int ownerId,@Param("num") int num);

//    @Select("SELECT propid, proName, ownerId, price, special, num " +
//            "FROM trad " +
//            "WHERE ownerId = #{tid} " +
//            "ORDER BY propid")
//    List<TradeItem> getTradeItem(@Param("tid") int Tid);
}