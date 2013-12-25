<%-- 
    Document   : home
    Created on : Dec 19, 2013, 5:24:35 PM
    Author     : furkan
--%>

<%@page import="Tables.User"%>
<%@page import="Dao.FollowDAO"%>
<%@page import="Dao.UserDAO"%>
<%@page import="Tables.Tweet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Dao.TweetDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/main.css"/>

    </head>
    <body>
        <div id="main">

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

                    <div id="usertweet">
                        <div id="tweets">
                            <%int firstid = Integer.parseInt(session.getAttribute("iduser").toString());
                                TweetDAO ttdao = new TweetDAO();
                                UserDAO dao = new UserDAO();
                                FollowDAO fdao = new FollowDAO();
                                List<Tweet> timeline = new ArrayList();
                                timeline = ttdao.getFollowingUserTweet(firstid);
                                for (int i = 0; i < timeline.size(); i++) {%>

                            <table border="1">                                
                                <tbody>
                                <form action="Show?op=showuser" method="POST">

                                    <tr>
                                        <td id="smallpicture" style="width: 10%;"></td>
                                        <td style="width: 20%"><%out.print(dao.getUsername(timeline.get(i).getIduserfk()));%></td>
                                        <td style="width: 50%"><%out.print(timeline.get(i).getContent());%></td>
                                        <td style="width: 20%"><%out.print(timeline.get(i).getDate());%></td>
                                        <td >
                                            <form action="replytweet.jsp?tweet=<%=timeline.get(i).getIdtweet()%>" method="POST">
                                                <input type="submit" value="Reply" />
                                            </form>
                                        </td>
                                        <td>
                                            <form action="ReTweet?tweet=<%=timeline.get(i).getIdtweet()%>" method="POST">
                                                <input type="submit" value="Retweet" />
                                            </form>
                                        </td>
                                    </tr>

                                </form>
                                <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>

        </div>
    </body>
</html>
