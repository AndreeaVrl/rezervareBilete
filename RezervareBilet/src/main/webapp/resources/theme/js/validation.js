function lunaInvalida(value, mesaj){
	if(value>12 || value<1){
		alert(mesaj);
		return true;
	}
	return false;
}
function anInvalid(value, mesaj){
	if(value<2018){
		alert(mesaj);
		return true;
	}
	return false;
}
function isNumber(evt){
	var charCode = (evt.which) ? evt.witch : evt.keyCode;
	if((charCode<48 && charCode >57)||charCode==46){
		return false;
	}
	return true;
}

function isCharacter(evt){
	var charCode = (evt.which) ? evt.witch : evt.keyCode;
	if((charCode>64 || charCode < 91) || (charCode > 96 || charCode < 123) ||charCode==45 || charCode==32 || charCode==13 ){
		return false;
	}
	return true;
}

function isNumberOrCharacter(evt){
	evt = evt || window.event;
	var charCode = evt.which || evt.keyCode;
	var charStr = String.fromCharCode(charCode);
	if(/[a-z0-9 ]/i.test(charStr)){
		return true;
	}else{
		return false;
	}
}

function isDate(id, message){
	var dtStr = $.trim($("#"+id).val());
	var dtCh = "/";
	var minYear = 1900;
	var maxYear = 2100;
	var pos1 = dtStr.indexOf(dtCh);
	var pos2 = dtStr.indexOf(dtCh,pos1+1);
	var strDay = dtStr.substring(0,pos1);
	var strMonth = dtStr.substring(pos1+1,pos2);
	var strYear = dtStr.substring(pos2+1);
	strYr=strYear;
	if(strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1);
	if(strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1);
	for(var i=1;i<=3;i++){
		if(strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1);
	}
	var month = parseInt(strMonth);
	var day = parseInt(strDay);
	var year = parseInt(strYr);
	if(pos1==-1||pos2==-1){
		alert(message+"1");
		return false;
	}
	if(strMonth.length<1 || month < 1 || month > 12 ){
		alert(message+" mL" +strMonth.length+" month"+month);
		return false;
	}
	if(strDay.length<1 || day < 1 || day > 31 || (month==2 && day>29)){
		alert(message+"3");
		return false;
	}
	if(strYear.length != 4 || year==0 || year < minYear || year>maxYear ){
		alert(message);
		return false;
	}
	if(dtStr.indexOf(dtCh,pos2+1) != -1){
		alert(message);
		return false;
	}
	return true;
}
function compareDate(date1, date2, mesaj){
//	var d1 = new Date(date1);
//	var d2 = new Date(date2);
	
	d1 = date1.getTime();
	d2 = date2.getTime();
	if(d1>d2){
		alert(mesaj);
		return false;
	}
	else{
		return true;
	}
}

function isEmpty(id, mesaj){
	var valFromId = $.trim($('#'+id).val());
	if(valFromId === ''){
		alert(mesaj);
		$('#'+id).focus();
		return true;
	}
	return false;
}
function areCorectPagesSet(page1,page2,mesaj){
	var pagInceput = $.trim($('#'+page1).val());
    var pagSfarsit = $.trim($('#'+page2).val());
	if(pagInceput<pagSfarsit){
		return true;
	}else{
		$('#'+page1).focus();
		alert(mesaj);
		return false;
	}
}
function completAuthors(autor, pozitie, mesaj){
	var nrAutori = autor.length;
	var nrPoz = pozitie.length;
	for(var j=0;j<nrAutori;j++){
		if(autor[j].value == ''){
			alert(mesaj);
			autor[j].focus();
			j=nrAutori;
			return true;
		}
	}
	for(var j=0;j<nrPoz;j++){
		if(pozitie[j].value == ''){
			alert(mesaj);
			pozitie[j].focus();
			j=nrPoz;
			return true;
		}
	}
	return false;
}	
	function completeName(autor, mesaj){
		var date = autor.length;
		for(var j=1;j<date;j++){
			if(autor[j].value == ''){
				alert(mesaj);
				autor[j].focus();
				j=autor;
				return true;
			}
		}
		return false;
	}

	function complete(autor, mesaj){
		var date = autor.length;
		for(var j=1;j<date;j++){
			if(autor[j].value == ''){
				alert(mesaj);
				autor[j].focus();
				j=autor;
				return true;
			}
		}
		return false;
	}
	function completeInst(inst, mesaj){
		var institutie = inst.length;
		for(var j=0;j<institutie;j++){
			if(inst[j].value == ''){
				alert(mesaj);
				inst[j].focus();
				j=institutie;
				return true;
			}
		}
		return false;
	}
	function completDatePassengers(institutie, localitate,tara, mesaj){
		var nrInst = institutie.length;
		var nrLoc = localitate.length;
		var nrTari = tara.length;
		for(var j=0;j<nrInst;j++){
			if(institutie[j].value == ''){
				alert(mesaj);
				institutie[j].focus();
				j=nrInst;
				return true;
			}
			if(localitate[j].value == ''){
				alert(mesaj);
				localitate[j].focus();
				j=nrLoc;
				return true;
			}
			if(tara[j].value == ''){
				alert(mesaj);
				tara[j].focus();
				j=nrTari;
				return true;
			}
		}
		return false;
	}