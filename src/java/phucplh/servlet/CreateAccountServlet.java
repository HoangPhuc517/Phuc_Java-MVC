/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucplh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phucplh.registration.RegistrationDAO;
import phucplh.registration.RegistrationDTO;
import phucplh.registration.RegistrationInserError;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String ERROR_PAGE = "createAccount.jsp";

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

        //1. get all parameter
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String conform = request.getParameter("txtConfirm");
        String lastname = request.getParameter("txtLastname");
        
        RegistrationInserError errors = new RegistrationInserError();
        boolean foundEErr = false;
        String url = ERROR_PAGE;

        try {
            //2. check all user's error
            if(username.trim().length() < 6 || username.trim().length() > 20){
                foundEErr = true;
                errors.setUsernameLengthErr("Username is required from 6 to 20 chars");
            }
            if(password.trim().length() < 6 || password.trim().length() > 30){
                foundEErr = true;
                errors.setPasswordLengthErr("Password is required from 6 to 30 chars");
            } else if (!conform.trim().equals(password.trim())) {
                foundEErr = true;
                errors.setConfirmNotMatch("Confirm must match password");
            }
            if(lastname.trim().length() < 2 || lastname.trim().length() > 50){
                foundEErr = true;
                errors.setFullNameLengthErr("Full name is required from 2 to 50 chars");
            }
            
            if (foundEErr) {
                request.setAttribute("INSERT_ERROR", errors);
            } else {
                //3. nếu không có lỗi insert DB - call DAO
                RegistrationDTO account = new RegistrationDTO(username, password, lastname, false);
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.createAccount(account);
                
                if (result) {
                    //. transfer to Login page
                    url = LOGIN_PAGE;
                }//end account is created
            }
            //4. process result 
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateNewAccountServlet _ SQL " + msg);
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " extsted!!!");
                request.setAttribute("INSERT_ERROR", errors);
            }
        } catch (NamingException ex) {
            log("CreateNewAccountServlet _ Naming " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
