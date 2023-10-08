<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Arimo&display=swap" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Barlow&display=swap" rel="stylesheet" />
        <link href="css/projectView.css" rel="stylesheet" />
        <title>Document</title>
    </head>
    <body>
        <div class="Main">
            <div class="Footer"></div>
            <div class="Header"></div>
            <div class="NasaLogo"></div>
            <div class="Sidebar"></div>
            <div class="ProjectPhoto">
                <img class="ProjectPhotoImage" src="images/android-chrome-512x512.png" alt="">
            </div>
            <div class="ProjectName">
                <span class="ProjectNameText"><%= request.getAttribute("name") %></span>
            </div>
            <div class="Description">
                <span class="DescriptionText"><%= request.getAttribute("description") %></span>
            </div>
            <div class="Details">
                <span class="DetailsText"><%= request.getAttribute("detail") %></span>
            </div>
            <div class="Header_Bar">
                <div class="Logo"></div>
                <div class="Title">
                    <div class="Joint_Logo_Background">
                        <img class="Joint_Logo" src="images/android-chrome-512x512.png" alt="">
                    </div>
                    <span class="Joint_Title">Joint</span>
                </div>

                <% if (request.getAttribute("loggedIn") == null || !(boolean) request.getAttribute("loggedIn")) { %>
                <div class="Login">
                    <div class="LoginBackground"></div>
                    <a class="LoginInput" href="login.jsp">Login</a>
                </div>

                <div class="Signup">
                    <div class="SignupBackground"></div>
                    <a class="SignupInput" href="register.jsp">Signup</a>
                </div>
                <% } else { %>
                <form action="profile" method = "POST">
                    <div class="Profile">
                        <input type="image" class="ProfileInput" src="images/Profile Picture.png" alt="">
                    </div>
                </form>
                <% } %>
            </div>
        </div>
    </body>
</html>