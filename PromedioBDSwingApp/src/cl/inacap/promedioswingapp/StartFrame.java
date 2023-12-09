package cl.inacap.promedioswingapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import java.awt.event.ActionEvent;

import cl.inacap.promedioswingapp.frame.*;

public class StartFrame extends JFrame {

	private JPanel contentPane;
	private IngresarEstudianteFrame ingresarEstudianteFrame; //Relación de tipo HAS-A (Porque el JFrame tiene JInternalFrame).
	private MostrarEstudianteFrame mostrarEstudianteFrame; //Relación de tipo HAS-A (Porque el JFrame tiene JInternalFrame).
	private EliminarEstudianteFrame eliminarEstudianteFrame; //Relación de tipo HAS-A (Porque el JFrame tiene JInternalFrame).
	private ModificarEstudianteFrame modificarEstudianteFrame; //Relaciónn de tipo HAS-A (Porque el JFrame tiene JInternalFrame).
	private JDesktopPane desktopPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Proceso de creación del frame.
	public StartFrame() {
		setTitle("PromedioSwingApp"); //Es es el titulo del frame, se muestra al lado del icono cuando se ejecuta el programa. 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 652);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);

		//Creación del JMenuItem (Ingresar Estudiante).
		JMenuItem mntmIngresarEstudiante = new JMenuItem("Ingresar Estudiante"); //Aca se crea el JMenuItem llamado (Ingresar Estudiante).
		mntmIngresarEstudiante.addActionListener(e -> showFrameIngresarEstudiante(e)); //Aca se añade el Listener del JmenuItem llamado (Ingresar Estudiante) el cual llama al método llamado showFrameIngresarEstudiante().
		mnOpciones.add(mntmIngresarEstudiante);

		//Creación del JMenuItem (Mostrar Estudiante).
		JMenuItem mntmMostrarEstudiantes = new JMenuItem("Mostrar Estudiantes"); //Aca se crea el JMenuItem llamado (Mostrar Estudiantes).
		mntmMostrarEstudiantes.addActionListener(e -> showFrameMostrarEstudiante(e)); //Aca se añade el Listener del JmenuItem llamado (Mostrar Estudiantes) el cual llama al método llamado showFrameMostrarEstudiante().
		mnOpciones.add(mntmMostrarEstudiantes);
		
		//Creación del JMenuItem (Modificar Estudiante).
		JMenuItem mntmModificarEstudiantes = new JMenuItem("Modificar Estudiantes"); //Aca se crea el JMenuItem llamado (Modificar Estudiantes).
		mntmModificarEstudiantes.addActionListener(e -> showFrameModificarEstudiantes(e)); //Aca se añade el Listener del JmenuItem llamado (Modificar Automoviles) el cual llama al método llamado showFrameModificarAutomoviles().
		mnOpciones.add(mntmModificarEstudiantes);
		
		//Creación del JMenuItem (Eliminar Estudiante).
		JMenuItem mntmEliminarEstudiante = new JMenuItem("Eliminar Estudiante"); //Aca se crea el JMenuItem llamado (Eliminar Estudiante).
		mntmEliminarEstudiante.addActionListener(e -> showFrameEliminarEstudiante(e)); //Aca se añade el Listener del JmenuItem llamado (Eliminar Estudiante) el cual llama al método llamado showFrameEliminarEstudiante().
		mnOpciones.add(mntmEliminarEstudiante);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}

	//Codigo a ejecutar del Listener del componente JMenuItem llamado (Modificar Estudiante).
	private void showFrameModificarEstudiantes(ActionEvent e) {
		if (this.modificarEstudianteFrame != null) { //Verifica si el usuario ya habia abierto el frame antes (Esta es la variable que está arriba como atributo).
			desktopPane.remove(this.modificarEstudianteFrame); //Esta instrucción elimina el frame existente si el usuario ya lo tenia abierto (Se debe utilizar el objeto desktopPane porque es allí donde fue agregado el frame).
		}
		this.modificarEstudianteFrame = new ModificarEstudianteFrame(); //Proceso de creación del nuevo frame (Ya que el anterior se borr+o).
		desktopPane.add(this.modificarEstudianteFrame); //Agregar el frame creado al desktopPane (Se debe utilizar el objeto desktopPane porque es allá donde fue agregado el frame).
		this.modificarEstudianteFrame.setVisible(true); //Hacer visible el nuevo frame.
	}

	//Codigo a ejecutar del Listener del componente JMenuItem llamado (Ingresar Estudiante).
	private void showFrameIngresarEstudiante(ActionEvent e) { //Aqui se muestra el frame de ingresar.
		if (this.ingresarEstudianteFrame != null) { //Verifica si el usuario ya habia abierto el frame antes (Esta es la variable que está arriba como atributo).
			desktopPane.remove(this.ingresarEstudianteFrame); //Esta instrucción elimina el frame existente si el usuario ya lo tenia abierto (Se debe utilizar el objeto desktopPane porque es allá donde fue agregado el frame).
		}
		this.ingresarEstudianteFrame = new IngresarEstudianteFrame(); //Proceso de creación del nuevo frame (Ya que el anterior se borró).
		desktopPane.add(this.ingresarEstudianteFrame); //Agregar el frame creado al desktopPane (Se debe utilizar el objeto desktopPane porque es allá donde fue agregado el frame).
		this.ingresarEstudianteFrame.setVisible(true); //Hacer visible el nuevo frame.
	}
	
	//Codigo a ejecutar del Listener del componente JMenuItem llamado (Mostrar Estudiante).
	private void showFrameMostrarEstudiante(ActionEvent e) { //Aqui se muestra el frame de ingresar.
		if (this.mostrarEstudianteFrame != null) { //Verifica si el usuario ya habia abierto el frame antes (Esta es la variable que está arriba como atributo).
			desktopPane.remove(this.mostrarEstudianteFrame); //Esta instrucción elimina el frame existente si el usuario ya lo tenia abierto (Se debe utilizar el objeto desktopPane porque es allá donde fue agregado el frame).
		}
		this.mostrarEstudianteFrame = new MostrarEstudianteFrame(); //Proceso de creación del nuevo frame (Ya que el anterior se borró).
		desktopPane.add(this.mostrarEstudianteFrame); //Agregar el frame creado al desktopPane (Se debe utilizar el objeto desktopPane porque es allá donde fue agregado el frame).
		this.mostrarEstudianteFrame.setVisible(true); //Hacer visible el nuevo frame.
	}
	
	//Codigo a ejecutar del Listener del componente JMenuItem llamado (Eliminar Estudiante).
		private void showFrameEliminarEstudiante(ActionEvent e) { //Aqui se muestra el frame de ingresar.
			if (this.eliminarEstudianteFrame != null) { //Verifica si el usuario ya habia abierto el frame antes (Esta es la variable que está arriba como atributo).
				desktopPane.remove(this.eliminarEstudianteFrame); //Esta instrucción elimina el frame existente si el usuario ya lo tenia abierto (Se debe utilizar el objeto desktopPane porque es allá donde fue agregado el frame).
			}
			this.eliminarEstudianteFrame = new EliminarEstudianteFrame(); //Proceso de creación del nuevo frame (Ya que el anterior se borró).
			desktopPane.add(this.eliminarEstudianteFrame); //Agregar el frame creado al desktopPane (Se debe utilizar el objeto desktopPane porque es allá donde fue agregado el frame).
			this.eliminarEstudianteFrame.setVisible(true); //Hacer visible el nuevo frame.
		}
}
