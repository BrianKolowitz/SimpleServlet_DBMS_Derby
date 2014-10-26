<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Submit Message</title>
    </head>
    <body>

    <h1>Submit Message Page</h1>
    
    <form method ="post" action = "submitMessage1">
        <table border="1">
            <tbody>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="sender" value="" size="50" /></td>
                </tr>
                 <tr>
                    <td>Title:</td>
                    <td><input type="text" name="title" value="" size="50" /></td>
                </tr>
                <tr>
                    <td> Message:</td>
                    <td><textarea name="message" rows="8" cols="50">
    </textarea></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Submit" name="Submit" /></td>
                </tr>
            </tbody>
        </table>
   </form>  
    <BR/><BR/>
    <a href ="listMessages">Click here for list of Messages</a>
    </body>
</html>
