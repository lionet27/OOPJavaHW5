package notebook.controller;
import notebook.model.User;
import java.util.List;



public interface Controller {
    public void saveUser(User user);

    public User readUser(Long userId) throws Exception;
    public User findUserById(long id);

    public List<User> getAllUsers();
    public void updateUser(String userId, User update);

    public void deleteUser(String userId);
}
