<%-- 
    Document   : index
    Created on : 18.Ara.2013, 16:42:58
    Author     : furkan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Twitter</title>
    </head>
    <body>
        <div id="main">
            <div id="login">
                <form action="Login" method="POST">
                    <table>
                        <thead>
                            <tr>
                                <th>Login !</th>                            
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Username:</td>
                                <td><input type="text" name="username" value="" /></td>
                            </tr>
                            <tr>
                                <td>Password:</td>
                                <td><input type="password" name="password" value="" /></td>
                            </tr>
                            <tr>
                                <td><a href="signup.jsp">Sign Up</a></td>
                                <td><input type="submit" value="Submit" /></td>
                            </tr>
                        </tbody>
                    </table>
                </form>

            </div>        
        </div>
    </body>
</html>
