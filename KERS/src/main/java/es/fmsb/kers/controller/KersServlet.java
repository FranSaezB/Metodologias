package es.fmsb.kers.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import es.fmsb.kers.model.ModeloDatos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author FranSaez
 */
@WebServlet(urlPatterns = {"/KersServlet"})
public class KersServlet extends HttpServlet {

    private ModeloDatos bd;

    public void init(ServletConfig cfg) throws ServletException {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    public void service(HttpServletRequest req,
            HttpServletResponse res) throws ServletException, IOException {
        HttpSession s = req.getSession(true);

        int totalcurvas = Integer.parseInt(req.getParameter("circuito"));
        int ganancia = Integer.parseInt(req.getParameter("coche"));

        int result = bd.calculoKers(ganancia, totalcurvas);

        s.setAttribute("resultadoKers", result);
        
        res.sendRedirect(res.encodeRedirectURL("ResultadoKers.jsp"));
    }

    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }

}
