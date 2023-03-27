package notebook.view;

import notebook.model.User;
import notebook.util.Commands;
import java.util.List;
import java.util.Scanner;
import notebook.controller.Controller;

public class UserView {
    private final Controller userController;

    public UserView(Controller userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com;
                

        while (true) {
            String command = prompt("Print command: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    User u = createUser();
                    userController.saveUser(u);
                    break;
                    
                case READ:
                    String id = prompt("ID user: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case LIST:
                    List<User> users = userController.getAllUsers();
                    for(User user: users) {
                        System.out.println(user);
                    }
                    break;
                case UPDATE:
                    String userId = prompt("Enter user id: ");
                    userController.updateUser(userId, createUser());
                    break;
                case DELETE:
                    userId = prompt("Enter user id: ");
                    userController.deleteUser(userId);
                    break;
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.println(message);
                
        return in.nextLine();
    }

    private User createUser() {
        String firstName = prompt("Name: ");
        String lastName = prompt("Surname: ");
        String phone = prompt("Telefone number: ");
        return new User(firstName, lastName, phone);
    }
}
