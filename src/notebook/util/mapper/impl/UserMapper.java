package notebook.util.mapper.impl;
import notebook.util.mapper.Mapper;
import notebook.model.User;

public class UserMapper implements Mapper<User, String> {
    public String delimeter;
    
    
    public UserMapper(String delimeter) {
        this.delimeter = delimeter;
    }

    
    @Override
    public String toInput(User user) {
        StringBuilder newUserString=new StringBuilder();
        newUserString.append(user.getId());
        newUserString.append(delimeter);
        newUserString.append(user.getFirstName());
        newUserString.append(delimeter);
        newUserString.append(user.getLastName());
        newUserString.append(delimeter);
        newUserString.append(user.getPhone());
       
        return newUserString.toString();
    }

    @Override
    public User toOutput(String s) {
        String[] lines = s.split(delimeter);
        long id;
        if (isDigit(lines[0])) {
            id = Long.parseLong(lines[0]);
            return new User(id, lines[1], lines[2], lines[3]);
        }
        throw new NumberFormatException("Id must be a large number");
    }

    private boolean isDigit(String s) throws NumberFormatException {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getDelimeter() {
        return delimeter;
    }

    
}
    

