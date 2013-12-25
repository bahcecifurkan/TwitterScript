/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Class.SQL;
import Dao.UserDAO;
import Tables.User;
import javax.servlet.http.HttpSession;

/**
 *
 * @author furkan
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paramusername = request.getParameter("username");
        String parampassword = request.getParameter("password");
        String paramid = request.getParameter("iduser");
        HttpSession session = request.getSession(true);
        SQL connect = new SQL();
        ResultSet result;
        String sorgu = "Select user.iduser, user.username, user.password, usertype.name FROM user"
                + " INNER JOIN usertype ON user.idusertypefk=usertype.idusertype";
        try {
            Statement st = connect.baglantiAc();
            result = st.executeQuery(sorgu);
            while (result.next()) {
                String username = result.getString("user.username");
                String password = result.getString("user.password");
                String typename = result.getString("usertype.name");
                int id = result.getInt("user.iduser");

                if (username.equals(paramusername) && password.equals(parampassword) && typename.equals("Admin")) {
//                    request.setAttribute("iduser", id);
//                    request.setAttribute("username", username);
//                    request.getRequestDispatcher("admin/adminlogin.jsp").forward(request, response);
                } else if (username.equals(paramusername) && password.equals(parampassword)) {
                    session.setAttribute("iduser", id);
                    session.setAttribute("username", username);
                    request.getRequestDispatcher("userindex.jsp?userid="+id).forward(request, response);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
