package notebook.controller.impl;

import notebook.controller.Controller;
import notebook.model.User;
import notebook.model.repository.GBRepository;
import java.util.List;
import java.util.Objects;

public class UserController implements Controller{
    private final GBRepository repository;



    public UserController(GBRepository repository) {
        this.repository = repository;
    }
    @Override
    public void saveUser(User user) {
        repository.create(user);
    }
    @Override
    public User readUser(Long userId) throws Exception {
        List<User> users = repository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                return user;
            }
        }

        throw new RuntimeException("User not found");
    }
    @Override
    public User findUserById(long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("User not found."));
    }
    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }
// может этот метод убрать
    public boolean userUpdate(long id, User update) {
        try {
            repository.update(id, update);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(String userId, User update) {
        update.setId(Long.parseLong(userId));
        repository.update(Long.parseLong(userId), update);
    }
    @Override
    public void deleteUser(String userId) {
        Long id=Long.parseLong(userId);
        repository.delete(id);
      
    }
}