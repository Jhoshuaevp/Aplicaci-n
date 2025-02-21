import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Userform extends JFrame {
    private JTextField idField, nameField, emailField;
    private JTextArea userList;

    public Userform() {
        setTitle("Gestión de Usuarios");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("ID:"));
        idField = new JTextField(10);
        add(idField);

        add(new JLabel("Nombre:"));
        nameField = new JTextField(10);
        add(nameField);

        add(new JLabel("Email:"));
        emailField = new JTextField(10);
        add(emailField);

        JButton createButton = new JButton("Crear");
        createButton.addActionListener(e -> createUser());
        add(createButton);

        JButton readButton = new JButton("Leer");
        readButton.addActionListener(e -> readUsers());
        add(readButton);

        JButton updateButton = new JButton("Actualizar");
        updateButton.addActionListener(e -> updateUser());
        add(updateButton);

        JButton deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(e -> deleteUser());
        add(deleteButton);

        userList = new JTextArea(10, 30);
        add(new JScrollPane(userList));

        setVisible(true);
    }

    private void createUser() {
        try {
            User user = new User(idField.getText(), nameField.getText(), emailField.getText());
            UserDao.createUser(user);
            JOptionPane.showMessageDialog(this, "Usuario creado");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al crear usuario");
        }
    }

    private void readUsers() {
        try {
            List<User> users = UserDao.readUsers();
            userList.setText("");
            for (User user : users) {
                userList.append(user.toString() + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer usuarios");
        }
    }

    private void updateUser() {
        try {
            UserDao.updateUser(idField.getText(), nameField.getText(), emailField.getText());
            JOptionPane.showMessageDialog(this, "Usuario actualizado");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar usuario");
        }
    }

    private void deleteUser() {
        try {
            UserDao.deleteUser(idField.getText());
            JOptionPane.showMessageDialog(this, "Usuario eliminado");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar usuario");
        }
    }

    public static void main(String[] args) {
        new Userform();
    }
}

