package oit.is.z1082.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchInfoMapper {
  @Select("SELECT * FROM MATCHINFO;")
  ArrayList<MatchInfo> selectAllMatchInfo();

  @Select("SELECT * FROM MATCHINFO WHERE user2 = #{user2};")
  MatchInfo selectByUser2(int user2);

  @Select("SELECT true FROM MATCHINFO WHERE user2 = #{user2} and isActive = true;")
  boolean selectBooleanByUser2(int user2);

  @Select("SELECT * FROM MATCHINFO WHERE isActive IS TRUE;")
  ArrayList<MatchInfo> selectTrueMatchInfo();

  @Select("SELECT id FROM MATCHEINFO where isActive is true;")
  int selectId();

  @Insert("INSERT INTO MATCHINFO(user1,user2,user1Hand,isActive) VALUES (#{user1},#{user2},#{user1Hand},#{isActive})")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatchInfo(MatchInfo matchinfo);

  @Update("UPDATE MATCHINFO SET isActive=#{isActive} WHERE id = #{id}")
  void updateById(MatchInfo matchInfo);

  @Update("UPDATE MATCHEINFO SET isActive=false WHERE id = #{id}")
  void updateById(int id);
}
