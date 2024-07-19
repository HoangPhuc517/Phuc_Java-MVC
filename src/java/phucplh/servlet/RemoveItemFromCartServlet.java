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

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "RemoveItemFromCartServlet", urlPatterns = {"/RemoveItemFromCartServlet"})
public class RemoveItemFromCartServlet extends HttpServlet {

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
        String urlRewriting = null;
        try {
            //1. Customer goes to cart place
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. Customer takes cart
                CartObject cart = (CartObject)session.getAttribute("CART");
                if (cart != null) {
                    //3. Customer takes item
                    Map<T_ProductDTO, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Get all selected Item 
                        String[] removedItem = request.getParameterValues("chkItem");
                        if (removedItem != null) {
                            //5. remove each item from cart
                            //for (String item : removedItem){
                                cart.removeItemFromCart(removedItem);
                            //}//end traverse each item
                            
                        }//end removedItem had choiced
                        session.setAttribute("CART", cart);
                    }//end item have existed
                }//end if cart has existed
            }//end session has existed
            urlRewriting = "DispatchController"
                            + "?btAction=View Your Cart";
        } finally {
            //6. call view cart again
            
            response.sendRedirect(urlRewriting);
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
