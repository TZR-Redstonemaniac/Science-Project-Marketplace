<%--suppress ALL --%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
  <head>
    <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Barlow&display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Arimo&display=swap" rel="stylesheet" />
    <link href="./css/index.css?v=1" rel="stylesheet"/>
    <title>Document</title>
  </head>
  <body>
    <div class="Main">
      <div class="Search">
        <div class="Search_Box"></div>
        <input type="text" class="Search_Input" placeholder="Search" id="search">
        <div class="Search_Icon"></div>
      </div>

      <div class="ProjectScrollBox">
        <% List<String> names = (List<String>) request.getAttribute("projectNames"); %>
        <% List<String> descriptions = (List<String>) request.getAttribute("projectDescriptions"); %>
        <% List<String> tags = (List<String>) request.getAttribute("projectTags"); %>
        <% List<String> details = (List<String>) request.getAttribute("projectDetails"); %>
        <% if (names != null && descriptions != null && tags != null && details != null) { %>
          <% for (int i = 0; i < names.size(); i++) { %>
            <div class="Project<%=i%>" id="<%= names.get(i) %>">
              <img src="images/favicon-16x16.png" class="Image" alt="">
              <a class="Name" id="<%= i %>">Name: <%= names.get(i) %></a>
              <span class="Description">Description: <%= descriptions.get(i) %></span>
              <span class="Tags">Tags: <%= tags.get(i) %></span>
            </div>

            <style>
              .Project<%=i%> {
                width: 678px;
                height: 258px;
                background: rgba(252,61,33,1);
                opacity: 1;
                position: absolute;
                top: <%= (290 * i) %>px;
                left: 0;
                overflow: hidden;
              }
            </style>
          <% } %>
        <% } %>
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
              <input type="image" class="ProfileInput" src="images/Profile Picture.png">
            </div>
          </form>
        <% } %>
      </div>

      <div class="Sidebar">
        <span class="Recommended_Tags_Text">RECOMMENDED TAGS:</span>
        <div class="Recommended_Tag_1"></div>
        <div class="Recommended_Tag_2"></div>
        <div class="Recommended_Tag_3"></div>
        <div class="Recommended_Tag_4"></div>

        <span class="Hot_Projects_Text">Hot Projects:</span>
        <div class="Hot_Project_1">
          <span class="Hot_Project_1_Text">PROJECT #1</span>
        </div>
        <div class="Hot_Project_2">
          <span class="Hot_Project_2_Text">PROJECT #2</span>
        </div>

        <span class="Preferred_Time_Range_Text">PREFERRED WORK TIME RANGE</span>
        <span class="Thirty-FortyFive_MinutesADay">30-45 MINS/DAY</span>
        <span class="One-Two_HoursADay">1-2 HOURS/DAY</span>
        <span class="Three-Four_HoursADay">3-4 HOURS/DAY</span>
        <span class="Five-Six_HoursADay">5-6 HOURS/DAY</span>

        <div class="Preferred_Time_Range_Check_Boxes"></div>
        <input type="hidden" name="hiddenId" id="hiddenId"/>
      </div>
    </div>
    <script>
      const search = document.getElementById("search");
      let projectDiv = null
      let jspVariable = null

      search.addEventListener("input", function () {
        let i = 0
        <% if (names != null) { %>
          <% for (String name : names) { %>
            projectDiv = document.getElementById("<%= name %>");
            jspVariable = "<%= name %>".toLowerCase();
            projectDiv.hidden = !jspVariable.includes(search.value);

            if (!projectDiv.hidden) {
              projectDiv.className = "Project" + i
              i++;
            } else {
              projectDiv.className = ""
            }
          <% } %>
        <% } %>
      });

      <% for (int i = 0; i < names.size(); i++) { %>
        document.getElementById("<%= i %>").addEventListener("click", function(event) {
          // Prevent the default behavior of the link (preventing the immediate redirection)
          event.preventDefault();

          document.getElementById("hiddenId").value = <%= i %>

          // After setting the attribute, you can redirect to the destination URL
          window.location.href = "viewProject?hiddenId=<%= i %>";
        });
      <% } %>
    </script>
  </body>
</html>