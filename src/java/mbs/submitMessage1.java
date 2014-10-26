/*
 * Hello.java
 *
 * Created on February 27, 2007, 12:39 PM
 */

package mbs;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.regex.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author spring
 * @version
 */
public class submitMessage1 extends HttpServlet {
    private Connection conn;
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
        Date start=null, connection = null, process =null, cclose =null;
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Boolean valid = false;
        String clean_author, clean_title, clean_message;
        Statement st;
        Matcher matcher;
         
        //clean the inputs using regular expressions
        // Compile regular expression
        Pattern pattern = Pattern.compile("[;:,#&'\".!?]");
        // Replace all occurrences of pattern in input
        matcher = pattern.matcher(request.getParameter ("sender"));
        clean_author = matcher.replaceAll("|");
        matcher = pattern.matcher(request.getParameter ("title"));
        clean_title = matcher.replaceAll("|");
        matcher = pattern.matcher(request.getParameter ("message"));
        clean_message = matcher.replaceAll("|");
        //open the DBMS and insert the record
        try{
            start = new Date();            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String connectionURL = "jdbc:derby://localhost:1527/ss_message";
            conn = DriverManager.getConnection(connectionURL, "TEST", "TEST");
            //conn=DriverManager.getConnection(database, "","");
            connection = new Date();
            st = conn.createStatement();
            String q1 = new String("INSERT INTO ss_message (author, title, ptime, message)"+
                " VALUES ('" +
                clean_author+"','"+
                clean_title+"', '" +
                df.format(start) + "', '" +
                clean_message + "')");
                System.out.println(q1);
                st.execute(q1);
                process = new Date();
                conn.close();
                cclose  = new Date();
                valid=true;
        }        
        catch (SQLException se)
        {
            se.printStackTrace();  
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(submitMessage1.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        if (valid)
        {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Message Confirmation</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hello " + clean_author+ "</h1>");
        out.println("<p>We stored the following:</p><ol>");
        out.println("<p>"+clean_title+"</p>");
        out.println("<p>"+clean_message+"</p>");
        out.println("</ol>");
        out.println("<h3>Processing time for connection was: "+ (connection.getTime()-start.getTime())+" milliseconds</h3>");
        out.println("<h3>Processing time for insert was: "+ (process.getTime()-connection.getTime())+" milliseconds</h3>");
        out.println("<h3>Processing time for close was: "+ (cclose.getTime()-process.getTime())+" milliseconds</h3>");
        out.println("<h3><a href =\"listMessages\">Click here for list</a></h3>");        
        out.println("</body>");
        out.println("</html>");
        }
        else
        {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Message Storage Problem</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hello " + request.getParameter ("sender") + "</h1>");
        out.println("<p>We were unable to store the message:</p><ol><p>");
        out.println(request.getParameter("message"));
        out.println("</p></ol>");
        out.println("<a href =\"listMessages\">Click here for list</a>");        
        out.println("</body>");
        out.println("</html>");   
        }
        out.close();
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
