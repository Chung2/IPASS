$("#rondeStartenButton").click(function() {
  window.location.href = "/gameHistory/createround.html";
})

$("#geschiedenisButton").click(function() {
  window.location.href = "/gameHistory/history.html";
})

$("#rondeNaamButton").click(function() {
  if ($("#nieuwRondeNaam").val() === "") {
    alert("Voer een naam voor de ronde in!");
  } else {
    window.location.href = "/gameHistory/selectgame.html";
  }
})

$("#spelSelecterenButton").click(function() {
  if ($("#spellen").val() === "Kies een spel!") {
    alert("Ongeschikt spel!");
  } else {
    window.location.href = "/gameHistory/selectplayers.html";
  }
})

$("#selecterenSpelersButton").click(function() {
  var speler1 = $("#speler1").val();
  var speler2 = $("#speler2").val();
  var speler3 = $("#speler3").val();

  if (!speler1 || !speler2 || !speler3) {
    alert("De ronde mist een speler");
  } else {
    var bevestiging = confirm("Weet je zeker dat je de ronde wilt starten?");
    if (bevestiging == true) {
      window.location.href = "/gameHistory/round.html";
    }
  }
})

$("#rondeSluitenButton").click(function() {
  var bevestiging = confirm("weet je zeker dat je de ronde wilt afsluiten?");
  if(bevestiging == true){
    window.location.href = "/gameHistory/postround.html";
  }
})

$("#rondeOpslaanButton").click(function(){
  if($("#winnaar").val() === "Kies de winnaar!" || $("#winnaar").val() === "Arnoud"){
    alert("Geen geldige winnaar gekozen!");
  }
  else{
  alert("De ronde wordt nu opgeslagen!");
    setTimeout(function(){window.location.href = "/gameHistory/menu.html"},2000);
  }
})
