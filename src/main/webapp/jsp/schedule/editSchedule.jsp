<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<bean:define id="title"><bean:message key="title.schedule.edit" /></bean:define><!-- スケジュール編集 -->

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
    <h3 class="page-header"><bean:message key="title.schedule.edit" /></h3><!-- スケジュール編集 -->

    <html:form action="/EditSchedule" method="post" styleClass="form-horizontal">
      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="editSchedule.name" /></label><!-- 社員名 -->
        <div class="col-sm-6">
          <html:text property="userName" styleClass="form-control" readonly="true" />
        </div>
        <div class="col-sm-3"></div>
      </div>

      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="item.viewDate" /></label><!-- 年月日 -->
        <div class="col-sm-6">
          <html:text property="viewDateDisp" name="CommonSession" styleClass="form-control" readonly="true" />
        </div>
        <div class="col-sm-3"></div>
      </div>

      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="editSchedule.time" /></label><!-- 予定時刻 -->
        <logic:equal property="editMode" name="CommonSession" value="true">
          <div class="col-sm-2">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="startTime"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
            <html:select property="startTime" styleClass="form-control" >
              <html:optionsCollection property="startTimeSelectBox" name="CommonSession" value="key" label="value" />
            </html:select>
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="startTime"/></p>
          </div>
          <div class="col-sm-2 text-center"><bean:message key="item.fromTo" /></div><!-- ～ -->
          <div class="col-sm-2">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="endTime"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
            <html:select property="endTime" styleClass="form-control" >
              <html:optionsCollection property="endTimeSelectBox" name="CommonSession" value="key" label="value" />
            </html:select>
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="endTime"/></p>
          </div>
        </logic:equal>
        <logic:equal property="editMode" name="CommonSession" value="false">
          <div class="col-sm-2">
            <html:select property="startTime" styleClass="form-control" disabled ="true">
              <html:optionsCollection property="startTimeSelectBox" name="CommonSession" value="key" label="value" />
            </html:select>
            <html:hidden property="startTime" />
          </div>
          <div class="col-sm-1 text-center"><bean:message key="item.fromTo" /></div><!-- ～ -->
          <div class="col-sm-2">
            <html:select property="endTime" styleClass="form-control" disabled ="true">
              <html:optionsCollection property="endTimeSelectBox" name="CommonSession" value="key" label="value" />
            </html:select>
            <html:hidden property="endTime" />
          </div>
        </logic:equal>
        <div class="col-sm-3"></div>
      </div>

      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="editSchedule.title" /></label><!-- タイトル -->
        <div class="col-sm-6">
          <logic:equal property="editMode" name="CommonSession" value="true">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="title"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
            <html:text property="title" maxlength="100" styleClass="form-control" />
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="title"/></p>
          </logic:equal>
          <logic:equal property="editMode" name="CommonSession" value="false">
            <html:text property="title" styleClass="form-control" readonly="true" />
          </logic:equal>
        </div>
        <div class="col-sm-3"></div>
      </div>

      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="editSchedule.body" /></label><!-- 内容 -->
        <div class="col-sm-6">
          <logic:equal property="editMode" name="CommonSession" value="true"><html:textarea property="body" rows="5" styleClass="form-control" /></logic:equal>
          <logic:equal property="editMode" name="CommonSession" value="false"><html:textarea property="body" rows="5" styleClass="form-control" readonly="true" /></logic:equal>
        </div>
        <div class="col-sm-3"></div>
      </div>

      <html:messages id="msg" message="true"><div class="alert alert-info"><bean:write name="msg" /></div></html:messages>
      <logic:messagesPresent property="value"><div class="alert alert-danger"><html:errors property="value" /></div></logic:messagesPresent>

      <div class="form-group">
        <div class="col-sm-offset-3 col-sm-9">
          <logic:equal property="editMode" name="CommonSession" value="true">
            <logic:equal property="viewMode" name="EditScheduleForm" value="1">
              <html:button property="regist" onclick="setAction('regist');" styleClass="btn btn-primary"><bean:message key="button.regist" /></html:button><!-- 登録 -->
              <html:button property="cancel" onclick="setAction('cancelNew');" styleClass="btn btn-default"><bean:message key="button.cancel" /></html:button><!-- キャンセル -->
            </logic:equal>
            <logic:equal property="viewMode" name="EditScheduleForm" value="2">
              <html:button property="edit" onclick="setAction('edit');" styleClass="btn btn-primary"><bean:message key="button.update" /></html:button><!-- 変更 -->
              <bean:define id="btn"><bean:message key="button.delete" /></bean:define>
              <bean:define id="msg"><bean:message key="confirm.delete" /></bean:define>
              <% String action = "doDelete('delete','" + msg + "');"; %>
              <input type="button" name="delete" value="<%=btn %>" onclick="<%=action %>" class="btn btn-warning"><!-- 削除 -->
              <html:button property="cancel" onclick="setAction('cancel');" styleClass="btn btn-default"><bean:message key="button.cancel" /></html:button><!-- キャンセル -->
            </logic:equal>
          </logic:equal>
          <logic:equal property="editMode" name="CommonSession" value="false">
            <html:button property="cancel" onclick="setAction('cancel');" styleClass="btn btn-default"><bean:message key="button.back" /></html:button><!-- 戻る -->
          </logic:equal>
        </div>
      </div>

      <html:hidden property="userId" />
      <html:hidden property="viewDate" />
      <html:hidden property="seq" />
      <html:hidden property="button" />
      <html:hidden property="viewMode" />
    </html:form>

    <jsp:include page="../common/footer.jsp" />
  </div>
</body>
</html:html>