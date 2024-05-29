package Ejercicio2;

public class Dieta {
    int id;
    String empleado;
    String departamento;
    double cantidadEuros;
    String concepto;

    public Dieta (int id, String empleado, String departamento, double cantidadEuros, String concepto) {
        this.id = id;
        this.empleado = empleado;
        this.departamento = departamento;
        this.cantidadEuros = cantidadEuros;
        this.concepto = concepto;
    }

    @Override
    public String toString() {
        return "ID: " +id+ "\nEmpleado: "+empleado+ "\nDepartamento: "+departamento+ "\nCantidad en euros: "+cantidadEuros+ "\nConcepto: "+concepto;
    }
}
