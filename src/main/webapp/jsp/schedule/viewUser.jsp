<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<bean:define id="title"><bean:message key="title.view.user" /></bean:define><!-- 表示利用者変更 -->

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
    <h3 class="page-header"><bean:message key="title.view.user" /></h3><!-- 表示利用者変更 -->

    <html:form action="/ViewUser" method="post">
      <table class="table table-hover">
        <thead>
          <tr>
            <th class="col-sm-2"><bean:message key="view.user.select" /></th><!-- 選択 -->
            <th class="col-sm-4"><bean:message key="view.user.dept" /></th><!-- 所属 -->
            <th class="col-sm-6"><bean:message key="view.user.name" /></th><!-- 氏名 -->
          </tr>
        </thead>
        <bean:define id="viewUserId" property="viewUserId" name="CommonSession" scope="session" />
        <bean:define id="userRadioMap" property="userIdMap" name="ViewUserForm" />
        <tbody>
          <logic:iterate id="userInfo" property="values" name="userRadioMap">
            <tr class="list">
              <bean:define id="user" name="userInfo" property="value" type="jp.logware.custmer.subform.UserValue" />
              <td><html:radio idName="userInfo" property="userId" value="key" /></td>
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