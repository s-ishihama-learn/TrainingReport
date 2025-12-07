<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<div id="top"></div>

<div id="header">
<div id="header_inner1">
    <h1><bean:message key="header.program" /></h1><!-- Log Custmer -->
    <p><bean:message key="header.title" /></p><!-- 営業日報 -->
</div>
<div id="header_inner2">
	<div id="postinfo">
      <bean:define id="scheduleTitle"><bean:message key="header.schedule.title" /></bean:define>
      <bean:define id="scheduleContent"><bean:message key="header.schedule.content" /></bean:define>
      <button class="style-icon" data-toggle="popover" data-placement="left" title="<%=scheduleTitle%>" data-content="<%=scheduleContent%>">
        <span><bean:message key="header.schedule" /></span><!-- 予定 -->
      </button>

      <bean:define id="factTitle"><bean:message key="header.fact.title" /></bean:define>
      <bean:define id="factContent"><bean:message key="header.fact.content" /></bean:define>
      <button class="style-icon" data-toggle="popover" data-placement="left" title="<%=factTitle%>" data-content="<%=factContent%>">
        <span><bean:message key="header.fact" /></span><!-- 事実 -->
      </button>

      <bean:define id="guessTitle"><bean:message key="header.guess.title" /></bean:define>
      <bean:define id="guessContent"><bean:message key="header.guess.content" /></bean:define>
      <button class="style-icon" data-toggle="popover" data-placement="left" title="<%=guessTitle%>" data-content="<%=guessContent%>">
        <span><bean:message key="header.guess" /></span><!-- 推察 -->
      </button>

      <bean:define id="nextTitle"><bean:message key="header.next.title" /></bean:define>
      <bean:define id="nextContent"><bean:message key="header.next.content" /></bean:define>
      <button class="style-icon" data-toggle="popover" data-placement="left" title="<%=nextTitle%>" data-content="<%=nextContent%>">
        <span><bean:message key="header.next" /></span><!-- 次回 -->
      </button>

      <bean:define id="approvalTitle"><bean:message key="header.approval.title" /></bean:define>
      <bean:define id="approvalContent"><bean:message key="header.approval.content" /></bean:define>
      <button class="style-icon" data-toggle="popover" data-placement="left" title="<%=approvalTitle%>" data-content="<%=approvalContent%>">
        <span><bean:message key="header.approval" /></span><!-- 承認 -->
      </button>
	</div>
</div>
</div>

<div style="margin: 50px;"></div>


<script src="<%=request.getContextPath()%>/js/jquery-2.2.3.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script>$('[data-toggle=popover]').popover();</script>
<style type="text/css">
button.style-icon {
    -moz-user-select: none;
    background-image: none;
    border: 1px solid transparent;
    border-radius: 4px;
    cursor: pointer;
    display: inline-block;
    font-size: 12px;
    font-weight: 400;
    line-height: 1.42857;
    margin-bottom: 0;
    padding: 2px;
    text-align: center;
    vertical-align: middle;
    white-space: nowrap;
    background-color: #ffffff;
}
</style>
