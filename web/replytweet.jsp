
<%@page import="Tables.User"%>
<%@page import="Dao.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                        <%
                            session.setAttribute("tweet", request.getParameter("tweet"));
                        %>
                        <form action="ReplyTweet" method="POST">
                            <textarea name="replytext" rows="4" cols="20"></textarea>
                            <input type="submit" value="Reply" />
                        </form>

                    </div>
                </div>
            </div>
            <div id="content">

            </div>            
        </div>
    </body>
</html>
