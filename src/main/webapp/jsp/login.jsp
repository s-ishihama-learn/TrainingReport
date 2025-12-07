<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ page import="java.lang.String"%>
<bean:define id="title"><bean:message key="title.login" /></bean:define><!-- ログイン -->

<html:html>
<head>
<jsp:include page="common/header.jsp">
  <jsp:param name="title" value="<%=title%>"></jsp:param>
</jsp:include>
<script type="text/javascript">
	$(function() {
		var placeholders = {
			"userid" : "ユーザーID",
			"passwd" : "パスワード"
		}
		for ( var id in placeholders) {
			$("#" + id).attr("placeholder", placeholders[id]);
		}
	});
</script>
<script src="<%=request.getContextPath()%>/js/info.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/header1.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/footer.css">
</head>

<body onLoad="loadText()">
  <div class="container">
    <jsp:include page="common/header1.jsp" />

    <html:form action="/Login" method="post" styleClass="form-inline text-right">
      <div class="form-group">
        <label class="sr-only" for="userId"><bean:message key="login.userId" /></label><!-- ユーザーID -->
        <html:text property="userId" maxlength="20" styleClass="form-control" styleId="userid" />
      </div>
      <div class="form-group">
        <label class="sr-only" for="pass"><bean:message key="login.passwd" /></label><!-- パスワード -->
        <html:password property="passwd" maxlength="20" styleClass="form-control" styleId="passwd" />
      </div>
      <html:button property="login" onclick="setAction('login');" styleClass="btn btn-primary">
        <bean:message key="button.login" />
      </html:button><!-- ログイン -->

      <p/>
      <html:messages id="msg" message="true"><div class="alert alert-info"><bean:write name="msg" /></div></html:messages>
      <logic:messagesPresent><div class="alert alert-danger"><html:errors /></div></logic:messagesPresent>

      <html:hidden property="limitDate" />
      <html:hidden property="button" />
    </html:form>

    <h2><bean:message key="login.info" /></h2><!-- お知らせ -->
    <div id="infoList"></div>

    <jsp:include page="common/footer.jsp" />
  </div>
</body>
</html:html>