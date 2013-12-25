<%-- 
    Document   : userindex
    Created on : Dec 18, 2013, 7:21:45 PM
    Author     : furkan
--%>

<%@page import="Dao.FollowDAO"%>
<%@page import="Tables.Tweet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Dao.TweetDAO"%>
<%@page import="Dao.UserDAO"%>
<%@page import="Tables.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/main.css" />
    </head>
    <body>
        <div id="main">
            <%
                if (request.getParameter("userid") != null) {
                    int paramid = Integer.parseInt(request.getParameter("userid").toString());
                    UserDAO dao = new UserDAO();
                    User gg = new User();
                    gg = dao.getUser(paramid);
                    int firstid = Integer.parseInt(session.getAttribute("iduser").toString());
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
                        <div id="sendtweet">
                            <%
                                FollowDAO fdao = new FollowDAO();
                                if (firstid == paramid) {
                            %>

                            <form action="SendTweet" method="POST">
                                <textarea name="content" rows="2" cols="20"></textarea>
                                <br/><input type="submit" value="Tweet" />
                            </form>

                            <%
                            } else if (fdao.getFollow(paramid, firstid) == 0) {%>
                            <form action="FollowServlet?op=follow" method="POST">
                                <input type="submit" value="Follow" />
                            </form>
                            <%} else {%>
                            <form action="FollowServlet?op=unfollow" method="POST">
                                Following.. <input type="submit" value="Unfollow" />
                            </form>
                            <%}
                            %>
                        </div>

                        <div id="tweets">
                            <%
                                int tweets;

                                if (firstid == paramid) {
                                    tweets = firstid;
                                } else {
                                    tweets = paramid;
                                }

                                TweetDAO tdao = new TweetDAO();
                                List<Tweet> tweetlist = new ArrayList();
                                tweetlist = tdao.getAllUserTweet(tweets);

                                for (int i = 0; i < tweetlist.size(); i++) {%>

                            <table border="1">                                
                                <tbody>
                                    <tr>
                                        <td id="smallpicture" style="width: 10%;"></td>
                                        <td style="width: 50%;"><%out.print(tweetlist.get(i).getContent());%></td>
                                        <td style="width: 20%;">
                                            <a href="replyshow.jsp?tweetid=<%=tweetlist.get(i).getIdtweet()%>" >
                                                <%out.print(tweetlist.get(i).getDate());%>
                                            </a>
                                        </td>
                                <form action="DeleteTweet" method="POST">
                                    <td hidden="true">
                                        <input  type = "text" name = "idtweet" value = "<%out.print(tweetlist.get(i).getIdtweet());%>"/>
                                    </td>
                                    <%if (firstid == paramid) {%>
                                    <td style="width: 2%"><input type="submit" value="Delete" /></td>
                                </form>

                                <%}%>
                                </tr>

                                </tbody>
                            </table>
                            <%}%>
                        </div>

                    </div>
                </div>
            </div>
            <!-- main-->
        </div>




        <%
            }%>

</div>
</body>
</html>
