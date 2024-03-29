/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lehuuhieu
 */
public class MainController extends HttpServlet {

    private static final String REGISTER = "RegisterController";
    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String POST_ARTICLE = "PostArticleController";
    private static final String SEARCH = "SearchController";
    private static final String POST_COMMENT = "PostCommnetController";
    private static final String SEARCH_ARTICLE = "SearchArticleController";
    private static final String VERIFY_EMAIL = "VerifyEmailController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;
        String action = request.getParameter("action");
        log(action);

        try {
            if (action.equals("Register")) {
                url = REGISTER;
            } else if (action.equals("Login")) {
                url = LOGIN;
            } else if (action.equals("Post")) {
                url = POST_ARTICLE;
            } else if (action.equals("Search")) {
                url = SEARCH;
            } else if (action.equals("Comment")) {
                url = POST_COMMENT;
            } else if (action.equals("Search Article")) {
                url = SEARCH_ARTICLE;
            } else if (action.equals("Verify")) {
                url = VERIFY_EMAIL;
            }
        } catch (Exception e) {
            log("ERROR at MainController: " + e.getMessage());
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
