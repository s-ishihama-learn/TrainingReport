<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#nav-menu-1">
        <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#"></a>
    </div>
    <div class="collapse navbar-collapse" id="nav-menu-1">
      <ul class="nav navbar-nav">
        <logic:equal property="viewDisp" name="CommonSession" value="1">
        <li><a href="javascript:setMenuApprovalAction('viewWeek');"><bean:message key="menu.group" /></a></li><!-- グループ表示 -->
        <li><a href="javascript:setMenuApprovalAction('viewUser');"><bean:message key="menu.view.user" /></a></li><!-- 表示利用者変更 -->
        </logic:equal>
        <logic:equal property="viewDisp" name="CommonSession" value="2">
        <li><a href="javascript:setMenuApprovalAction('viewMonth');"><bean:message key="menu.month" /></a></li><!-- 月表示 -->
        <li><a href="javascript:setMenuApprovalAction('viewGroup');"><bean:message key="menu.view.group" /></a></li><!-- 表示グループ設定 -->
        </logic:equal>
        <li><a href="javascript:setMenuApprovalAction('approval');"><bean:message key="menu.approval" /></a></li><!-- 承認 -->
      </ul>
    </div>
  </div>
</nav>