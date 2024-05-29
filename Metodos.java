package Ejercicio2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Metodos {
    Conexion conexion = new Conexion();
    Scanner scanner = new Scanner(System.in);
    Connection cn = null;
    Statement stm;
    ResultSet rs;

    public Metodos (Statement stm) {
        this.stm = stm;
    }
    public void crearTabla() {
        try {
            rs = stm.executeQuery("SHOW TABLES LIKE 'Dietas'");

            if (!rs.next()) {
                String tabla = "CREATE TABLE Dietas (id INT AUTO_INCREMENT, empleado VARCHAR(30), departamento VARCHAR(45), cantidadEuros DOUBLE, concepto VARCHAR(255), PRIMARY KEY (id));";
                stm.executeUpdate(tabla);
                System.out.println("La tabla se ha creado correctamente");
            } else {
                System.out.println("La tabla ya existe");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    int numDieta = 1;

    public void rellenarTabla(String empleado, String departamento, double cantidadEuros, String concepto) {
        try {
        rs = stm.executeQuery("SHOW TABLES LIKE 'Dietas'");

        if (rs.next()) {
            String insercion = "INSERT INTO Dietas (empleado, departamento, cantidadEuros, concepto) VALUES ('"+empleado+"', '"+departamento+"', "+cantidadEuros+", '"+concepto+"');";
            stm.executeUpdate(insercion);
            numDieta++;

            if (numDieta == 11) {
                System.out.println("Se han insertado los datos con exito");
            }

        } else {
            System.out.println("No existe la tabla Dietas");
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void consulta() {
        try {
            rs = stm.executeQuery("SELECT * FROM Dietas WHERE departamento = 'Informática' AND cantidadEuros > 30");
            ArrayList<Dieta> dietas = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String empleado = rs.getString("empleado");
                String departamento = rs.getString("departamento");
                double cantidadEuros = rs.getDouble("cantidadEuros");
                String concepto = rs.getString("concepto");

                Dieta dieta = new Dieta (id, empleado, departamento, cantidadEuros, concepto);
                dietas.add(dieta);
            }

            for (Dieta verDietas : dietas) {
                System.out.println(verDietas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void incremento () {
        try {
            rs = stm.executeQuery("SELECT * FROM Dietas WHERE departamento = 'Ventas'");

            if (rs.next()) {
                String incremento = "UPDATE Dietas SET cantidadEuros = cantidadEuros + (cantidadEuros * 0.1) WHERE departamento = 'Ventas'";
                stm.executeUpdate(incremento);
                System.out.println("Se ha incrementado en un 10% las dietas del departamento Ventas");
            } else {
                System.out.println("No hay dietas con el departamento de Ventas.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
