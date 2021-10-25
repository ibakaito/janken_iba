package oit.is.z1082.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchMapper {
  @Select("SELECT user2 FROM MATCHES;")
  ArrayList<Match> selectById(int id);

  @Select("SELECT * FROM MATCHES;")
  ArrayList<Match> selectAllMatch();

  @Select("SELECT * FROM MATCHES WHERE user2Hand=#{user2Hand};")
  ArrayList<Match> selectuser2Hand(String user2Hand);

  @Insert("INSERT INTO matches (user1,user2,user1Hand,user2Hand) VALUES (#{user1},#{user2},#{user1Hand},#{user2Hand})")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatch(Match match);

}
