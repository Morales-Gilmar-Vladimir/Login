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

LoginUsuario 

El código proporcionado es una aplicación de Java Swing que permite a los usuarios iniciar sesión y, si aún no están registrados, pueden registrarse a través de un botón adicional. La aplicación almacena los nombres de usuario y sus contraseñas en un archivo llamado "usuarios.dat”.

1. Importaciones

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
Se importan las clases necesarias para construir la interfaz gráfica (javax.swing.), manejar eventos de botones (java.awt.event.), trabajar con archivos (java.io.*) y utilizar las colecciones de Listas (java.util.ArrayList, java.util.List).

2. Declaración de la clase LoginUsuario


public class LoginUsuario {
    // ...
}

3. Declaración de componentes y listas

private JLabel Nombre;
private JLabel Contra;
private JPasswordField passwordField1;
private JTextField textField1;
private JButton inicioBoton;
private JButton registrarUsuarioButton;
private JPanel rootPanel;

private List<String> usuarios;
private List<String> claves;
En esta sección, se definen los componentes de la interfaz gráfica (Nombre, Contra, passwordField1, textField1, inicioBoton, registrarUsuarioButton y rootPanel), así como dos listas (usuarios y claves) para almacenar los datos de los usuarios.

4. Constructor LoginUsuario

public LoginUsuario() {
    usuarios = new ArrayList<>();
    claves = new ArrayList<>();

    cargarUsuariosDesdeArchivo();

    // ActionListener para el botón de inicio de sesión (inicioBoton)
    inicioBoton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nombreUsuario = textField1.getText();
            String claveUsuario = new String(passwordField1.getPassword());

            // Buscar el nombre de usuario en la lista de usuarios
            int indiceUsuario = usuarios.indexOf(nombreUsuario);
            if (indiceUsuario != -1 && claves.get(indiceUsuario).equals(claveUsuario)) {
                // Si se encuentra el usuario y la contraseña coincide, mostrar el formulario del usuario
                mostrarFormularioUsuario(nombreUsuario);
            } else {
                // Si el usuario o contraseña son incorrectos, mostrar un mensaje de error
                JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña incorrectos.",
                        "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
            }

            // Limpiar los campos de texto para permitir un nuevo inicio de sesión
            textField1.setText("");
            passwordField1.setText("");
        }
    });

    // ActionListener para el botón de registrar usuario (registrarUsuarioButton)
    registrarUsuarioButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Abrir una nueva ventana para el registro de usuarios
            JFrame frame = new JFrame("Registrar Usuario");
            frame.setContentPane(new RegistrarUsuario().rootPanel);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    });
}
En el constructor de la clase, se inicializan las listas usuarios y claves como instancias de ArrayList. Luego, se llama al método cargarUsuariosDesdeArchivo() para cargar los datos previamente registrados desde el archivo "usuarios.dat". Además, se agrega un ActionListener al botón inicioBoton para manejar el evento de clic sobre el botón de inicio de sesión. Cuando se presiona este botón, el ActionListener obtiene el nombre de usuario y la contraseña ingresados en los campos de texto y verifica si coinciden con los datos almacenados en las listas usuarios y claves. Si la autenticación es exitosa, se llama al método mostrarFormularioUsuario(nombreUsuario) para mostrar un formulario de usuario. En caso contrario, se muestra un mensaje de error.

También se agrega un ActionListener al botón registrarUsuarioButton para manejar el evento de clic sobre el botón de registro de usuario. Cuando se presiona este botón, se abre una nueva ventana que permite el registro de un nuevo usuario utilizando la clase RegistrarUsuario.

5. Método cargarUsuariosDesdeArchivo()

private void cargarUsuariosDesdeArchivo() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usuarios.dat"))) {
        usuarios = (List<String>) ois.readObject();
        claves = (List<String>) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}
Este método se encarga de cargar los datos previamente registrados desde el archivo "usuarios.dat" y los almacena en las listas usuarios y claves. Utiliza un ObjectInputStream para leer los datos del archivo. Si ocurre algún error durante el proceso de lectura, se imprime la traza de la excepción.

6. Método mostrarFormularioUsuario(String nombreUsuario)

private void mostrarFormularioUsuario(String nombreUsuario) {
    JFrame frame = new JFrame("Formulario de Usuario");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(300, 100);
    frame.setLayout(new FlowLayout());

    JLabel lblUsuario = new JLabel("Usuario: " + nombreUsuario);
    frame.add(lblUsuario);

    frame.setVisible(true);
}
Este método crea una nueva ventana (JFrame) para mostrar el formulario del usuario. En este caso, simplemente muestra una etiqueta con el nombre del usuario.

7. Método main()

public static void main(String[] args) {
    JFrame frame = new JFrame("Login de Usuario");
    frame.setContentPane(new LoginUsuario().rootPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
}
El método main() es el punto de entrada de la aplicación. En él, se crea una instancia de la clase LoginUsuario, se obtiene el panel rootPanel y se establece como contenido principal de un JFrame. Luego se configuran algunas propiedades del JFrame y se muestra la ventana.
