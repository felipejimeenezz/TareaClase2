package Ejercicio2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        Scanner scanner = new Scanner(System.in);
        Connection cn = null;
        boolean encendido = true;
        Statement stm = null;

        try {
            cn = conexion.conectar();
            stm = cn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Metodos metodos = new Metodos (stm);
        int opcion;
        int numDieta = 1;

        while (encendido) {
            System.out.println("Selecciona una opción: \n1. Crear una tabla Dietas\n2. Insertar 10 dietas\n3. Mostrar empleados de Informática con dietas mayores de 30 euros\n4. Incrementar todas las dietas del departamento de Ventas en un 10%\n5. Salir");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    metodos.crearTabla();
                    break;
                case 2:
                    while (numDieta < 11) {
                        System.out.println("Inserte el nombre del empleado de la dieta "+numDieta+":");
                        String empleado = scanner.next();

                        System.out.println("Inserte el nombre del departamento de la dieta "+numDieta+":");
                        String departamento = scanner.next();

                        System.out.println("Inserte la cantidad en euros de la dieta "+numDieta+":");
                        double cantidadEuros = scanner.nextDouble();

                        System.out.println("Inserte el concepto de la dieta "+numDieta+":");
                        String concepto = scanner.next();

                        metodos.rellenarTabla(empleado, departamento, cantidadEuros, concepto);
                        numDieta++;
                    }
                    break;
                case 3:
                    metodos.consulta();
                    break;
                case 4:
                    metodos.incremento();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    encendido = false;
                    break;
            }
        }
    }
}
