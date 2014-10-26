/*
 * reviewMessages.java
 *
 * Created on February 28, 2007, 12:11 PM
 */

package mbs;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author spring
 * @version
 */
public class listMessages extends HttpServlet {
    private Connection conn;
    private Statement st;
    private ResultSet rs=null;
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
         try{
              Class.forName("org.apache.derby.jdbc.ClientDriver");
            String connectionURL = "jdbc:derby://localhost:1527/ss_message";
            conn = DriverManager.getConnection(connectionURL, "TEST", "TEST");
            st = conn.createStatement();
            String q1 = new String("SELECT * FROM SS_MESSAGE");
            rs =  st.executeQuery(q1);
        }        
        catch (SQLException se)
        {
            se.printStackTrace();  
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(listMessages.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        /*File SD= new File("c:\\spring\\TmpMsgDat");
        if (!SD.exists()) SD.mkdir();
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
            return name.startsWith("GB");}
            };
            File[] files = SD.listFiles(filter); */
       
        /* TODO output your page here */
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet list Messages</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>The following Messages exist</h1>");
        out.println("<ol>");
       try{
           while(rs.next())
           {
             out.println("<li><a href=\"reviewMessage?file="+
                 rs.getString("messageid")+
                 "\">"+rs.getString("author")+"</a></li>");
           }
       }
       catch(SQLException sqle)
                 {
            sqle.printStackTrace();  
        }

       /*for (int i=0;i<files.length;i++)
        {out.println("<li><a href=\"reviewMessage?file="+
                 files[i].getName().toString()+
                 "\">"+files[i].getName().toString()+"</a></li>");
        }*/
        out.println("</ol>");
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
