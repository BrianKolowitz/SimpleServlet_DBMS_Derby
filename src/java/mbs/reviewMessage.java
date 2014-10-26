/*
 * reviewMessage.java
 *
 * Created on February 28, 2007, 12:58 PM
 */

package mbs;

import java.io.*;
import java.net.*;
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
public class reviewMessage extends HttpServlet {
    
    private Connection conn;
    private Statement st;
  
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int bytesread;
        char[] cbuf = new char[128];
        ResultSet rs=null;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        try{
             Class.forName("org.apache.derby.jdbc.ClientDriver");
            String connectionURL = "jdbc:derby://localhost:1527/ss_message";
            conn = DriverManager.getConnection(connectionURL, "TEST", "TEST");
            st = conn.createStatement();
            String q1 = new String("SELECT * FROM ss_message WHERE messageid = "+
                    request.getParameter("file"));
            rs =  st.executeQuery(q1);
        }       
        catch (SQLException se)
        {
            se.printStackTrace();  
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(reviewMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* TODO output your page here */
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet reviewMessage</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Review Message Page</h1>");
       out.println("<h3><a href =\"listMessages\">Click here to got back to the message list</a></h3>");
        out.println("<form method =\"post\" action = \"acceptMessage\">");
        out.println("<input type=\"hidden\" name=\"file\" value=\""+request.getParameter("file")+"\" />");
        out.println("<table border=\"1\"> <tbody>");
    try{
       if( rs.next()){
            out.println("<tr><td>Name:</td>");
            out.println("<td><input type=\"text\" name=\"sender\" value=\""+rs.getString("author") +"\" size=\"50\" /></td>");
            out.println("</tr><tr>");
            out.println("<tr><td>Title:</td>");
            out.println("<td><input type=\"text\" name=\"title\" value=\""+rs.getString("title") +"\" size=\"50\" /></td>");
            out.println("</tr><tr>");
            out.println("<td> Message:</td>");
            out.println("<td><textarea name=\"message\" rows=\"4\" cols=\"50\">");
            out.println(rs.getString("message"));}
       else
       {
            out.println("</tr><td>No Message Found</td><tr>");   
       }
    /*
    File SD= new File("c:\\spring\\TmpMsgDat\\"+request.getParameter("file"));
    FileReader fr = new FileReader(SD);
    while ((bytesread=fr.read(cbuf, 0, 128))!=-1)
    {String ps = new String(cbuf, 0, bytesread);
        out.println(ps);}
     */
    
    }
    catch(SQLException sqle)
                 {
            sqle.printStackTrace();  
        }

    out.println("</textarea></td>");
    out.println("</tr> <tr> <td></td>");
    out.println("<td><input type=\"submit\" value=\"Save Edited Message\" name=\"Submit\" /></td>");
    out.println(" </tr> </tbody></table> </form>");
        out.println("</body>");
        out.println("</html>");
    
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
