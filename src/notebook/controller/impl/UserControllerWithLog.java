package notebook.controller.impl;

import java.util.List;

import notebook.controller.Controller;
import notebook.model.User;
import notebook.util.logger.impl.loggerController;

public class UserControllerWithLog implements Controller {
    private UserController userController;
    private loggerController loggerController;

    public UserControllerWithLog(UserController userController, loggerController loggerController) {
        this.userController = userController;
        this.loggerController =new loggerController();
    }

    @Override
    public void deleteUser(String userId) {
        userController.deleteUser(userId);
        loggerController.log("Delete user ID="+userId);
    }

    @Override
    public User findUserById(long id) {
        
        return userController.findUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        
        return userController.getAllUsers();
    }

    @Override
    public User readUser(Long userId) throws Exception {
        
        return userController.readUser(userId);
    }

    @Override
    public void saveUser(User user) {
        userController.saveUser(user);
        loggerController.log("Save user:"+user);
        
    }

    @Override
    public void updateUser(String userId, User update) {
        userController.updateUser(userId, update);
        loggerController.log("Update user with :"+update);
    }

    

}    

    
    
