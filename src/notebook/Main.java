package notebook;

import notebook.model.User;
// import notebook.model.dao.impl.FileOperation;
import notebook.model.repository.GBRepository;
import notebook.model.repository.impl.UserRepository;
import notebook.view.UserView;


import static notebook.util.DBConnector.DB_PATH;
import static notebook.util.DBConnector.createDB;
import static notebook.view.DelimeterView.getDelimeter;

import notebook.controller.Controller;
import notebook.controller.impl.UserController;
import notebook.controller.impl.UserControllerWithLog;
import notebook.util.logger.impl.loggerController;


public class Main {
    public static void main(String[] args) {
        createDB();
       
        GBRepository repository = new UserRepository(getDelimeter(), DB_PATH);
        Controller controller = new UserControllerWithLog(new UserController(repository), new loggerController());
        UserView view = new UserView(controller);
        view.run();
    }
}
