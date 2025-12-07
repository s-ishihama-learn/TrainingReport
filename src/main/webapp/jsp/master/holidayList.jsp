<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<bean:define id="title"><bean:message key="title.holiday.list" /></bean:define><!-- 休日一覧 -->

<html:html>
<head>
<jsp:include page="../common/header.jsp">
  <jsp:param name="title" value="<%=title%>"></jsp:param>
</jsp:include>
<script type="text/javascript">
<!--
	function doDateEdit(date) {
		document.forms[0].date.value = date;
		document.forms[0].button.value = 'date';
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
    <h3 class="page-header"><bean:message key="title.holiday.list" /></h3><!-- 休日一覧 -->

    <html:form action="/HolidayList" method="post">
      <html:messages id="msg" message="true"><div class="alert alert-info"><bean:write name="msg" /></div></html:messages>
      <logic:messagesPresent><div class="alert alert-danger"><html:errors /></div></logic:messagesPresent>

      <div class="pull-right">
        <html:button property="new" onclick="setAction('new');" styleClass="btn btn-info"><bean:message key="button.new" /></html:button><!-- 新規 -->
      </div>

      <table class="table table-hover">
        <thead>
          <tr>
            <th class="col-sm-2"><bean:message key="holidayEdit.date" /></th><!-- 休日 -->
            <th class="col-sm-7"><bean:message key="holidayEdit.note" /></th><!-- 備考 -->
            <th class="col-sm-3"><bean:message key="holidayEdit.kind" /></th><!-- 休日種別 -->
          </tr>
          <logic:iterate id="holiday" property="holidayList" name="HolidayListForm">
            <tr>
              <bean:define id="date" property="date" name="holiday" />
              <% String dateStr = "javascript:doDateEdit('" + date + "')"; %>
              <td><html:link href="<%=dateStr%>"><bean:write property="date" name="holiday" /></html:link></td>
              <td><bean:write property="note" name="holiday" /></td>
              <td><bean:write property="kindName" name="holiday" /></td>
            </tr>
          </logic:iterate>
      </table>

      <html:hidden property="date" />
      <html:hidden property="button" />
    </html:form>

    <jsp:include page="../common/footer.jsp" />
  </div>
</body>
</html:html>