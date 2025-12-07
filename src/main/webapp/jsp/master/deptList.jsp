<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<bean:define id="title"><bean:message key="title.dept.list" /></bean:define><!-- 所属一覧 -->

<html:html>
<head>
<jsp:include page="../common/header.jsp">
  <jsp:param name="title" value="<%=title%>"></jsp:param>
</jsp:include>
<script type="text/javascript">
<!--
	function doDeptEdit(deptCode) {
		document.forms[0].deptCode.value = deptCode;
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
    <h3 class="page-header"><bean:message key="title.dept.list" /></h3><!-- 所属一覧 -->

    <html:form action="/DeptList" method="post">
      <html:messages id="msg" message="true"><div class="alert alert-info"><bean:write name="msg" /></div></html:messages>
      <logic:messagesPresent><div class="alert alert-danger"><html:errors /></div></logic:messagesPresent>

      <div class="pull-right">
        <html:button property="new" onclick="setAction('new');" styleClass="btn btn-info"><bean:message key="button.new" /></html:button><!-- 新規 -->
      </div>

      <table class="table table-hover">
        <thead>
          <tr>
            <th class="col-sm-2"><bean:message key="deptEdit.code" /></th><!-- 所属コード -->
            <th class="col-sm-10"><bean:message key="deptEdit.name" /></th><!-- 所属名 -->
          </tr>
        </thead>
        <tbody>
          <logic:iterate id="dept" property="deptList" name="DeptListForm">
            <tr>
              <bean:define id="deptCode" property="deptCode" name="dept" />
              <% String deptNameStr = "javascript:doDeptEdit('" + deptCode + "')"; %>
              <td><html:link href="<%=deptNameStr%>"><bean:write property="deptCode" name="dept" /></html:link></td>
              <td><bean:write property="deptName" name="dept" /></td>
            </tr>
          </logic:iterate>
        </tbody>
      </table>

      <html:hidden property="deptCode" />
      <html:hidden property="button" />
    </html:form>

    <jsp:include page="../common/footer.jsp" />
  </div>
</body>
</html:html>