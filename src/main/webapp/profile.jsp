<!DOCTYPE html>
<html>
  <head>
    <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Barlow&display=swap" rel="stylesheet" />
    <link href="./css/profile.css" rel="stylesheet" />
    <title>Document</title>
  </head>
  <body>
    <div class="Main">
      <span class="ProfileSettingTitle">Account Profile</span>
      <span class="UploadProfilePicture">Upload Profile Picture</span>
      <div class="Header"></div>
      <div class="Footer"></div>
      <div class="NasaLogo"></div>
      <div class="Title">
        <span class="TitleText">Joint</span>
      </div>
      <form action="saveProfile" method="post">
        <div class="Email">
          <input type="text" class="EmailInput" value="<%= request.getAttribute("email") %>" name="email" >
        </div>
        <div class="Username">
          <input type="text" class="UsernameInput" value="<%= request.getAttribute("username") %>" name="username" >
        </div>
        <div class="CurrentPassword">
          <input type="password" class="CurrentPasswordInput" name="CurrentPasswordInput">
        </div>
        <div class="NewPassword">
          <input type="password" class="NewPasswordInput" name="NewPasswordInput">
        </div>
        <div class="DateOfBirth">
          <input type="text" class="DateOfBirthInput" value="<%= request.getAttribute("dateOfBirth") %>" name="dateOfBirth" >
        </div>
        <div class="Tags">
          <input type="text" class="TagsInput" value="<%= request.getAttribute("tags") %>" name="tags" >
        </div>
        <div class="SaveChanges">
          <div class="SaveChangesBackground"></div>
          <input type="submit" class="SaveChangesText">Save Changes
        </div>
      </form>
      <div class="ProfilePicture"></div>
    </div>
  </body>
</html>