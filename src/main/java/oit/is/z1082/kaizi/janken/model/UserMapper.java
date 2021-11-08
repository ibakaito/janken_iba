package oit.is.z1082.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
  @Select("select id,name from users where id = #{id}")
  User selectById(int id);

  @Select("select * from users where name = #{name}")
  User selectByName(String name);

  @Select("select name from users where id = #{id}")
  String selectNameById(int id);

  @Select("select id,name from users where not id = #{id}")
  User selectByNotId(int id);

  @Select("SELECT name FROM USERS;")
  ArrayList<User> selectAllName();

  @Select("SELECT * FROM USERS;")
  ArrayList<User> selectAllUser();

  @Insert("INSERT INTO users (name) VALUES (#{name});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertUser(User name);
  // INSERT INTO users (name) VALUES (name=#{name});

}
