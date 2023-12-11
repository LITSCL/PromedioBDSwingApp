package cl.inacap.promedioswingapp.frame;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.inacap.promedioswingappmodelo.dao.EstudianteDAO;
import cl.inacap.promedioswingappmodelo.dto.Estudiante;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EliminarEstudianteFrame extends JInternalFrame {
	private JComboBox<Estudiante> comboBoxSelecionarEstudianteEliminar;
	private JButton btnEliminar;
	private EstudianteDAO daoEstudiante = new EstudianteDAO();

	//Proceso de creación del frame.
	public EliminarEstudianteFrame() {
		setTitle("Eliminar Estudiante"); //Es es el titulo del frame, se muestra al lado del icono cuando se ejecuta el programa. 
		setClosable(true);
		
		//Todo este código se ejecuta antes de que se abra el JInternalFrame (Momento ideal para cargar el JComboBox).
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				List<Estudiante> estudiantesIngresados = daoEstudiante.getAll(); //Aca se trae la lista de estudiantes.
				
				if (estudiantesIngresados.isEmpty() == true) { //Aca se revisa si hay estudiantes en la lista, de no ser así se ejecuta el código.
					JOptionPane.showMessageDialog(null, "No hay estudiantes registrados en el sistema"); //El primer parámetro corresponde a la ubicacón del mensaje y el segundo parámetro corresponde al texto del mensaje
					dispose(); //Esta instrucción cierra la ventana (JInternal Frame llamado (Eliminar Estudiante)).
				}
				else { //Si la lista tiene estudiantes, entonces se ejecuta este código.
					for (Estudiante i : estudiantesIngresados) { //Aca se recorre la lista de estudiantes.
						comboBoxSelecionarEstudianteEliminar.addItem(i); //Se añaden los objetos de tipo Estudiante que están almacenados en la lista al JComboBox.
					}
				}
			}
		});
		
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione Estudiante a Eliminar");
		lblNewLabel.setBounds(243, 51, 211, 14);
		getContentPane().add(lblNewLabel);
		
		comboBoxSelecionarEstudianteEliminar = new JComboBox<>();
		comboBoxSelecionarEstudianteEliminar.setBounds(10, 75, 664, 20);
		getContentPane().add(comboBoxSelecionarEstudianteEliminar);
		
		//Aca se crea el botón llamado (Eliminar)
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(e -> eliminarEstudiante()); //Aca se añade el listener al botón, el cual llama al método llamado eliminarEstudiante().
		btnEliminar.setBounds(294, 106, 89, 23);
		getContentPane().add(btnEliminar);

	}

	private void eliminarEstudiante() {
		int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el estudiante?","Eliminar Estudiante", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE); //El primer parámetro indica el mensaje de la ventana, el segundo parámetro indica el nombre de la ventana, el tercer parámetro indica el tipo de opción y el cuarto parámetro indica el icono del tipo de mensaje (Esta instrucción retorna un dato de tipo int (0=Si/1=No)).
		
		if (respuesta == JOptionPane.YES_OPTION) { //Aca se consulta si la variable respuesta es igual a 1 (En otras palabras si el usuario dio click a "YES" en el botón de la ventana).
			Estudiante eliminado = (Estudiante)comboBoxSelecionarEstudianteEliminar.getSelectedItem(); //la variable eliminada almacena lo que está seleccionado en el JCombobox (Se esta haciendo down casting sinonimo de refundición de dato).
			daoEstudiante.delete(eliminado); //Aca se elimina de la lista el estudiante que está seleccionado en el JCombobox.
			JOptionPane.showMessageDialog(null, "Estudiante Eliminado"); //Aca se muestra el mensaje cuando se elimina un estudiante.
			this.dispose(); //Esta instrucción cierra la ventana (JInternal Frame llamado (Eliminar Estudiante)).
		}
	}
}
