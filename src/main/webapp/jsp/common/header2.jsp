<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<div id="top"></div>
<div id="header">
  <div id="header_inner1">
    <h1><bean:message key="header.program" /></h1><!-- Log Custmer -->
    <p><bean:message key="header.title" /></p><!-- 営業日報 -->
  </div>

  <div id="header_inner2">
    <span class="text-primary"><bean:write property="userName" name="CommonSession" /></span> <span class="text-muted"><bean:message key="login.user.welcome" />&nbsp;&nbsp;</span>
    <html:button property="new" onclick="setMenuAction('passwd');" styleClass="btn btn-default btn-xs">
      <bean:message key="link.passwd" />
    </html:button>
    <html:button property="new" onclick="setMenuAction('logout');" styleClass="btn btn-default btn-xs">
      <bean:message key="link.logout" />
    </html:button>
  </div>
</div>

<div style="margin: 20px;"></div>
