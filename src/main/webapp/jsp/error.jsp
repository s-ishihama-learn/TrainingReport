<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<bean:define id="title"><bean:message key="title.systemError" /></bean:define>

<html:html>
<head>
<jsp:include page="common/header.jsp">
  <jsp:param name="title" value="<%=title%>"></jsp:param>
</jsp:include>
<link rel="stylesheet" type="text/css" href="css/header2.css">
<link rel="stylesheet" type="text/css" href="css/footer.css">
</head>

<body>
  <div class="container">
    <jsp:include page="common/headerError.jsp" />

    <html:messages id="msg" message="false">
      <div class="alert alert-danger"><bean:write name="msg" ignore="true"/></div>
    </html:messages>

    <html:form action="/Error" method="post" styleClass="form-inline text-center">
      <html:button property="login" onclick="setAction('login');" styleClass="btn btn-default"><bean:message key="button.gologin" /></html:button>
      <html:hidden property="button" />
    </html:form>

    <jsp:include page="common/footer.jsp" />
  </div>
</body>
</html:html>