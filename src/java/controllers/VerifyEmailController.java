/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tblUser.UserDAO;

/**
 *
 * @author lehuuhieu
 */
@WebServlet(name = "VerifyEmailController", urlPatterns = {"/VerifyEmailController"})
public class VerifyEmailController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "login.jsp";
    private static final String INVALID = "verify.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;

        try {
            String code = request.getParameter("txtCode");
            String email = request.getParameter("email");

            boolean valid = true;

            if (code == null) {
                url = INVALID;
                request.setAttribute("INVALID", "Code can't be blank");
                valid = false;
                request.setAttribute("EMAIL", email);
            }

            if (valid) {
                UserDAO dao = new UserDAO();
                boolean check = dao.checkCode(email, code);
                if (check) {
                    check = dao.updateStatus(email, "New");
                    if (check) {
                        url = SUCCESS;
                    }
                } else {
                    url = INVALID;
                    request.setAttribute("INVALID_VERIFY", "Code is invalid");
                    request.setAttribute("EMAIL", email);
                }
            }
        } catch (Exception e) {
            log("ERROR at VerifyEmailController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
        processRequest(request, response);
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
