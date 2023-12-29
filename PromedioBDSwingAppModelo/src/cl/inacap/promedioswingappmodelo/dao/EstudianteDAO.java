package cl.inacap.promedioswingappmodelo.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import cl.inacap.promedioswingappmodelo.dto.Estudiante;
import cl.inacap.promedioswingappmodelo.util.BDUtil;

public class EstudianteDAO {
	private BDUtil bdUtils = new BDUtil();
	
	//Método que añaade objetos de tipo Estudiante a la tabla automoviles que se encuentra en la base de datos.
	public boolean save(Estudiante e) { //Este método inserta el estudiante a la base de datos.
		//Todos los métodos que se conecten a una base de datos deben seguir los 4 siguientes pasos:
		boolean resultado;
		try {
			//1. Conectarse a la base de datos.
			bdUtils.conectar();
			System.out.println("Conexión a la DB: " + bdUtils.conectar());
			//2. Definir la sentencia sql (INSERT).
			String sql = "INSERT INTO estudiante" + "(nombre, asignatura, nota_1, nota_2, nota_3, nota_4)" + " VALUES(?, ?, ?, ?, ?, ?)"; //Los ID Autoincrementales no van aca, ya que el dbms asigna su valor.
			Connection co = bdUtils.getConexion(); //Esta instrucción retorna la conexión activa.
			PreparedStatement st = co.prepareStatement(sql); //Aca se prepara el statement.
			st.setString(1,e.getNombre());
			st.setString(2,e.getAsignatura());
			st.setDouble(3,e.getNota1());
			st.setDouble(4,e.getNota2());
			st.setDouble(5,e.getNota3());
			st.setDouble(6,e.getNota4());
			//3. Ejecutar el SQL.
			st.executeUpdate();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			//4. Desconectarse.
		} finally { //Esta instrucción se ejecuta se caiga o no el programa.
			bdUtils.desconectar(); //Envia la petición de desconexión al dbms.
		}
		return resultado;
	}
	
	//Método que trae todos los objetos de la tabla llamada automoviles que se encuentra en la base de datos.
	public List<Estudiante> getAll() {
		//Todos los métodos que se conecten a una base de datos deben seguir los 4 siguientes pasos:
		boolean resultado;
		List<Estudiante> estudiantes = new ArrayList<>(); //La lista ya no debe estar arriba estatica ya que el contenido va a variar.
		try {
			//1. Conectarse a la base de datos.
			bdUtils.conectar();
			System.out.println("Conexión a la DB: " + bdUtils.conectar());
			//2. Definir la sentencia sql (SELECT).
			String sql = "SELECT id, nombre, asignatura, nota_1, nota_2, nota_3, nota_4" + " FROM estudiante";
			PreparedStatement st = bdUtils.getConexion().prepareStatement(sql); //Aca se trae la conexón y se prepara el statement.
			//3. Ejecutar el SQL.
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { //Se repite mientras avance el puntero.
				Estudiante es = new Estudiante();
				es.setId(rs.getInt(1)); //Aca se trae el valor del atributo desde la base de datos a java (INT A INT), se modifica utilizando el método setter.
				es.setNombre(rs.getString(2)); //Aca se trae el valor del atributo desde la base de datos a java (VARCHAR A STRING), se modifica utilizando el método setter.
				es.setAsignatura(rs.getString(3)); //Aca se trae el valor del atributo desde la base de datos a java (VARCHAR A STRING), se modifica utilizando el método setter.
				es.setNota1(rs.getDouble(4)); //Aca se trae el valor del atributo desde la base de datos a java (DOUBLE A DOUBLE), se modifica utilizando el método setter.
				es.setNota2(rs.getDouble(5)); //Aca se trae el valor del atributo desde la base de datos a java (DOUBLE A DOUBLE), se modifica utilizando el método setter.
				es.setNota3(rs.getDouble(6)); //Aca se trae el valor del atributo desde la base de datos a java (DOUBLE A DOUBLE), se modifica utilizando el método setter.
				es.setNota4(rs.getDouble(7)); //Aca se trae el valor del atributo desde la base de datos a java (DOUBLE A DOUBLE), se modifica utilizando el método setter.
				estudiantes.add(es); //Añade el estudiante a la lista (Dicha lista es la que se muestra en la interfaz).
			}
			rs.close(); //Se cierra el puntero.
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			estudiantes = null;
			//4. Desconectarse.
		} finally { //Esta instrucción se ejecuta se caiga o no el programa.
			bdUtils.desconectar(); //Envia la petición de desconexión al dbms.
		}
		
		return estudiantes;
	}
	
	//Metodo que trae objetos según un filtro de la tabla automoviles que se encuentra en la base de datos.
	public List<Estudiante> filtrarEstudiante(String filtro) {
		//Todos los métodos que se conecten a una base de datos deben seguir los 4 siguientes pasos:
		boolean resultado;
		List<Estudiante> estudiantes = new ArrayList<>(); //La lista ya no debe estar arriba estatica ya que el contenido va a variar.
		try {
			//1. Conectarse a la base de datos.
			bdUtils.conectar();
			System.out.println("Conexión a la DB: " + bdUtils.conectar());
			//2. Definir la sentencia sql (SELECT).
			String sql = "SELECT id, nombre, asignatura, nota_1, nota_2, nota_3, nota_4" + " FROM estudiante";
			sql+=" WHERE " + "(nota_1 + nota_2 + nota_3 + nota_4) / 4" + filtro;
			PreparedStatement st = bdUtils.getConexion().prepareStatement(sql); //Aca se trae la conexión y se prepara el statement.
			
			//3. Ejecutar el SQL.
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { //Se repite mientras avance el puntero.
				Estudiante es = new Estudiante();
				es.setId(rs.getInt(1)); //Aca se trae el valor del atributo desde la base de datos a java (INT A INT), se modifica utilizando el método setter.
				es.setNombre(rs.getString(2)); //Aca se trae el valor del atributo desde la base de datos a java (VARCHAR A STRING), se modifica utilizando el método setter.
				es.setAsignatura(rs.getString(3)); //Aca se trae el valor del atributo desde la base de datos a java (VARCHAR A STRING), se modifica utilizando el método setter.
				es.setNota1(rs.getDouble(4)); //Aca se trae el valor del atributo desde la base de datos a java (DOUBLE A DOUBLE), se modifica utilizando el método setter.
				es.setNota2(rs.getDouble(5)); //Aca se trae el valor del atributo desde la base de datos a java (DOUBLE A DOUBLE), se modifica utilizando el método setter.
				es.setNota3(rs.getDouble(6)); //Aca se trae el valor del atributo desde la base de datos a java (DOUBLE A DOUBLE), se modifica utilizando el método setter.
				es.setNota4(rs.getDouble(7)); //Aca se trae el valor del atributo desde la base de datos a java (DOUBLE A DOUBLE), se modifica utilizando el método setter.
				estudiantes.add(es); //Añade el estudiante a la lista (Dicha lista es la que se muestra en la interfaz).
			}
			rs.close(); //Se cierra el puntero.
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			estudiantes = null;
			//4. Desconectarse.
		} finally { //Esta instrucción se ejecuta se caiga o no el programa.
			bdUtils.desconectar(); //Envia la petición de desconexión al dbms.
		}
		
		return estudiantes;
	}
	
	public void update(Estudiante e) {
		//Todos los métodos que se conecten a una base de datos deben seguir los 4 siguientes pasos:
		boolean resultado;
		try {
			//1. Conectarse a la base de datos.
			bdUtils.conectar();
			System.out.println("Conexión a la DB: " + bdUtils.conectar());
			//2. Definir la sentencia sql (UPDATE).
			String sql = "UPDATE estudiante SET nombre = ?" + ", asignatura = ?" + ", nota_1 = ?" + ", nota_2 = ?" + ", nota_3 = ?" + ", nota_4 = ?" + " WHERE id = ?";
			PreparedStatement st = bdUtils.getConexion().prepareStatement(sql); //Aca se trae la conexión y se prepara el statement.
			st.setString(1, e.getNombre());
			st.setString(2, e.getAsignatura());
			st.setDouble(3, e.getNota1());
			st.setDouble(4, e.getNota2());
			st.setDouble(5, e.getNota3());
			st.setDouble(6, e.getNota4());
			st.setInt(7, e.getId());
			//3. Ejecutar el SQL.
			st.executeUpdate();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			
		} catch (Exception ex) {
			//4. Desconectarse.
		} finally { //Esta instrucción se ejecuta, caiga o no el programa.
			bdUtils.desconectar(); //Envia la petición de desconexión al dbms.
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
		}
		
	}
	
	//Método que Elimina objetos de la tabla automoviles que se encuentra en la base de datos.
	public void delete(Estudiante e) {
		//Todos los métodos que se conecten a una base de datos deben seguir los 4 siguientes pasos:
		boolean resultado;
		try {
			//1. Conectarse a la base de datos.
			this.bdUtils.conectar();
			System.out.println("Conexión a la DB: " + bdUtils.conectar());
			//2. Definir la sentencia sql (INSERT).
			String sql = "DELETE FROM estudiante" + " WHERE id = ?";
			PreparedStatement st = bdUtils.getConexion().prepareStatement(sql); //Aca se trae la conexión y se prepara el statement.
			st.setInt(1, e.getId());
			//3. Ejecutar el SQL.
			st.executeUpdate();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			//4. Desconectarse.
		} finally { //Esta instrucción se ejecuta, caiga o no el programa.
			bdUtils.desconectar(); //Envia la petición de desconexión al dbms.
		}
	}
}
