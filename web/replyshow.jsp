<%-- 
    Document   : replyshow
    Created on : Dec 20, 2013, 1:58:50 PM
    Author     : furkan
--%>

<%@page import="Tables.User"%>
<%@page import="Dao.FollowDAO"%>
<%@page import="Dao.UserDAO"%>
<%@page import="Dao.TweetDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Tables.Tweet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/main.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div id="main">
            <%

                int paramid = Integer.parseInt(session.getAttribute("iduser").toString());
                UserDAO dao = new UserDAO();
                User gg = new User();
                gg = dao.getUser(paramid);

            %>
            <div id="cssmenu">
                <ul>
                    <li class="active"><a href="Show?op=home"><span>Home</span></a></li>
                    <li><a href="Show?op=myprofile"><span>My Profile</span></a></li>
                    <li><a href="Show?op=logout"><span>Logout</span></a></li>
                </ul>
            </div>
            <div id="content">
                <div class="blok" id="leftblok">
                    <div id="cssleftmenu">
                        <ul>
                            <li><span><a href="">Following</a></span></li>
                            <li><span>Followers</span></li>
                        </ul>
                    </div>
                </div>

                <div class="blok" id="rightblok">

                    <div id="usercontent" >

                        <div id="picture"></div>
                        <div id="namesurname">
                            <%out.print(gg.getName());%> 
                            <%out.print(gg.getSurname());%>
                        </div>
                        <div id="username"><%out.print(gg.getUsername());%></div>
                        <div id="about">About:<%out.print(gg.getBio());%></div>

                    </div>
                    <div id="usertweet">
                        <div id="tweets">
                            <%
                                int firsttweetid = Integer.parseInt(request.getParameter("tweetid"));
                                List<Tweet> tweetlist = new ArrayList();
                                TweetDAO tdao = new TweetDAO();
                                UserDAO udao = new UserDAO();

                                tweetlist = tdao.showReply(firsttweetid);

                                for (int i = 0; i < tweetlist.size(); i++) {%>

                            <table border="1">                                
                                <tbody>
                                    <tr>
                                        <td id="smallpicture" style="width: 10%;"></td>
                                        <td style="width: 20%;"><%out.print(udao.getUsername(tweetlist.get(i).getIduserfk()));%></td>
                                        <td style="width: 50%;"><%out.print(tweetlist.get(i).getContent());%></td>
                                        <td> <%out.print(tweetlist.get(i).getDate()); %></td>
                                    </tr>

                                </tbody>
                            </table>
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
