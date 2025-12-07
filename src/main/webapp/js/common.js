function setAction(action){
    document.forms[0].button.value = action;
    document.forms[0].submit();
    return true;
}
function doDelete(action, msg){
	if(!window.confirm(msg)){
		return false;
	}
	document.forms[0].button.value = action;
	document.forms[0].submit();
}
function doCancel(action){
	var forms = document.forms[0];
	for (var i = 0; i < forms.length; i++) {
		var elem = forms.elements[i];
		if (elem.type == 'text' && !elem.readOnly){
			elem.value = '';
		}
		if (elem.type == 'password'){
			elem.value = '';
		}
	}
	document.forms[0].button.value = action;
	document.forms[0].submit();
}

function setMenuAdminAction(action){
	var s = window.location.pathname;
	var ss = s.split("/");
	document.forms[0].action = "/" + ss[1] + "/MenuAdmin.do";
	document.forms[0].button.value = action;
	document.forms[0].submit();
}
function setMenuApprovalAction(action){
	var s = window.location.pathname;
	var ss = s.split("/");
	document.forms[0].action = "/" + ss[1] + "/MenuApproval.do";
	document.forms[0].button.value = action;
	document.forms[0].submit();
}
function setMenuGeneralAction(action){
	var s = window.location.pathname;
	var ss = s.split("/");
	document.forms[0].action = "/" + ss[1] + "/MenuGeneral.do";
	document.forms[0].button.value = action;
	document.forms[0].submit();
}

function setMenuAction(action){
	var s = window.location.pathname;
	var ss = s.split("/");
	document.forms[0].action = "/" + ss[1] + "/Menu.do";
	document.forms[0].button.value = action;
	document.forms[0].submit();
}
