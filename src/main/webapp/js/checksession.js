//$(document).on("pagecontainerload",function() {
  var token = sessionStorage.getItem("sessionToken");
  if (sessionStorage.getItem("sessionToken") === null) {
    window.location.href = "/gameHistory/index.html";
  }
//});
