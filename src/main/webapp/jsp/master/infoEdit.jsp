<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<bean:define id="title"><bean:message key="title.info.edit" /></bean:define><!-- お知らせ編集 -->

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
    <h3 class="page-header"><bean:message key="title.info.edit" /></h3><!-- お知らせ編集 -->

    <html:form action="/InfoEdit" method="post" styleClass="form-horizontal">
      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="infoEdit.date" /></label><!-- 日付 -->
        <div class="col-sm-3">
          <logic:equal property="viewMode" name="InfoEditForm" value="1">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="infoDate"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
              <html:text property="infoDate" maxlength="24" styleClass="form-control" />
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="infoDate"/></p>
          </logic:equal>
          <logic:equal property="viewMode" name="InfoEditForm" value="2">
            <html:text property="infoDate" maxlength="24" styleClass="form-control" readonly="true" />
          </logic:equal>
        </div>
        <div class="col-sm-6"></div>
      </div>

      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="infoEdit.body" /></label><!-- 内容 -->
        <div class="col-sm-6">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="infoBody"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
              <html:textarea property="infoBody" rows="3" styleClass="form-control" />
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="infoBody"/></p>
        </div>
        <div class="col-sm-3"></div>
      </div>

      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="infoEdit.limiDate" /></label><!-- 表示期限 -->
        <div class="col-sm-3">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="limiDate"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
              <html:text property="limiDate" maxlength="10" styleClass="form-control" />
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="limiDate"/></p>
        </div>
        <div class="col-sm-6"></div>
      </div>

      <html:messages id="msg" message="true"><div class="alert alert-info"><bean:write name="msg" /></div></html:messages>
      <logic:messagesPresent property="value"><div class="alert alert-danger"><html:errors property="value" /></div></logic:messagesPresent>

      <div class="form-group">
        <div class="col-sm-offset-3 col-sm-9">
          <logic:equal property="viewMode" name="InfoEditForm" value="1">
            <html:button property="regist" onclick="setAction('regist');" styleClass="btn btn-primary"><bean:message key="button.regist" /></html:button><!-- 登録 -->
          </logic:equal>
          <logic:equal property="viewMode" name="InfoEditForm" value="2">
            <html:button property="edit" onclick="setAction('edit');" styleClass="btn btn-primary"><bean:message key="button.update" /></html:button><!-- 変更 -->
          </logic:equal>
          <logic:equal property="viewMode" name="InfoEditForm" value="2">
            <bean:define id="btn"><bean:message key="button.delete" /></bean:define>
            <bean:define id="msg"><bean:message key="confirm.delete" /></bean:define>
            <% String action = "doDelete('delete','" + msg + "');"; %>
            <input type="button" name="delete" value="<%=btn %>" onclick="<%=action %>" class="btn btn-warning"><!-- 削除 -->
          </logic:equal>
          <html:button property="cancel" onclick="doCancel('cancel');" styleClass="btn btn-default"><bean:message key="button.cancel" /></html:button><!-- キャンセル -->
        </div>
      </div>

      <html:hidden property="seq" />
      <html:hidden property="button" />
      <html:hidden property="viewMode" />
    </html:form>

    <jsp:include page="../common/footer.jsp" />
  </div>
</body>
</html:html>
