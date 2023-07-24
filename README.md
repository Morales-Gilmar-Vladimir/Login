Integrantes:
1.	Gilmar Morales
2.	Paúl Hidalgo
RegistrarUsuario 
El código proporcionado es una aplicación de Java Swing que permite registrar usuarios y sus contraseñas en una lista, y luego guardar esa información en un archivo llamado "usuarios.dat".
1. Importaciones
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
Se importan las clases necesarias para construir la interfaz gráfica (javax.swing.), manejar eventos de botones (java.awt.event.), trabajar con archivos (java.io.*) y utilizar las colecciones de Listas (java.util.ArrayList, java.util.List).
2. Declaración de la clase RegistrarUsuario
public class RegistrarUsuario {
}
3. Declaración de componentes y listas
private JPanel rootPanel;
private JTextField usuario;
private JPasswordField clave;
private JLabel Nombre;
private JLabel Contra;
private JButton guardarButton;

private List<String> usuarios;
private List<String> claves;
Aquí se definen los componentes de la interfaz gráfica (rootPanel, usuario, clave, Nombre, Contra y guardarButton), así como dos listas (usuarios y claves) para almacenar los datos introducidos por el usuario.
4. Constructor RegistrarUsuario
public RegistrarUsuario() {
    usuarios = new ArrayList<>();
    claves = new ArrayList<>();

    // ActionListener para el botón guardarButton
    guardarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Obtener el nombre de usuario y la contraseña ingresados
            String nombreUsuario = usuario.getText();
            String claveUsuario = new String(clave.getPassword());

            // Agregar el nombre de usuario y contraseña a las listas
            usuarios.add(nombreUsuario);
            claves.add(claveUsuario);

            // Guardar usuarios en el archivo
            guardarUsuariosEnArchivo();

            // Limpiar los campos de texto para permitir un nuevo registro
            usuario.setText("");
            clave.setText("");
        }
    });
}
En el constructor de la clase, se inicializan las listas usuarios y claves como instancias de ArrayList. Luego, se agrega un ActionListener al botón guardarButton para manejar el evento de clic sobre el botón. Cuando el botón es presionado, el ActionListener obtiene el texto ingresado en los campos de texto usuario y clave, los agrega a las listas respectivas y llama al método guardarUsuariosEnArchivo() para guardar los datos en el archivo "usuarios.dat". Finalmente, los campos de texto se limpian para permitir un nuevo registro.
5. Método guardarUsuariosEnArchivo()
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
Este método se encarga de guardar las listas usuarios y claves en el archivo "usuarios.dat". Utiliza un ObjectOutputStream para escribir los datos en el archivo. Si ocurre algún error durante el proceso de escritura, se muestra un mensaje de error.
6. Método main()
public static void main(String[] args) {
    JFrame frame = new JFrame("Registrar Usuario");
    frame.setContentPane(new RegistrarUsuario().rootPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
}
El método main() es el punto de entrada de la aplicación. En él, se crea una instancia de la clase RegistrarUsuario, se obtiene el panel rootPanel y se establece como contenido principal de un JFrame. Luego se configuran algunas propiedades del JFrame y se muestra la ventana.

