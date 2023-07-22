import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrarUsuario {
    private JPanel rootPanel;
    private JTextField usuario;
    private JPasswordField clave;
    private JLabel Nombre;
    private JLabel Contra;
    private JButton guardarButton;

    private List<String> usuarios;
    private List<String> claves;

    public RegistrarUsuario() {
        usuarios = new ArrayList<>();
        claves = new ArrayList<>();

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = usuario.getText();
                String claveUsuario = new String(clave.getPassword());

                usuarios.add(nombreUsuario);
                claves.add(claveUsuario);

                guardarUsuariosEnArchivo();

                usuario.setText("");
                clave.setText("");
            }
        });
    }

    private void guardarUsuariosEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuarios.dat"))) {
            oos.writeObject(usuarios);
            oos.writeObject(claves);
            JOptionPane.showMessageDialog(rootPanel, "Usuario guardado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPanel, "Error al guardar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Registrar Usuario");
        frame.setContentPane(new RegistrarUsuario().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}