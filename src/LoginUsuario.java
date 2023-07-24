import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoginUsuario {
    private JLabel Nombre;
    private JLabel Contra;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton inicioBoton;
    private JButton registrarUsuarioButton;
    private JPanel rootPanel;

    private List<String> usuarios;
    private List<String> claves;

    public LoginUsuario() {
        usuarios = new ArrayList<>();
        claves = new ArrayList<>();

        cargarUsuariosDesdeArchivo();

        inicioBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = textField1.getText();
                String claveUsuario = new String(passwordField1.getPassword());

                int indiceUsuario = usuarios.indexOf(nombreUsuario);
                if (indiceUsuario != -1 && claves.get(indiceUsuario).equals(claveUsuario)) {
                    mostrarFormularioUsuario(nombreUsuario);
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña incorrectos.",
                            "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
                }

                textField1.setText("");
                passwordField1.setText("");
            }
        });

        registrarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Registrar Usuario");
                frame.setContentPane(new RegistrarUsuario().rootPanel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    private void cargarUsuariosDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usuarios.dat"))) {
            usuarios = (List<String>) ois.readObject();
            claves = (List<String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void mostrarFormularioUsuario(String nombreUsuario) {
        JFrame frame = new JFrame("Formulario de Usuario");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLayout(new FlowLayout());

        JLabel lblUsuario = new JLabel("Usuario: " + nombreUsuario);
        frame.add(lblUsuario);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login de Usuario");
        frame.setContentPane(new LoginUsuario().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}