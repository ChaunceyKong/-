import com.kong.dao.UserMapper;
import com.kong.pojo.User;
import com.kong.utils.MybatisUtils;
import lombok.ToString;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {

    @Test
    public void queryUserById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);

        User user = mapper.queryUserById(1);
        System.out.println(user);
        sqlSession.close();

        // mapper.updateUser(new User(2,"aaa","12345"));
        //sqlSession.clearCache(); // 手动清理缓存
        //System.out.println("===========================");

        User user2 = mapper2.queryUserById(1);
        System.out.println(user2);

        System.out.println(user == user2);

        sqlSession2.close();
    }
}
