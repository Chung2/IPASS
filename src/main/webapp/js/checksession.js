//$(document).on("pagecontainerload",function() {
  var token = sessionStorage.getItem("sessionToken");
  console.log(token);
  if (sessionStorage.getItem("sessionToken") === null) {
    window.location.href = "/gameHistory/index.html";
  }
//});
