package oit.is.z1082.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
  @Select("select id,name from users where id = #{id}")
  User selectById(int id);

  @Select("select name from users where id = #{id}")
  String selectNameById(int id);

  @Select("select id,name from users where not id = #{id}")
  User selectByNotId(int id);

  @Select("SELECT name FROM USERS;")
  ArrayList<User> selectAllName();

  @Select("SELECT * FROM USERS;")
  ArrayList<User> selectAllUser();

}
