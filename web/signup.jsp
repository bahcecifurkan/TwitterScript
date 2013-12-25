<%-- 
    Document   : signup
    Created on : Dec 20, 2013, 3:38:28 PM
    Author     : furkan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tbody>
            <form action="SignUp" method="POST">
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" value="" /></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" value="" /></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" name="email" value="" /></td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name" value="" /></td>
                </tr>
                <tr>
                    <td>Surname:</td>
                    <td><input type="text" name="surname" value="" /></td>
                </tr>
                <tr>
                    <td>Date:</td>
                    <td><input type="text" name="date" value="" />yyyy-mm-dd</td>
                </tr>
                <tr>
                    <td>Bio:</td>
                    <td><input type="text" name="bio" value="" /></td>
                </tr>
                <tr>
                    <td>Gender:</td>
                    <td><input type="text" name="gender" value="" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Submit" /></td>
                </tr>
            </form>
            </tbody>
        </table>

    </body>
</html>
