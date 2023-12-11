package cl.inacap.promedioswingapp.frame;

import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import cl.inacap.promedioswingappmodelo.dto.Estudiante;
import cl.inacap.promedioswingappmodelo.dao.EstudianteDAO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;

public class MostrarEstudianteFrame extends JInternalFrame { //Esta clase hereda de JInternalFrame (Son los Frames que se muestran cuando se ejecuta el codigo de los JMenuItem).
	private JTable tableMostrarEstudiante; 
	private JComboBox<String> comboBoxFiltrarEstudiantes; //En esta variable se almacena el valor del JComboBox.
	private JButton btnFiltrar;

	//Proceso de creación del frame.
	public MostrarEstudianteFrame() { //Este es el frame que se muestra cuando se escucha el JMenuItem llamado (Mostrar Estudiante).
		setTitle("Mostrar Estudiantes"); //Es es el titulo del frame, se muestra al lado del icono cuando se ejecuta el programa. 
		//Todo este código se ejecuta antes de que se abra el JInternalFrame (Momento ideal para cargar el JComboBox).
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				List<Estudiante> estudiantesIngresados = new EstudianteDAO().getAll(); //En esta lista se almacenan todos los estudiantes ingresados.
				
				if (estudiantesIngresados.isEmpty() == true) { //Aca se revisa si hay estudiantes en la lista, de no ser así se ejecuta el código.
					JOptionPane.showMessageDialog(null, "No hay estudiantes registrados en el sistema"); //El primer parámetro corresponde a la ubicación del mensaje y el segundo parámetro corresponde al texto del mensaje
					dispose(); //Esta instrucción cierra la ventana (JInternal Frame llamado (Eliminar Estudiante)).
				}
				
				//Proceso de agregadro de items al JComboBox.
				comboBoxFiltrarEstudiantes.addItem("Promedio menor que 4.0");
				comboBoxFiltrarEstudiantes.addItem("Promedio igual a 4.0");
				comboBoxFiltrarEstudiantes.addItem("Promedio mayor que 4.0");
			}
		});
		
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 664, 363);
		getContentPane().add(scrollPane);
		
		tableMostrarEstudiante = new JTable();
		tableMostrarEstudiante.setEnabled(false);
		scrollPane.setViewportView(tableMostrarEstudiante);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(e -> filtrarEstudiantes(e)); //Aca se añade el Listener del JButton llamado (Filtrar) el cual llama al método llamado filtrarEstudiantes().
		btnFiltrar.setBounds(295, 9, 89, 23);
		getContentPane().add(btnFiltrar);
		
		comboBoxFiltrarEstudiantes = new JComboBox<>();
		comboBoxFiltrarEstudiantes.setBounds(10, 11, 233, 20);
		getContentPane().add(comboBoxFiltrarEstudiantes);
		cargarTabla();
	}
	
	//Proceso de muestreo con filtros (El filtro se hace en la campa dao, aca solo se muestran).
	private void filtrarEstudiantes(ActionEvent e) {
		
		String seleccion = (String)this.comboBoxFiltrarEstudiantes.getSelectedItem(); //En esta variable se almacena el valor del JComboBox.
		
		//1. Proceso de creación del TableModel.
		DefaultTableModel mo = new DefaultTableModel(); //Este es el modelo donde van a ir las filas y columnas.
		
		//2. Proceso de llenado de columnas.
		mo.addColumn("Nombre"); //Se añade una columna llamada (Nombre).
		mo.addColumn("Asignatura"); //Se añade una columna llamada (Asignatura).
		mo.addColumn("Promedio"); //Se añade una columna llamada (Promedio).
		
		//3. Proceso de traer la lista y de agregado de filas.
			
			if ("Promedio menor que 4.0".equals(seleccion)) { //Si el promedio es menor que 4 y la cedena coincide con lo que seleccionó el usuario, el código se ejecuta.
				List<Estudiante> estudiantesFiltrados = new EstudianteDAO().filtrarEstudiante("<4.0"); //En esta lista se almacenan todos los estudiantes que estan filtrados según el parámetro dado.
				
				for(Estudiante es : estudiantesFiltrados) {

					Object[] fila = new Object[3];
					fila[0] = es.getNombre();
					fila[1] = es.getAsignatura();
					fila[2] = es.getPromedio();
					mo.addRow(fila);
				}
				
			}
			else if ("Promedio igual a 4.0".equals(seleccion)) {
				List<Estudiante> estudiantesFiltrados = new EstudianteDAO().filtrarEstudiante("=4.0"); //En esta lista se almacenan todos los estudiantes que estan filtrados según el parámetro dado.
				
				for (Estudiante es : estudiantesFiltrados) {

					Object[] fila = new Object[3];
					fila[0] = es.getNombre();
					fila[1] = es.getAsignatura();
					fila[2] = es.getPromedio();
					mo.addRow(fila);
				}
				
			} else if("Promedio mayor que 4.0".equals(seleccion)) {
				List<Estudiante> estudiantesFiltrados = new EstudianteDAO().filtrarEstudiante(">4.0"); //En esta lista se almacenan todos los estudiantes que estan filtrados según el parámetro dado.
			
				for (Estudiante es : estudiantesFiltrados) {

					Object[] fila = new Object[3];
					fila[0] = es.getNombre();
					fila[1] = es.getAsignatura();
					fila[2] = es.getPromedio();
					mo.addRow(fila);
				}
			
			}
		
		//4. Proceso de definir en la tabla el TableModel.
		tableMostrarEstudiante.setModel(mo);
	}

	private void cargarTabla() {
		//1. Proceso de traer los estudiantes ingresados.
		List<Estudiante> estudiantesIngresados = new EstudianteDAO().getAll(); //En esta lista se almacenan todos los estudiantes ingresados.
		
		//2. Proceso de creación del TableModel.
		DefaultTableModel mo = new DefaultTableModel(); //Este es el modelo donde van a ir las filas y columnas.
		
		//3. Proceso de llenado de columnas.
		mo.addColumn("Nombre"); //Se añade una columna llamada (Nombre).
		mo.addColumn("Asignatura"); //Se añade una columna llamada (Asignatura).
		mo.addColumn("Promedio"); //Se añade una columna llamada (Promedio).
		
		//4. Proceso de agregado de filas.
		for (Estudiante es : estudiantesIngresados) {
			Object[] fila = new Object[3];
			fila[0] = es.getNombre();
			fila[1] = es.getAsignatura();
			fila[2] = es.getPromedio();
			mo.addRow(fila);
		}
		
		//5. Proceso de definir en la tabla el TableModel.
		tableMostrarEstudiante.setModel(mo);
	}
}
