<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<bean:define id="title"><bean:message key="title.select" /></bean:define><!-- 登録選択 -->

<html:html>
<head>
<jsp:include page="../common/header.jsp">
  <jsp:param name="title" value="<%=title%>"></jsp:param>
</jsp:include>
<link rel="stylesheet" type="text/css" href="css/header2.css">
<link rel="stylesheet" type="text/css" href="css/footer.css">
</head>

<body>
  <div class="container">
    <jsp:include page="../common/header2.jsp" />
    <h3 class="page-header"><bean:message key="title.select" /></h3><!-- 登録選択 -->

    <html:form action="/SelectNew" method="post">
      <html:messages id="msg" message="true"><div class="alert alert-info"><bean:write name="msg" /></div></html:messages>
      <logic:messagesPresent><div class="alert alert-danger"><html:errors /></div></logic:messagesPresent>

      <div class="text-center">
        <html:link href="javascript:setAction('schedule');" styleClass="btn btn-success btn-lg">
          <span class="glyphicon glyphicon-time"></span>&nbsp;<bean:message key="button.schedule" />
        </html:link><!-- スケジュール -->
        &nbsp;&nbsp;
        <html:link href="javascript:setAction('report');" styleClass="btn btn-primary btn-lg">
          <span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;<bean:message key="button.report" />&nbsp;&nbsp;
        </html:link><!-- 営業日報 -->
      </div>

      <br/>
      <br/>
      <div class="text-center">
        <html:button property="cancel" onclick="setAction('cancel');" styleClass="btn btn-default"><bean:message key="button.back" /></html:button><!-- 戻る -->
      </div>

      <html:hidden property="button" />
    </html:form>

    <jsp:include page="../common/footer.jsp" />
  </div>
</body>
</html:html>