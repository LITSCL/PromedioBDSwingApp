package cl.inacap.promedioswingapp.frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.inacap.promedioswingappmodelo.dto.Estudiante;
import cl.inacap.promedioswingappmodelo.dao.EstudianteDAO;

public class ModificarEstudianteFrame extends JInternalFrame {
	private JTextField textFieldModificar;
	private JList listEstudiantes;
	static EstudianteDAO daoEstudiante = new EstudianteDAO();
	private JComboBox<String> comboBoxModificar;
	
	//Proceso de creación del frame.
	public ModificarEstudianteFrame() {
		setTitle("Modificar Estudiantes"); //Es es el titulo del frame, se muestra al lado del icono cuando se ejecuta el programa. 
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			
			//Todo este código se ejecuta antes de que se abra el JInternalFrame (Momento ideal para cargar el JList y el JComboBox).
			public void internalFrameOpened(InternalFrameEvent arg0) {
				List<Estudiante> estudiantesIngresados = daoEstudiante.getAll(); //Aca se trae la lista de estudiantes.
				
				if (estudiantesIngresados.isEmpty() == true) { //Aca se revisa si hay estudiantes en la lista, de no ser así se ejecuta el código.
					JOptionPane.showMessageDialog(null, "No hay estudiantes registrados en el sistema"); //El primer parámetro corresponde a la ubicación del mensaje y el segundo parámetro corresponde al texto del mensaje
					dispose(); //Esta instrucción cierra la ventana (JInternal Frame llamado (Eliminar Estudiante)).
				}
				
				DefaultListModel listEstudiantesSeleccionar = new DefaultListModel(); //Se crea un objeto de tipo DefaultListModel.
				
				for (int i = 0; i < estudiantesIngresados.size(); i++) { //Aca se recorre la lista de automoviles.
					listEstudiantesSeleccionar.addElement(estudiantesIngresados.get(i).toString()); //Aca se añaden los automoviles al JList.
				}

				listEstudiantes.setModel(listEstudiantesSeleccionar); //Aca se añaden todos los textos al componente JList.
				
				comboBoxModificar.addItem("Nota 1");
				comboBoxModificar.addItem("Nota 2");
				comboBoxModificar.addItem("Nota 3");
				comboBoxModificar.addItem("Nota 4");
			}
		});
		
		setBounds(100, 100, 700, 445);
		setClosable(true);
		getContentPane().setLayout(null);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(e -> modificarEstudiante(e)); //Aca se añade el Listener del JButton llamado (Modificar) el cual llama al método llamado ModificarAutomovil().
		btnModificar.setBounds(295, 9, 89, 23);
		getContentPane().add(btnModificar);
		
		textFieldModificar = new JTextField();
		textFieldModificar.setBounds(10, 10, 135, 20);
		getContentPane().add(textFieldModificar);
		textFieldModificar.setColumns(10);
		
		comboBoxModificar = new JComboBox<>();
		comboBoxModificar.setBounds(155, 10, 130, 20);
		getContentPane().add(comboBoxModificar);
		
		listEstudiantes = new JList();
		listEstudiantes.setBounds(10, 43, 664, 361);
		getContentPane().add(listEstudiantes);
	}
		
	//Código a ejecutar del Listener del componente JButton llamado (Modificar).
	private void modificarEstudiante(ActionEvent e) {
		List<Estudiante> estudiantesIngresados = new EstudianteDAO().getAll(); //En esta lista se almacenan todos los automoviles ingresados.
		try {
				
			Estudiante estudianteSeleccionado = estudiantesIngresados.get(listEstudiantes.getSelectedIndex()); //Se obtiene el objeto de la lista utilizando el indice de lo que el usuario selecciono en el JList y se almacena en una variable.
			if (comboBoxModificar.getSelectedItem().equals("Nota 1")) { //Si el valor del JComboBox es igual a Patente, entonces ejecuta dicho código.
				if (textFieldModificar.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"- No hay nada escrito en el JTextField", "Error de validación",JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
				}
				else {
					try {
						String modificar = textFieldModificar.getText(); //Se almacena el valor del JTextField en una variable.
						double nota = Double.parseDouble(modificar);
						if (nota < 1.0 || nota > 7.0) {
							JOptionPane.showMessageDialog(null,"- La nota esta fuera de rango (1.0/7.0)","Error de validación",JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
						}
						else {
							estudianteSeleccionado.setNota1(nota); //Aca se modifica su atributo.
							daoEstudiante.update(estudianteSeleccionado); //Aca se selecciona el objeto y se manda a actualizar.
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,"- El valor que ingresó no es válido", "Error de validación",JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
					}
				}
			}
			if (comboBoxModificar.getSelectedItem().equals("Nota 2")) { //Si el valor del JComboBox es igual a Patente, entonces ejecuta dicho código.
				if (textFieldModificar.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "- No hay nada escrito en el JTextField", "Error de validación", JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
				}
				else {
					try {
						String modificar = textFieldModificar.getText(); //Se almacena el valor del JTextField en una variable.
						double nota = Double.parseDouble(modificar);
						if (nota < 1.0 || nota > 7.0) {
							JOptionPane.showMessageDialog(null, "- La nota esta fuera de rango (1.0/7.0)", "Error de validación", JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
						}
						else {
							estudianteSeleccionado.setNota2(nota); //Aca se modifica su atributo.
							daoEstudiante.update(estudianteSeleccionado); //Aca se selecciona el objeto y se manda a actualizar.
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "- El valor que ingresó no es válido", "Error de validación", JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
					}
				}
			}
			if (comboBoxModificar.getSelectedItem().equals("Nota 3")) { //Si el valor del JComboBox es igual a Patente, entonces ejecuta dicho código.
				if (textFieldModificar.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "- No hay nada escrito en el JTextField", "Error de validación", JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
				}
				else {
					try {
						String modificar = textFieldModificar.getText(); //Se almacena el valor del JTextField en una variable.
						double nota = Double.parseDouble(modificar);
						if (nota < 1.0 || nota > 7.0) {
							JOptionPane.showMessageDialog(null, "- La nota esta fuera de rango (1.0/7.0)", "Error de validación", JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar, en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
						}
						else {
							estudianteSeleccionado.setNota3(nota); //Aca se modifica su atributo.
							daoEstudiante.update(estudianteSeleccionado); //Aca se selecciona el objeto y se manda a actualizar.
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "- El valor que ingresó no es válido", "Error de validación", JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
					}
				}
			}
			if (comboBoxModificar.getSelectedItem().equals("Nota 4")) { //Si el valor del JComboBox es igual a Patente, entonces ejecuta dicho código.
				if (textFieldModificar.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "- No hay nada escrito en el JTextField", "Error de validación", JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
				}
				else {
					try {
						String modificar = textFieldModificar.getText(); //Se almacena el valor del JTextField en una variable.
						double nota = Double.parseDouble(modificar);
						if (nota < 1.0 || nota > 7.0) {
							JOptionPane.showMessageDialog(null, "- La nota esta fuera de rango (1.0/7.0)", "Error de validación", JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
						}
						else {
							estudianteSeleccionado.setNota4(nota); //Aca se modifica su atributo.
							daoEstudiante.update(estudianteSeleccionado); //Aca se selecciona el objeto y se manda a actualizar.
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "- El valor que ingresó no es válido", "Error de validación", JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
					}
				}
			}
				
			//Proceso de refrescado del JList.
			DefaultListModel listEstudiantesSeleccionar = new DefaultListModel(); //Se crea un objeto de tipo DefaultListModel.
				
			for (int i = 0; i < estudiantesIngresados.size(); i++) { //Aca se recorre la lista de automoviles.
				listEstudiantesSeleccionar.addElement(estudiantesIngresados.get(i).toString()); //Aca se añaden los automoviles al JList.
			}
			listEstudiantes.setModel(listEstudiantesSeleccionar); //Aca se añaden todos los textos al componente JList.
			
		} catch (Exception ex) {
			if (textFieldModificar.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "- No hay nada escrito en el JTextField \n- No seleccionó ningún automóvil ", "Error de validación", JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
			}
			else {
				JOptionPane.showMessageDialog(null, "- No seleccionó ningún Estudiante", "Error de validación", JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
			}
		}	
	}
}
