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
public class CircuitosServlet extends HttpServlet {

    private ModeloDatos bd;

    public void init(ServletConfig cfg) throws ServletException {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession s = req.getSession(true);
        try {

            String nombre = (String) req.getParameter("textNombre");
            String ciudad = (String) req.getParameter("textCiudad");
            String pais = (String) req.getParameter("textPais");
            int vueltas = Integer.valueOf(req.getParameter("numeroVueltas"));
            int longitud = Integer.valueOf(req.getParameter("longitudVuelta"));
            int curvas = Integer.valueOf(req.getParameter("numeroCurvas"));
            if (bd.existeCircuito(nombre)) {
                res.sendRedirect(res.encodeRedirectURL("ErrorYaExistente.html"));
            } else {
                bd.insertarCircuito(nombre, ciudad, pais, vueltas, longitud, curvas);
                res.sendRedirect(res.encodeRedirectURL("CreacionCorrecta.html"));
            }

        } catch (Exception e){
            
            res.sendRedirect(res.encodeRedirectURL("ErrorCreacion.html"));
            
        }

    }

    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }

}
