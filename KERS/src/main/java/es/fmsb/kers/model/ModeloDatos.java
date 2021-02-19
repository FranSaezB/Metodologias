/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.fmsb.kers.model;

import java.util.*;

import java.sql.*;

/**
 *
 * @author FranSaez
 */
public class ModeloDatos {

    private Connection con;
    private Statement set;
    private ResultSet rs;

    public void abrirConexion() {

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/CircuitosCochesdb", "root", "root");
            System.out.println("Se ha conectado");
        } catch (Exception e) {
            System.out.println("No se ha conectado");
        }
    }

    public boolean existeCircuito(String nombre) {
        boolean existe = false;
        String cad;
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM CIRCUITOS");
            while (rs.next()) {
                cad = rs.getString("NOMBRE");
                cad = cad.trim();
                if (cad.compareTo(nombre.trim()) == 0) {
                    existe = true;
                }
            }
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No lee de la tabla");
        }
        return (existe);
    }

    public boolean existeCoche(String nombre) {
        boolean existe = false;
        String cad;
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM COCHES");
            while (rs.next()) {
                cad = rs.getString("Nombre");
                cad = cad.trim();
                if (cad.compareTo(nombre.trim()) == 0) {
                    existe = true;
                }
            }
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No lee de la tabla");
        }
        return (existe);
    }

    public void insertarCircuito(String nombre, String ciudad, String pais, int vueltas, int longitud, int curvas) {
        
        int totalCurvas = vueltas*curvas;
        
        try {
            set = con.createStatement();
            set.executeUpdate("INSERT INTO CIRCUITOS "
                    + " (nombre,ciudad, pais, vueltas, longitud, curvas, totalcurvas) VALUES ('" + nombre + "','" + ciudad + "','" + pais + "'," + vueltas + "," + longitud + "," + curvas + "," + totalCurvas + ")");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No inserta en la tabla");
        }
    }

    public void insertarCoche(String nombre, int ganancia) {
        try {
            set = con.createStatement();
            set.executeUpdate("INSERT INTO COCHES "
                    + " (nombre,ganancia) VALUES ('" + nombre + "'," + ganancia + ")");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No inserta en la tabla");
        }
    }

    public int calculoKers(int totalCurvas, int ganancia) {

        return (totalCurvas * ganancia);

    }

    public void cerrarConexion() {
        try {
            con.close();
        } catch (Exception e) {
        }
    }

}
