<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<bean:define id="title"><bean:message key="title.approval.list" /></bean:define><!-- 未承認一覧 -->

<html:html>
<head>
<jsp:include page="../common/header.jsp">
  <jsp:param name="title" value="<%=title%>"></jsp:param>
</jsp:include>
<script type="text/javascript">
<!--
	function doApprovalEdit(userId, viewDate, seq) {
		document.forms[0].userId.value = userId;
		document.forms[0].viewDate.value = viewDate;
		document.forms[0].seq.value = seq;
		document.forms[0].button.value = 'seq';
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
    <h3 class="page-header"><bean:message key="title.approval.list" /></h3><!-- 未承認一覧 -->

    <html:form action="/ListApproval" method="post">
      <html:messages id="msg" message="true"><div class="alert alert-info"><bean:write name="msg" /></div></html:messages>
      <logic:messagesPresent><div class="alert alert-danger"><html:errors /></div></logic:messagesPresent>

      <table class="table table-hover">
        <thead>
          <tr>
            <th class="col-sm-3"><bean:message key="editSchedule.name" /></th><!-- 社員名 -->
            <th class="col-sm-2"><bean:message key="item.date" /></th><!-- 日付 -->
            <th class="col-sm-7"><bean:message key="editReport.title" /></th><!-- タイトル -->
          </tr>
        </thead>
        <tbody>
          <logic:iterate id="item" property="approvalList" name="ListApprovalForm">
            <tr>
              <bean:define id="userId" property="userId" name="item" />
              <bean:define id="viewDate" property="viewDate" name="item" />
              <bean:define id="seq" property="seq" name="item" />
              <% String itemStr = "javascript:doApprovalEdit('" + userId + "','" + viewDate + "','" + seq + "')"; %>
              <td><html:link href="<%=itemStr%>"><bean:write property="userName" name="item" /></html:link></td>
              <td><bean:write property="viewDate" name="item" /></td>
              <td><bean:write property="title" name="item" /></td>
            </tr>
          </logic:iterate>
        </tbody>
      </table>

      <br/>
      <br/>
      <div class="text-center">
        <html:button property="cancel" onclick="setAction('cancel');" styleClass="btn btn-default"><bean:message key="button.back" /></html:button><!-- 戻る -->
      </div>

      <html:hidden property="userId" />
      <html:hidden property="viewDate" />
      <html:hidden property="seq" />
      <html:hidden property="button" />
    </html:form>

    <jsp:include page="../common/footer.jsp" />
  </div>
</body>
</html:html>
