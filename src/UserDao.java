import java.io.*;
import java.util.*;

public class UserDao {
    private static final String FILE_NAME = "users.txt";

    public static void createUser(User user) throws IOException {
        FileWriter fw = new FileWriter(FILE_NAME, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(user.toString());
        bw.newLine();
        bw.close();
    }

    public static List<User> readUsers() throws IOException {
        List<User> users = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            users.add(new User(data[0], data[1], data[2]));
        }
        br.close();
        return users;
    }

    public static void updateUser(String userId, String newName, String newEmail) throws IOException {
        List<User> users = readUsers();
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));
        for (User user : users) {
            if (user.getId().equals(userId)) {
                bw.write(userId + "," + newName + "," + newEmail);
            } else {
                bw.write(user.toString());
            }
            bw.newLine();
        }
        bw.close();
    }

    public static void deleteUser(String userId) throws IOException {
        List<User> users = readUsers();
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));
        for (User user : users) {
            if (!user.getId().equals(userId)) {
                bw.write(user.toString());
                bw.newLine();
            }
        }
        bw.close();
    }
}
