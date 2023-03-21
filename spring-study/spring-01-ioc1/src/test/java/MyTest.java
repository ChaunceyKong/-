import com.kong.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {

        //获取ApplicationContext，拿到spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        //需要什么就直接get什么
        UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("userServiceImpl");
        userServiceImpl.getUser();
    }
}
