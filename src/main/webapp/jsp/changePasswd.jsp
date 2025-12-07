<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<bean:define id="title"><bean:message key="title.password" /></bean:define><!-- パスワード変更 -->

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
    <jsp:include page="common/header2.jsp" />
    <logic:equal property="userCom" name="CommonSession" value="9"><jsp:include page="navibar/naviAdmin.jsp" /></logic:equal>
    <logic:equal property="userCom" name="CommonSession" value="2"><jsp:include page="navibar/naviApproval.jsp" /></logic:equal>
    <logic:equal property="userCom" name="CommonSession" value="1"><jsp:include page="navibar/naviGeneral.jsp" /></logic:equal>
    <h3 class="page-header"><bean:message key="title.password" /></h3><!-- パスワード変更 -->

    <html:form action="/ChangePasswd" method="post" styleClass="form-horizontal">
      <html:messages id="msg" message="true"><div class="alert alert-info"><bean:write name="msg" /></div></html:messages>
      <logic:messagesPresent property="value"><div class="alert alert-danger"><html:errors property="value" /></div></logic:messagesPresent>

      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="changePasswd.oldPass" /></label><!-- 旧パスワード -->
        <div class="col-sm-3">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="oldPass"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
            <html:password property="oldPass" maxlength="20" styleClass="form-control" />
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="oldPass"/></p>
        </div>
        <div class="col-sm-6"></div>
      </div>

      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="changePasswd.newPass" /></label><!-- 新パスワード -->
        <div class="col-sm-3">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="newPass"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
            <html:password property="newPass" maxlength="20" styleClass="form-control" />
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="newPass"/></p>
        </div>
        <div class="col-sm-6"></div>
      </div>

      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="changePasswd.chkPass" /></label><!-- 確認パスワード -->
        <div class="col-sm-3">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="chkPass"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
            <html:password property="chkPass" maxlength="20" styleClass="form-control" />
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="chkPass"/></p>
        </div>
        <div class="col-sm-6"></div>
      </div>

      <div class="form-group">
        <div class="col-sm-offset-3 col-sm-9">
          <html:button property="change" onclick="setAction('change');" styleClass="btn btn-primary"><bean:message key="button.update" /></html:button><!-- 変更 -->
          <html:button property="cancel" onclick="doCancel('cancel');" styleClass="btn btn-default"><bean:message key="button.cancel" /></html:button><!-- キャンセル -->
        </div>
      </div>

      <html:hidden property="userId" />
      <html:hidden property="button" />
    </html:form>

    <jsp:include page="common/footer.jsp" />
  </div>
</body>
</html:html>