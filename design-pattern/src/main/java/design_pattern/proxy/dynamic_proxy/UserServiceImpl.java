package design_pattern.proxy.dynamic_proxy;

/**
 * @author fitbbc
 * @date 2019/05/23
 */
public class UserServiceImpl implements UserService {
    @Override
    public void saveUser() {
        System.out.println("save User");
    }
}
