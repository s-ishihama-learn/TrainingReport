<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<bean:define id="title"><bean:message key="title.type.list" /></bean:define><!-- 分類一覧 -->

<html:html>
<head>
<jsp:include page="../common/header.jsp">
  <jsp:param name="title" value="<%=title%>"></jsp:param>
</jsp:include>
<script type="text/javascript">
<!--
	function doTypeEdit(typeCode) {
		document.forms[0].typeCode.value = typeCode;
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
    <h3 class="page-header"><bean:message key="title.type.list" /></h3><!-- 分類一覧 -->

    <html:form action="/TypeList" method="post">
      <html:messages id="msg" message="true"><div class="alert alert-info"><bean:write name="msg" /></div></html:messages>
      <logic:messagesPresent><div class="alert alert-danger"><html:errors /></div></logic:messagesPresent>

      <div class="pull-right">
        <html:button property="new" onclick="setAction('new');" styleClass="btn btn-info"><bean:message key="button.new" /></html:button><!-- 新規 -->
      </div>

      <table class="table table-hover">
        <thead>
          <tr>
            <th class="col-sm-2"><bean:message key="typeEdit.code" /></th><!-- 分類コード -->
            <th class="col-sm-10"><bean:message key="typeEdit.name" /></th><!-- 分類名 -->
          </tr>
        </thead>
        <tbody>
          <logic:iterate id="type" property="typeList" name="TypeListForm">
            <tr>
              <bean:define id="typeCode" property="typeCode" name="type" />
              <% String typeNameStr = "javascript:doTypeEdit('" + typeCode + "')"; %>
              <td><html:link href="<%=typeNameStr%>"><bean:write property="typeCode" name="type" /></html:link></td>
              <td><bean:write property="typeName" name="type" /></td>
            </tr>
          </logic:iterate>
        </tbody>
      </table>

      <html:hidden property="typeCode" />
      <html:hidden property="button" />
    </html:form>

    <jsp:include page="../common/footer.jsp" />
  </div>
</body>
</html:html>
