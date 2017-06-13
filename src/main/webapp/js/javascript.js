
//functies onclick

//menu speler
$("#rondeStartenButton").click(function() {
  window.location.href = "/gameHistory/createround.html";
})

$("#geschiedenisButton").click(function() {
  window.location.href = "/gameHistory/history.html";
})

//menu admin
$("#verwijderenButton").click(function(){
  window.location.href = "/gameHistory/admin/deletegame.html"
})

$("#nieuwSpelButton").click(function(){
  window.location.href = "/gameHistory/admin/addgame.html"
})

//pagina createround
$("#rondeNaamButton").click(function() {
  var bestaat = false;
  var rondeNaam = $("#nieuwRondeNaam").val();
  var bestaandeNamen = [];

  if (rondeNaam === "") {
    alert("Voer een naam voor de ronde in!");
  } else {
    $.getJSON("./rest/rondes/", function(rondeData) {
      $.each(rondeData, function(v, n) {
        bestaandeNamen.push(n.naam);
      })
      for (var i = 0; i < bestaandeNamen.length; i++) {
        if (bestaandeNamen[i] === rondeNaam) {
          alert("De ronde"+ rondeNaam +" bestaat al	");
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

//pagina selectgame
$("#spelSelecterenButton").click(function() {
  var gekozenSpel = $("#spellen").val();
  if (gekozenSpel === "Kies een spel!") {
    alert("Geen geldige spel!");
  } else {
    window.location.href = "/gameHistory/selectplayers.html";
  }
})

//pagina selectplayers
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

//pagina round
$("#rondeSluitenButton").click(function() {
  var tijd = $("#stopWatchTijd h3").html();
  var uren = tijd.slice(0, 2);
  var minuten = tijd.slice(3, 5);
  var secondes = tijd.slice(6, 8);

  var bevestiging = confirm("weet je zeker dat je de ronde wilt afsluiten?");
  if (bevestiging == true) {
    window.location.href = "/gameHistory/postround.html";
  }
})

//pagina posround
$("#rondeOpslaanButton").click(function() {
  var winnaar = $("#winnaar").val();
  var notities = $("textarea#notities").val();

  if (winnaar === "Kies de winnaar!" || winnaar === "Arnoud") {
    alert("Geen geldige winnaar gekozen!");
  } else {
    alert("De ronde wordt nu opgeslagen!");
    setTimeout(function() {
      window.location.href = "/gameHistory/menu.html"
    }, 2000);
  }
})

//pagina addgame
$("#toevoegenGameButton").click(function(){

var nieuwSpel = $("#GameNaam").val();
var nieuwInstructies = $("#gameInstructies").val();
var bestaandeSpellen = [];
var bestaat = false;

if (nieuwSpel === "") {
  alert("Voer een naam voor het spel in!");
} else {
  $.getJSON("../rest/spellen/", function(spellenData) {
    $.each(spellenData, function(v, n) {
      bestaandeSpellen.push(n.naam);
    })
    for (var i = 0; i < bestaandeSpellen.length; i++) {
      if (bestaandeSpellen[i] === nieuwSpel) {
        alert("Het spel "+ nieuwSpel+" bestaat al	");
        bestaat = true;
        break;
      }
    }
    if (!bestaat) {
      var data = {
        "naam":nieuwSpel,
        "Instructies":nieuwInstructies
      };

      var JSONdata = JSON.stringify(data);
      var uri = "../rest/spellen/"
    	  console.log(uri);
     $.ajax(uri, {
       method: "POST",
       data: JSONdata,
      //  beforeSend: function(xhr){
      //    var token = window.sessionStorage.getItem("sessionToken");
      //    xhr.setRequestHeader('Authorization','Bearer' +token);
      //  },
       success: function(response) {
         alert("Het spel "+ nieuwSpel +" is toegevoegd!");
       },
       error: function(response) {
         alert("Het spel "+ nieuwSpel +" is niet toegevoegd!");
       }
     });
      //window.location.href = "/gameHistory/selectgame.html";
    }
  });
}
});

//pagina deletegame
function deleteButton(id){

}


//functies change

//pagina selectgame
$("#spellen").change(function() {
  $.getJSON("./rest/spellen/" + this.value, function(spellenData) {
    $("#instructies").html(spellenData.Instructies);
  })
})


//functies keyup

//pagina history
$("#searchBarWinnaars").keyup(function() {
  var input, filter, table, tr, td, i;
  input = document.getElementById("searchBarWinnaars");
  filter = input.value.toUpperCase();
  table = document.getElementById("resultaten");
  tr = table.getElementsByTagName("tr");

  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[4];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
})
