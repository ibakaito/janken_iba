package oit.is.z1082.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchMapper {
  @Select("SELECT user2 FROM MATCHES;")
  Match selectById(int id);

  @Select("SELECT user1 FROM MATCHES;")
  Match selectById2(int id);

  @Select("SELECT id FROM MATCHES where isActive is true;")
  int selectId();

  @Select("SELECT * FROM MATCHES where isActive is true;")
  Match selectByAllTrue();

  @Select("SELECT * FROM MATCHES;")
  ArrayList<Match> selectAllMatch();

  @Select("SELECT * FROM MATCHES WHERE user2Hand=#{user2Hand};")
  ArrayList<Match> selectuser2Hand(String user2Hand);

  @Select("SELECT isActive FROM MATCHES WHERE isActive=#{isActive};")
  boolean selectIsActive(boolean isActive);

  @Insert("INSERT INTO matches (user1,user2,user1Hand,user2Hand,isActive) VALUES (#{user1},#{user2},#{user1Hand},#{user2Hand},#{isActive})")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatch(Match match);

  @Update("UPDATE MATCHES SET isActive=false WHERE id = #{id}")
  void updateById(int id);

}
