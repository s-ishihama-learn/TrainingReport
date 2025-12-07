<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<bean:define id="title"><bean:message key="title.view.group" /></bean:define><!-- 表示グループ設定 -->

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
    <h3 class="page-header"><bean:message key="title.view.group" /></h3><!-- 表示グループ設定 -->

    <html:form action="/ViewGroupUser" method="post">
      <table class="table table-hover">
        <thead>
          <tr>
            <th class="col-sm-2"><bean:message key="view.user.select" /></th><!-- 選択 -->
            <th class="col-sm-4"><bean:message key="view.user.dept" /></th><!-- 所属 -->
            <th class="col-sm-6"><bean:message key="view.user.name" /></th><!-- 氏名 -->
          </tr>
        </thead>
        <tbody>
          <logic:iterate id="user" property="userList" name="ViewGroupUserForm">
            <tr class="list">
              <bean:define id="value" property="userId" name="user" />
              <% String valueStr = (String) value; %>
              <td><html:multibox property="checked" value="<%=valueStr%>" /></td>
              <td><bean:write property="deptName" name="user" /></td>
              <td><bean:write property="userName" name="user" /></td>
            </tr>
          </logic:iterate>
        </tbody>
      </table>

      <div class="form-group">
        <div class="col-sm-12">
          <html:button property="change" onclick="setAction('change');" styleClass="btn btn-primary"><bean:message key="button.update" /></html:button><!-- 変更 -->
          <html:button property="cancel" onclick="setAction('cancel');" styleClass="btn btn-default"><bean:message key="button.cancel" /></html:button><!-- キャンセル -->
        </div>
      </div>
      &nbsp;

      <html:hidden property="deptCode" />
      <html:hidden property="userId" />
      <html:hidden property="button" />
    </html:form>

    <jsp:include page="../common/footer.jsp" />
  </div>
</body>
</html:html>