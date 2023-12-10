package cl.inacap.promedioswingapp.frame;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.inacap.promedioswingappmodelo.dto.Estudiante;
import cl.inacap.promedioswingappmodelo.dao.EstudianteDAO;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class IngresarEstudianteFrame extends JInternalFrame { //Esta clase hereda de JInternalFrame (Son los Frames que se muestran cuando se ejecuta el codigo de los JMenuItem).
	private JTextField textFieldNombre; 
	private JTextField textFieldNota4;
	private JTextField textFieldNota1;
	private JTextField textFieldNota2;
	private JTextField textFieldNota3;
	private JComboBox <String> comboBoxAsignatura; //A los JComboBox siempre hay que asignarles el tipo de dato que van a mostrar, y al momento de crear el objeto deben tener un <>.

	//Proceso de creación del frame.
	public IngresarEstudianteFrame() {
		setTitle("Ingresar Estudiante"); //Es es el titulo del frame, se muestra al lado del icono cuando se ejecuta el programa. 
		setClosable(true);
		
		//Todo este código se ejecuta antes de que se abra el JInternalFrame (Momento ideal para cargar el JComboBox).
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) { //Aca se crea el listener del JComboBox llamado (Asignautras).
				String[] asignaturas = { //Este Array se puede reemplazar por un DAO.
					"Programación Orientada a Objetos",
					"Programación de Componentes Web",
					"Desarrollo de Aplicaciones Web",
					"Analisis y Diseño Orientado a Objeto",
					"Inglés",
					"Matemática Aplicada"			
				};
				
				//Proceso de agregación de items al JComboBox.
				for(String i : asignaturas) { //Aca se recorre el Array llamado asignaturas.
					comboBoxAsignatura.addItem(i); //Aca se agregan los items al JComboBox.
				}
			}
		}); 
		
		//Este es el frame que se muestra cuando se escucha el JMenuItem llamado (Ingresar Estudiante).
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		//Todos estos componentes están dentro del frame.
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(51, 30, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nota 1");
		lblNewLabel_1.setBounds(51, 80, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Asignatura");
		lblNewLabel_2.setBounds(51, 55, 79, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nota 2");
		lblNewLabel_3.setBounds(51, 105, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Nota 3");
		lblNewLabel_4.setBounds(51, 130, 46, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Nota 4");
		lblNewLabel_5.setBounds(51, 155, 46, 14);
		getContentPane().add(lblNewLabel_5);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(e -> ingresarEstudiante(e)); //Aca se añade el Listener del JButton llamado (Agregar) el cual llama al método llamado ingresarEstudiante().
		btnAgregar.setBounds(295, 197, 89, 23);
		getContentPane().add(btnAgregar);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(120, 27, 86, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldNota4 = new JTextField();
		textFieldNota4.setBounds(120, 152, 86, 20);
		getContentPane().add(textFieldNota4);
		textFieldNota4.setColumns(10);
		
		textFieldNota1 = new JTextField();
		textFieldNota1.setBounds(120, 77, 86, 20);
		getContentPane().add(textFieldNota1);
		textFieldNota1.setColumns(10);
		
		textFieldNota2 = new JTextField();
		textFieldNota2.setBounds(120, 102, 86, 20);
		getContentPane().add(textFieldNota2);
		textFieldNota2.setColumns(10);
		
		textFieldNota3 = new JTextField();
		textFieldNota3.setBounds(120, 127, 86, 20);
		getContentPane().add(textFieldNota3);
		textFieldNota3.setColumns(10);
		
		comboBoxAsignatura = new JComboBox<>(); //Si al JComboBox se le añadió un tipo de dato, a la creacin del objeto hay que añadirle un <>.
		comboBoxAsignatura.setBounds(120, 52, 264, 20);
		getContentPane().add(comboBoxAsignatura); 

	}
	
	//Código a ejecutar del Listener del componente JButton llamado (Agregar).
	private void ingresarEstudiante(ActionEvent e) {
		List<String> errores = new ArrayList<String>(); //Aca se crea una lista, la cual almacenará todos los posible errores cometidos por el usuario.
		String nombre = this.textFieldNombre.getText().trim(); //Aca se almacena lo que el usuario ingresa en el componente, similar a un Scanner.
		if (nombre.isEmpty() == true) {
			errores.add("- Tiene que ingresar un nombre");
		}
		
		double nota1 = -1;
		try {
			nota1 = Double.parseDouble(this.textFieldNota1.getText().trim()); //Aca se convierte lo que ingresó el usuario en el componente y se intenta convertir a double.
			if (nota1 < 1.0 || nota1 > 7.0) {
				errores.add("La nota esta fuera de rango (1.0/7.0)");
			}
		} catch (Exception ex) {
			errores.add("- La Nota 1 debe ser un número");
		}
		
		double nota2 = -1;
		try {
			nota2 = Double.parseDouble(this.textFieldNota2.getText().trim()); //Aca se convierte lo que ingresó el usuario en el componente y se intenta convertir a double.
			if (nota2 < 1.0 || nota2 > 7.0) {
				errores.add("La nota esta fuera de rango (1.0/7.0)");
			}
		} catch (Exception ex) {
			errores.add("- La Nota 2 debe ser un número");
		}
		
		double nota3 = -1;
		try {
			nota3 = Double.parseDouble(this.textFieldNota3.getText().trim()); //Aca se convierte lo que ingresó el usuario en el componente y se intenta convertir a double.
			if(nota3 < 1.0 || nota3 > 7.0) {
				errores.add("La nota esta fuera de rango (1.0/7.0)");
			}
		} catch (Exception ex) {
			errores.add("- La Nota 3 debe ser un número");
		}
		
		double nota4 = -1;
		try {
			nota4 = Double.parseDouble(this.textFieldNota4.getText().trim()); //Aca se convierte lo que ingresó el usuario en el componente y se intenta convertir a double.
			if (nota4 < 1.0 || nota4 > 7.0) {
				errores.add("La nota esta fuera de rango (1.0/7.0)");
			}
		} catch (Exception ex) {
			errores.add("- La Nota 4 debe ser un número");
		}
		
		//Proceso de muestreo de errores (Solo se ejecuta si existen errores).
		if (!errores.isEmpty()) { //Si la lista tiene errores (no está vacia), se ejecuta el código.
			String mensaje = String.join("\n", errores); //Esta instrucción almacena todos los elementos del la lista llamada errores separados por un \n.
			JOptionPane.showMessageDialog(null, mensaje, "Error de validación", JOptionPane.WARNING_MESSAGE); //El primer parámetro siempre debe ser null para que el mensaje se centre, en el segundo parámetro va el mensaje a mostrar,en el tercer parámetro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
		}
		//Proceso de creación del objeto de tipo Estudiante (Solo se ejecuta si no existen errores).
		else {
			Estudiante es = new Estudiante();
			es.setNombre(nombre);
			es.setNota1(nota1);
			es.setNota2(nota2);
			es.setNota3(nota3);
			es.setNota4(nota4);
			String asignaturaSeleccionada = (String)this.comboBoxAsignatura.getSelectedItem(); //Tiene que refundirse el dato a tipo String porque el atributo del JComboBox lo declaramos como String.
			es.setAsignatura(asignaturaSeleccionada);
			
			new EstudianteDAO().save(es); //Solo utilizar esta manera de agregar objetos al dao si solo un botón en todo el programa va a añadir los objetos, en caso contrario crear un objeto estatico y en esta linea llama a la instancia.
			
			JOptionPane.showMessageDialog(null, "Estudiante ingresado"); //Esta instrucción muestra un mensaje cuando el estudiante es ingresado.
			this.dispose(); //Esta instrucción cierra la ventana (JInternal Frame llamado (Ingresar Estudiante)).
		}
	}
}
