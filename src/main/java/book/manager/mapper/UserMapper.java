package book.manager.mapper;

import book.manager.entity.AuthUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from users where name = #{username}")
    AuthUser getUserByName(String username);

    @Options(useGeneratedKeys = true, keyColumn = "id",keyProperty = "id")
    @Insert("insert into users(name,role,password) value(#{username},#{role},#{password})")
    int registerUser(AuthUser user);

    @Insert("insert into student(uid,name,grade,sex) value(#{uid},#{name},#{grade},#{sex})")
    int addStudentInfo(@Param("uid") int uid, @Param("name") String name, @Param("grade") String grade, @Param("sex") String sex);

    @Select("select sid from student where uid = #{uid}")
    Integer getSidByUid(int uid);
    @Select("SELECT COUNT(*) FROM student")
    int studentNumber();

}
