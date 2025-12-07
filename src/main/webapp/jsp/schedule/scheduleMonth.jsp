<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ page import="jp.logware.custmer.subform.DayItemValue"%>
<bean:define id="title"><bean:message key="title.schdule.month" /></bean:define><!-- 月表示 -->

<html:html>
<head>
<jsp:include page="../common/header.jsp">
  <jsp:param name="title" value="<%=title%>"></jsp:param>
</jsp:include>
<script type="text/javascript">
<!--
	function doListSchedule(userId, viewDate) {
		document.forms[0].userId.value = userId;
		document.forms[0].viewDate.value = viewDate;
		document.forms[0].button.value = 'schedule';
		document.forms[0].submit();
	}
	function doListReport(userId, viewDate) {
		document.forms[0].userId.value = userId;
		document.forms[0].viewDate.value = viewDate;
		document.forms[0].seq.value = 1;
		document.forms[0].button.value = 'seq';
		document.forms[0].submit();
	}
	function doNewSchedule(userId, viewDate) {
		document.forms[0].userId.value = userId;
		document.forms[0].viewDate.value = viewDate;
		document.forms[0].seq.value = 0;
		document.forms[0].button.value = 'new';
		document.forms[0].submit();
	}
	-->
</script>
<link rel="stylesheet" type="text/css" href="css/header2.css">
<link rel="stylesheet" type="text/css" href="css/footer.css">
<link rel="stylesheet" type="text/css" href="css/month.css">
</head>

<body>
  <div class="container">
    <jsp:include page="../common/header2.jsp" />
    <logic:equal property="userCom" name="CommonSession" value="9"><jsp:include page="../navibar/naviAdmin.jsp" /></logic:equal>
    <logic:equal property="userCom" name="CommonSession" value="2"><jsp:include page="../navibar/naviApproval.jsp" /></logic:equal>
    <logic:equal property="userCom" name="CommonSession" value="1"><jsp:include page="../navibar/naviGeneral.jsp" /></logic:equal>

    <html:form action="/ScheduleMonth" method="post">
      <div class="col-sm-2 text-left">
        <div class="btn-group btn-group-xs">
          <html:button property="regist" onclick="setAction('lastMonth');" styleClass="btn btn-success"><bean:message key="button.month.last" /></html:button><!-- 前の月 -->
        </div>
      </div>
      <div class="col-sm-8 text-center">
        <h4>
          <bean:write property="viewMonthDisp" name="CommonSession" scope="session" />&nbsp;&nbsp;
          <bean:message key="item.bracket.s" /><bean:write property="viewUserName" name="CommonSession" /><bean:message key="item.bracket.e" />
        </h4>
      </div>
      <div class="col-sm-2 text-right">
        <div class="btn-group btn-group-xs">
          <html:button property="regist" onclick="setAction('nextMonth');" styleClass="btn btn-success"><bean:message key="button.month.next" /></html:button><!-- 次の月 -->
        </div>
      </div>

      <table class="month">
        <tr height="20px">
          <th class="sun"><bean:message key="calendar.sun" /></th><!-- 日 -->
          <th class="week"><bean:message key="calendar.mon" /></th><!-- 月 -->
          <th class="week"><bean:message key="calendar.tue" /></th><!-- 火 -->
          <th class="week"><bean:message key="calendar.wed" /></th><!-- 水 -->
          <th class="week"><bean:message key="calendar.thu" /></th><!-- 木 -->
          <th class="week"><bean:message key="calendar.fri" /></th><!-- 金 -->
          <th class="sat"><bean:message key="calendar.sat" /></th><!-- 土 -->
        </tr>
        <bean:define id="monthItem" property="monthItem" name="ScheduleMonthForm" type="java.util.List<Object>" />
        <bean:define id="start" property="startDay" name="ScheduleMonthForm" type="java.lang.Integer" />
        <bean:define id="last" property="lastDay" name="ScheduleMonthForm" type="java.lang.Integer" />
        <bean:define id="holidayMap" property="holidayColorMap" name="ScheduleMonthForm" type="java.util.Map" />
        <bean:define id="viewMonth" property="viewMonth" name="ScheduleMonthForm" />
        <bean:define id="newLinkString" property="newLinkString" name="CommonSession" scope="session" type="java.lang.String[]" />
        <bean:define id="itsMe" property="itsMe" name="CommonSession" scope="session" />
        <% int i = 0; %>
        <logic:iterate id="weekItems" name="monthItem">
          <tr>
            <bean:define id="weekItem" name="weekItems" type="java.util.List<Object>" />
            <logic:iterate id="dayItems" name="weekItem">
              <%
              start++;
              String ds = "0" + String.valueOf(start);
              String dayStr = viewMonth + ds.substring(ds.length() - 2, ds.length());
              if ((i % 7) == 0) {
                if (start < 1) {
                  out.println("<td class=\"none\">");
                } else {
                  out.println("<td class=\"sun\">");
                }
              } else if ((i % 7) == 6) {
                if (start > last) {
                  out.println("<td class=\"none\">");
                } else {
                  if (holidayMap.get(dayStr) == null) {
                    out.println("<td class=\"sat\">");
                  } else {
                    String c = (String)holidayMap.get(dayStr);
                    out.println("<td class=\"week\" style=\"background-color: " + c + "\">");
                  }
                }
              } else {
                if (start < 1 || start > last) {
                  out.println("<td class=\"none\">");
                } else {
                  if (holidayMap.get(dayStr) == null) {
                    out.println("<td class=\"week\">");
                  } else {
                    String c = (String)holidayMap.get(dayStr);
                    out.println("<td class=\"week\" style=\"background-color: " + c + "\">");
                  }
                }
              }
              %>
                <table class="day">
                  <bean:define id="day" value="<%=start.toString()%>" />
                  <logic:lessThan name="day" value="1">
                    <tr>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                  </logic:lessThan>
                  <logic:greaterThan name="day" value="<%=last.toString()%>">
                    <tr>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                  </logic:greaterThan>
                  <logic:greaterEqual name="day" value="1">
                    <logic:lessEqual name="day" value="<%=last.toString()%>">
                      <tr height="20px">
                        <td class="left bold"><bean:write name="day" /></td>
                        <logic:equal name="itsMe" value="true">
                          <td class="right">
                            <html:link href="<%=newLinkString[start - 1]%>" styleClass="btn btn-default btn-xs">
                              <span class="glyphicon glyphicon-list-alt"></span><bean:message key="button.new" />
                            </html:link><!-- 新規 -->
                          </td>
                        </logic:equal>
                      </tr>
                      <logic:empty name="dayItems">
                        <tr><td colspan="2">&nbsp;</td></tr>
                        <tr><td colspan="2">&nbsp;</td></tr>
                      </logic:empty>
                      <logic:notEmpty name="dayItems">
                        <tr>
                          <td colspan="2" class="center">
                            <bean:define id="dayItem" name="dayItems" type="java.util.List<Object>" />
                            <logic:iterate id="items" name="dayItem">
                              <bean:define id="item" name="items" type="jp.logware.custmer.subform.DayItemValue" />
                              <bean:define id="userId" property="userId" name="item" />
                              <bean:define id="viewDate" property="viewDate" name="item" />
                              <logic:equal property="kind" name="item" value="S">
                                <% String scheduleStr = "javascript:doListSchedule('" + userId + "','" + viewDate + "')"; %>
                                <html:link href="<%=scheduleStr%>" styleClass="btn btn-success btn-xs">
                                  <span class="glyphicon glyphicon-time"></span>&nbsp;<bean:message key="button.schedule" />
                                </html:link><!-- スケジュール -->
                              </logic:equal>
                              <p style="line-height: 10px"></p>
                              <logic:equal property="kind" name="item" value="R">
                                <% String reportStr = "javascript:doListReport('" + userId + "','" + viewDate + "')"; %>
                                <html:link href="<%=reportStr%>" styleClass="btn btn-primary btn-xs">
                                  <span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;<bean:message key="button.report" />&nbsp;&nbsp;&nbsp;&nbsp;
                                </html:link><!-- 営業日報 -->
                              </logic:equal>
                            </logic:iterate>
                          </td>
                        </tr>
                      </logic:notEmpty>
                    </logic:lessEqual>
                  </logic:greaterEqual>
                </table>
              </td>
              <% i++; %>
            </logic:iterate>
          </tr>
        </logic:iterate>
      </table>

      <html:hidden property="userId" />
      <html:hidden property="viewDate" />
      <html:hidden property="seq" />
      <html:hidden property="button" />
    </html:form>

    <jsp:include page="../common/footer.jsp" />
  </div>
</body>
</html:html>