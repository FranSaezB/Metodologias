/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.fmsb.kers.controller;

import es.fmsb.kers.model.ModeloDatos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author FranSaez
 */
public class CochesServlet extends HttpServlet {

    private ModeloDatos bd;

    public void init(ServletConfig cfg) throws ServletException {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession s = req.getSession(true);

        try {

            String nombre = (String) req.getParameter("textNombreC");
            int ganancia = Integer.valueOf(req.getParameter("numeroGanancia"));
            if (bd.existeCoche(nombre)) {
                res.sendRedirect(res.encodeRedirectURL("ErrorYaExistente.html"));
            } else {
                bd.insertarCoche(nombre, ganancia);
                res.sendRedirect(res.encodeRedirectURL("CreacionCorrecta.html"));
            }

        } catch (Exception e) {

            res.sendRedirect(res.encodeRedirectURL("ErrorCreacion.html"));

        }

    }

    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }

}
