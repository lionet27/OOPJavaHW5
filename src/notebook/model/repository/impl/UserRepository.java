package notebook.model.repository.impl;

import notebook.model.User;
import notebook.model.dao.impl.FileOperation;
import notebook.model.repository.GBRepository;
import notebook.model.repository.Operation;
import notebook.util.mapper.impl.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.io.*;


public class UserRepository implements GBRepository,Operation<String>{
    private  final String delimeter;
    private  final UserMapper mapper;
    private final String fileName;

    

    public UserRepository(String delimeter, String fileName) {
        this.delimeter = delimeter;
        this.mapper = new UserMapper(delimeter);
        this.fileName = fileName;
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> readAll() {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(fileName);
            // ˜˜˜˜˜˜˜ ˜˜˜˜˜˜ FileReader ˜˜˜ ˜˜˜˜˜˜˜ File
            FileReader fr = new FileReader(file);
            //˜˜˜˜˜˜˜ BufferedReader ˜ ˜˜˜˜˜˜˜˜˜˜˜˜˜ FileReader ˜˜˜ ˜˜˜˜˜˜˜˜˜˜˜ ˜˜˜˜˜˜˜˜˜˜
            BufferedReader reader = new BufferedReader(fr);
            // ˜˜˜˜˜˜˜ ˜˜˜˜˜˜˜ ˜˜˜˜˜˜ ˜˜˜˜˜˜
            String line = reader.readLine();
            if (line != null) {
                lines.add(line);
            }
            while (line != null) {
                // ˜˜˜˜˜˜˜˜˜ ˜˜˜˜˜˜˜˜˜ ˜˜˜˜˜˜ ˜ ˜˜˜˜˜
                line = reader.readLine();
                if (line != null) {
                    lines.add(line);
                }
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    @Override
    public void saveAll(List<String> data) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            for (String line : data) {
                // ˜˜˜˜˜˜ ˜˜˜˜ ˜˜˜˜˜˜
                writer.write(line);
                // ˜˜˜˜˜˜ ˜˜ ˜˜˜˜˜˜˜˜
                writer.append('\n');
                
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public List<User> findAll() {
        
        List<String> lines = readAll();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.toOutput(line));
        }
        return users;
    }

    @Override
    public User create(User user) {
        List<User> users = findAll();
        long max = 0L;
        for (User u : users) {
            long id = u.getId();
            if (max < id){
                max = id;
            }
        }
        long next = max + 1;
        user.setId(next);
        users.add(user);
        write(users);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> update(Long userId, User update) {
        List<User> users = findAll();
        User editUser = users.stream()
                .filter(u -> u.getId()
                        .equals(userId))
                .findFirst().orElseThrow(() -> new RuntimeException("User not found"));
        editUser.setFirstName(update.getFirstName());
        editUser.setLastName(update.getLastName());
        editUser.setPhone(update.getPhone());
        write(users);
        return Optional.of(update);
    }

    @Override
    public boolean delete(Long userId) {
        List<User> users = findAll();
        User delUser = users.stream()
                .filter(u -> u.getId()
                        .equals(userId))
                .findFirst().orElseThrow(() -> new RuntimeException("User not found"));

                users.remove(delUser);
        write(users);
        return true;
    }

    @Override
    public void write(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User u: users) {
            lines.add(mapper.toInput(u));
        }
        saveAll(lines);
    }

    public UserMapper getMapper() {
        return mapper;
    }

    public String getDelimeter() {
        return delimeter;
    }
    


    
}
