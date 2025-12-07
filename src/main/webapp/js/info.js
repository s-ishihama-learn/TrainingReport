var xmlHttp;

// お知らせの取得
function loadText(){
  if (window.XMLHttpRequest){
    xmlHttp = new XMLHttpRequest();
  }else{
    if (window.ActiveXObject){
      xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }else{
      xmlHttp = null;
    }
  }
  var s = window.location.pathname;
  var ss = s.split("/");
  xmlHttp.onreadystatechange = checkStatus;
  xmlHttp.open("GET", "/" + ss[1] + "/InfoAction.do", true);
  xmlHttp.send(null);
}

// httpチェック
function checkStatus(){
  if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
    var node = document.getElementById("infoList");
    node.innerHTML = xmlHttp.responseText;
  }
}
