<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Calculo de Kers en Formula 1</title>
    </head>
    <body>
    <center><H1>Calculo de Kers en Formula 1</H1></center><hr>
    <form action="KersServlet" method="POST">

        <b>CALCULO DE KERS:</b>

        <p> Seleccionar Circuito: <select name="circuito">
                <%
                    try {

                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/CircuitosCochesdb", "root", "root");
                        Statement stmt = con.createStatement();
                        ResultSet rs;
                        rs = stmt.executeQuery("SELECT * FROM CIRCUITOS");

                        while (rs.next()) {
                            out.write("<option value=" + rs.getInt("TOTALCURVAS") + ">" + rs.getString("NOMBRE") + "</option>");

                        }
                        rs.close();
                        stmt.close();

                    } catch (Exception e) {
                        System.err.print("Error al rellernar la lista");
                    }
                %>
            </select></p>

        <p> Seleccionar Coche: <select name="coche">
                <%
                    try {

                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/CircuitosCochesdb", "root", "root");
                        Statement stmt = con.createStatement();
                        ResultSet rs;
                        rs = stmt.executeQuery("SELECT * FROM COCHES");

                        while (rs.next()) {
                            out.write("<option value=" + rs.getInt("GANANCIA") + ">" + rs.getString("NOMBRE") + "</option>");

                        }
                        rs.close();
                        stmt.close();

                    } catch (Exception e) {
                        System.err.print("Error al rellernar la lista");
                    }
                %>
            </select></p>
        <p align="left">
            <input type="submit" name="B1" value="Calcular"></p>

    </form>
</body>
</html>
