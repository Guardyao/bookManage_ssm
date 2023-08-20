package book.manager.mapper;

import book.manager.entity.AuthUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from users where name = #{username}")
    AuthUser getUserByName(String username);

//    @Options(useGeneratedKeys = true, keyColumn = "id",keyProperty = "id")
//    @Insert("insert into users(name,role,password) value(#{username},#{role},#{password})")
//    int registerUser(AuthUser user);

    @Options(useGeneratedKeys = true, keyColumn = "id",keyProperty = "id")
    @Insert("insert into users(name,role,password) value(#{username},#{role},#{password})")
    int registerUser(AuthUser user);

    @Insert("insert into verify(email,code) value(#{email},#{code}")
    void verify(@Param("email") String email, @Param("code") String code);

    @Select("select code from verify where email = #{email}")
    String getCode(String email);

    @Select("select sid from student where email = #{email}")
    String getEmail(String email);

    @Delete("delete from verify where email = #{email}")
    void deleteCode(String email);

    @Insert("insert into student(uid,name,grade,sex，email) value(#{uid},#{name},#{grade},#{sex},#{email})")
    int addStudentInfo(@Param("uid") int uid, @Param("name") String name, @Param("grade") String grade, @Param("sex") String sex, @Param("email") String email);

    @Select("select sid from student where uid = #{uid}")
    Integer getSidByUid(int uid);
    @Select("SELECT COUNT(*) FROM student")
    int studentNumber();

}
