$("#rondeStartenButton").click(function() {
  window.location.href = "/gameHistory/createround.html";
})

$("#geschiedenisButton").click(function() {
  window.location.href = "/gameHistory/history.html";
})

$("#rondeNaamButton").click(function() {
  var bestaat = false;
  var rondeNaam = $("#nieuwRondeNaam").val();
  var bestaandeNamen = [];

  if ($("#nieuwRondeNaam").val() === "") {
    alert("Voer een naam voor de ronde in!");
  } else {
    $.getJSON("./rest/rondes/", function(rondeData) {
      $.each(rondeData, function(v, n) {
        bestaandeNamen.push(n.naam);
      })
      for (var i = 0; i < bestaandeNamen.length; i++) {
        if (bestaandeNamen[i] === rondeNaam) {
          alert("Hij bestaat al	");
          bestaat = true;
          break;
        }
      }
      if (!bestaat) {
        window.location.href = "/gameHistory/selectgame.html";
      }
    });

  }
});

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

  if (!speler1 || !speler2 || !speler3 || speler1 == "Geen speler") {
    alert("geen geldige spelers!");
  } else {

    var bevestiging = confirm("Weet je zeker dat je de ronde wilt starten?");
    if (bevestiging == true) {
      window.location.href = "/gameHistory/round.html";
    }
  }
})

$("#rondeSluitenButton").click(function() {
  var bevestiging = confirm("weet je zeker dat je de ronde wilt afsluiten?");
  if (bevestiging == true) {
    window.location.href = "/gameHistory/postround.html";
  }
})

$("#rondeOpslaanButton").click(function() {
  if ($("#winnaar").val() === "Kies de winnaar!" || $("#winnaar").val() === "Arnoud") {
    alert("Geen geldige winnaar gekozen!");
  } else {
    alert("De ronde wordt nu opgeslagen!");
    setTimeout(function() {
      window.location.href = "/gameHistory/menu.html"
    }, 2000);
  }
})

$("#spellen").change(function() {
  $.getJSON("./rest/spellen/" + this.value, function(spellenData) {
    $("#instructies").html(spellenData.Instructies);
  })
})