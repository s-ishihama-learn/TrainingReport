<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<bean:define id="title"><bean:message key="title.type.edit" /></bean:define><!-- 分類編集 -->

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
    <h3 class="page-header"><bean:message key="title.type.edit" /></h3><!-- 分類編集 -->

    <html:form action="/TypeEdit" method="post" styleClass="form-horizontal">
      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="typeEdit.code" /></label><!-- 分類コード -->
        <div class="col-sm-6">
          <logic:equal property="viewMode" name="TypeEditForm" value="1">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="typeCode"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
              <html:text property="typeCode" maxlength="3" styleClass="form-control" />
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="typeCode"/></p>
          </logic:equal>
          <logic:equal property="viewMode" name="TypeEditForm" value="2">
            <html:text property="typeCode" maxlength="3" styleClass="form-control" readonly="true" />
          </logic:equal>
        </div>
        <div class="col-sm-3"></div>
      </div>

      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="typeEdit.name" /></label><!-- 分類名 -->
        <div class="col-sm-6">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="typeName"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
              <html:text property="typeName" maxlength="50" styleClass="form-control" />
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="typeName"/></p>
        </div>
        <div class="col-sm-3"></div>
      </div>

      <html:messages id="msg" message="true"><div class="alert alert-info"><bean:write name="msg" /></div></html:messages>
      <logic:messagesPresent property="value"><div class="alert alert-danger"><html:errors property="value" /></div></logic:messagesPresent>

      <div class="form-group">
        <div class="col-sm-offset-3 col-sm-9">
          <logic:equal property="viewMode" name="TypeEditForm" value="1">
            <html:button property="regist" onclick="setAction('regist');" styleClass="btn btn-primary"><bean:message key="button.regist" /></html:button><!-- 登録 -->
          </logic:equal>
          <logic:equal property="viewMode" name="TypeEditForm" value="2">
            <html:button property="edit" onclick="setAction('edit');" styleClass="btn btn-primary"><bean:message key="button.update" /></html:button><!-- 変更 -->
          </logic:equal>
          <logic:equal property="viewMode" name="TypeEditForm" value="2">
            <bean:define id="btn"><bean:message key="button.delete" /></bean:define>
            <bean:define id="msg"><bean:message key="confirm.delete" /></bean:define>
            <% String action = "doDelete('delete','" + msg + "');"; %>
            <input type="button" name="delete" value="<%=btn %>" onclick="<%=action %>" class="btn btn-warning"><!-- 削除 -->
          </logic:equal>
          <html:button property="cancel" onclick="doCancel('cancel');" styleClass="btn btn-default"><bean:message key="button.cancel" /></html:button><!-- キャンセル -->
        </div>
      </div>

      <html:hidden property="typeCode" />
      <html:hidden property="button" />
      <html:hidden property="viewMode" />
    </html:form>

    <jsp:include page="../common/footer.jsp" />
  </div>
</body>
</html:html>