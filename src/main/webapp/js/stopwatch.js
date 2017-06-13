function stopwatch(){
	var h2 = document.getELementsByTagName("h2")[0];
	var uren = 0;
	var minuten = 0;
	var secondes = 0;
	var t = null;

	var stoptijd = null;

	function add(){
		secondes++;
		if(secoundes >= 60){
			secondes = 0;
			minutes++;
			if(minutes >=60){
				minutes = 0;
				uren++;
			}
		}
	}
	h2.textContent = (uren ?(uren > 9 ? uren: "0" +uren) : "00") + " : " + (minuten ? (minuten > 9 ? minuten : "0" + minuten) : "00") + " : " + (secondes > 9 ? secondes : "0" + secondes );
	function time(){
		t = setTimeout(add,1000)
	}
	timer();
}

$("#startTimer").click(function(){
	timer();
	alert("hallo");	
});