package cl.inacap.promedioswingappmodelo.dto;

public class Estudiante {

	private int id;
	private String nombre;
	private String asignatura;
	private double nota1;
	private double nota2;
	private double nota3;
	private double nota4;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPromedio() {
		return (nota1 + nota2 + nota3 + nota4) / 4;
	}
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getAsignatura() {
		return asignatura;
	}


	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}


	public double getNota1() {
		return nota1;
	}


	public void setNota1(double nota1) {
		this.nota1 = nota1;
	}


	public double getNota2() {
		return nota2;
	}


	public void setNota2(double nota2) {
		this.nota2 = nota2;
	}


	public double getNota3() {
		return nota3;
	}


	public void setNota3(double nota3) {
		this.nota3 = nota3;
	}


	public double getNota4() {
		return nota4;
	}


	public void setNota4(double nota4) {
		this.nota4 = nota4;
	}


	public String toString() {
		return "[Nombre]= " + this.nombre + " [Nota 1]= " + this.nota1 + " [Nota 2]= " + this.nota2 + " [Nota 3]= " + this.nota3 + " [Nota 4]= " + this.nota4 + " [Promedio]= " + (nota1 + nota2 + nota3 + nota4) / 4;
	}
	
	
	
}
