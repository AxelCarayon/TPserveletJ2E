/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import invoice.DAOstateForm;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.CustomerEntity;
import simplejdbc.DAO;
import simplejdbc.DataSourceFactory;

/**
 *
 * @author Axel
 */
@WebServlet(name = "ShowClientInStateWithForm", urlPatterns = {"/ShowClientInStateWithForm"})
public class ShowClientInStateWithForm extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowClientInStateWithForm</title>");            
            out.println("</head>");
            out.println("<body>");
            try {   // Trouver la valeur du paramètre HTTP state
                String val = request.getParameter("state");
                // on doit convertir cette valeur en entier (attention aux exceptions !)
                String state1 = val;
 
                DAOstateForm dao = new DAOstateForm(DataSourceFactory.getDataSource());
                List<CustomerEntity> customer = dao.customersInState(state1);
                // Afficher les propriétés du client 
                out.println("<style>\n" +
                            "table {\n" +
                            "  font-family: arial, sans-serif;\n" +
                            "  border-collapse: collapse;\n" +
                            "  width: 100%;\n" +
                            "}\n" +
                            "\n" +
                            "td, th {\n" +
                            "  border: 1px solid #dddddd;\n" +
                            "  text-align: left;\n" +
                            "  padding: 8px;\n" +
                            "}\n" +
                            "\n" +
                            "tr:nth-child(even) {\n" +
                            "  background-color: #dddddd;\n" +
                            "}\n" +
                            "</style>");
                List<String> state = dao.StateList();
                out.println("<form action='ShowClientInStateWithForm'>");
                out.println("<select name='state'>");
                for (int i=0; i<state.size();i++){
                    out.println("<option value ='"+state.get(i)+"'>"+state.get(i)+"</option>");
                }
                out.println("</select>");
                out.println("<input type='submit'>");
                out.println("</form>");
                if (val != null) {
                    out.println("<table>");
                    out.println("<tr> <th>ID</th> <th>Name</th> <th>Address</th> </tr>");
                    for (int i=0; i<customer.size();i++){
                        out.println("<tr>");
                        out.println("<td>"+customer.get(i).getCustomerId()+"</td>");
                        out.println("<td>"+customer.get(i).getName()+"</td>");
                        out.println("<td>"+customer.get(i).getAddressLine1()+"</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                }
            } catch (Exception e) {
                out.printf("Erreur : %s", e.getMessage());
            }
            
            out.println("</body>");
            out.println("</html>");
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
