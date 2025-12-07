<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<bean:define id="title"><bean:message key="title.user.edit" /></bean:define><!-- 社員編集 -->

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
    <h3 class="page-header"><bean:message key="title.user.edit" /></h3><!-- 社員編集 -->

    <html:form action="/UserInit" method="post" styleClass="form-horizontal">
      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="userEdit.code" /></label><!-- 社員コード -->
        <div class="col-sm-6">
         <bean:define id="haserr" value=""/>
         <logic:messagesPresent property="userId"><%haserr="has-error";%></logic:messagesPresent>
          <div class="input-group <%=haserr%>">
            <html:text property="userId" maxlength="20" styleClass="form-control" />
          <span class="input-group-addon"><bean:message key="label.required" /></span>
          </div>
          <p class="text-danger"><html:errors property="userId"/></p>
        </div>
        <div class="col-sm-3"></div>
      </div>

      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="userEdit.name" /></label><!-- 社員名 -->
        <div class="col-sm-6">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="userName"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
              <html:text property="userName" styleClass="form-control" />
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="userName"/></p>
        </div>
        <div class="col-sm-3"></div>
      </div>

      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="userEdit.passwd" /></label><!-- パスワード -->
        <div class="col-sm-6">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="passwd"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
             <html:password property="passwd" maxlength="20" styleClass="form-control" />
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="passwd"/></p>
        </div>
        <div class="col-sm-3"></div>
      </div>

      <html:messages id="msg" message="true"><div class="alert alert-info"><bean:write name="msg" /></div></html:messages>
      <logic:messagesPresent property="value"><div class="alert alert-danger"><html:errors property="value" /></div></logic:messagesPresent>

      <div class="form-group">
        <div class="col-sm-offset-3 col-sm-9">
          <html:button property="regist" onclick="setAction('regist');" styleClass="btn btn-primary"><bean:message key="button.regist" /></html:button><!-- 登録 -->
        </div>
      </div>

      <html:hidden property="button" />
    </html:form>

    <jsp:include page="../common/footer.jsp" />
  </div>
</body>
</html:html>
