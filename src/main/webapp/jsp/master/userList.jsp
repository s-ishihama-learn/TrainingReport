<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<bean:define id="title"><bean:message key="title.user.list" /></bean:define><!-- 社員一覧 -->

<html:html>
<head>
<jsp:include page="../common/header.jsp">
  <jsp:param name="title" value="<%=title%>"></jsp:param>
</jsp:include>
<script type="text/javascript">
<!--
	function doUserEdit(userId) {
		document.forms[0].userId.value = userId;
		document.forms[0].button.value = 'name';
		document.forms[0].submit();
	}
	-->
</script>
<link rel="stylesheet" type="text/css" href="css/header2.css">
<link rel="stylesheet" type="text/css" href="css/footer.css">
</head>

<body>
  <div class="container">
    <jsp:include page="../common/header2.jsp" />
    <logic:equal property="userCom" name="CommonSession" value="9"><jsp:include page="../navibar/naviAdmin.jsp" /></logic:equal>
    <h3 class="page-header"><bean:message key="title.user.list" /></h3><!-- 社員一覧 -->

    <html:form action="/UserList" method="post">
      <html:messages id="msg" message="true"><div class="alert alert-info"><bean:write name="msg" /></div></html:messages>
      <logic:messagesPresent><div class="alert alert-danger"><html:errors /></div></logic:messagesPresent>

      <div class="pull-right">
        <html:button property="new" onclick="setAction('new');" styleClass="btn btn-info"><bean:message key="button.new" /></html:button><!-- 新規 -->
      </div>

      <table class="table table-hover">
        <thead>
          <tr>
            <th class="col-sm-2"><bean:message key="userEdit.code" /></th><!-- 社員コード -->
            <th class="col-sm-3"><bean:message key="deptEdit.name" /></th><!-- 所属名 -->
            <th class="col-sm-5"><bean:message key="userEdit.name" /></th><!-- 社員名 -->
            <th class="col-sm-2"><bean:message key="userEdit.userCom" /></th><!-- 権限 -->
          </tr>
          <logic:iterate id="user" property="userList" name="UserListForm">
            <tr>
              <bean:define id="userId" property="userId" name="user" />
              <% String userNameStr = "javascript:doUserEdit('" + userId + "')"; %>
              <td><html:link href="<%=userNameStr%>"><bean:write property="userId" name="user" /></html:link></td>
              <td><bean:write property="deptName" name="user" /></td>
              <html:hidden property="deptCode" name="user" />
              <td><bean:write property="userName" name="user" /></td>
              <td><bean:write property="userCom" name="user" /></td>
            </tr>
          </logic:iterate>
      </table>

      <html:hidden property="userId" />
      <html:hidden property="button" />
    </html:form>

    <jsp:include page="../common/footer.jsp" />
  </div>
</body>
</html:html>