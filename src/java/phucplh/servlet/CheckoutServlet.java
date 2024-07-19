/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucplh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phucplh.T_Product.T_ProductDTO;
import phucplh.cart.CartObject;
import phucplh.tblOder.tblOderDAO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

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
        float tatol = Float.parseFloat(request.getParameter("txtTotalPrice"));
        int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
        String[] idItem = request.getParameterValues("txtId");
        String url = "invalid.html";
        
        try {
           //1. Customer goes to cart place
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. Customer takes cart
                CartObject cart = (CartObject)session.getAttribute("CART");
                if (cart != null) {
                    tblOderDAO dao = new tblOderDAO();
                    
                    String lastID = dao.lastIdOder();
                    String id = dao.autoIDBill(lastID);                   
                    boolean insert = dao.insertIntoOder(id, tatol);
                    if (insert) {
                        url = "onlineShopping.html";
                        session.removeAttribute("CART");
                    }
                }//end if cart has existed
            }//end session has existed
            
                            
        } catch(SQLException ex){
            ex.printStackTrace();
        } catch (NamingException ex){
            ex.printStackTrace();
        } finally{
            response.sendRedirect(url);
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
