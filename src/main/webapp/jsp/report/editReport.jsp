<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<bean:define id="title"><bean:message key="title.report.edit" /></bean:define><!-- 顧客日報編集 -->

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
    <h3 class="page-header"><bean:message key="title.report.edit" /></h3><!-- 顧客日報編集 -->

    <html:form action="/EditReport" method="post" styleClass="form-horizontal">
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
        <label class="col-sm-3 control-label"><bean:message key="editReport.title" /></label><!-- タイトル -->
        <div class="col-sm-6">
          <logic:equal property="editMode" name="CommonSession" value="true">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="title"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
            <html:textarea property="title" rows="5" styleClass="form-control" />
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="title"/></p>
          </logic:equal>
          <logic:equal property="editMode" name="CommonSession" value="false">
            <html:textarea property="title" rows="5" styleClass="form-control" readonly="true" />
          </logic:equal>
        </div>
        <div class="col-sm-3"></div>
      </div>

      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="editReport.reportType" /></label><!-- 日報分類 -->
        <div class="col-sm-6">
          <logic:equal property="editMode" name="CommonSession" value="true">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="reportType"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
            <html:select property="reportType" styleClass="form-control">
              <html:optionsCollection property="typeSelectBox" name="CommonSession" value="key" label="value" />
            </html:select>
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="reportType"/></p>
          </logic:equal>
          <logic:equal property="editMode" name="CommonSession" value="false">
            <html:select property="reportType" styleClass="form-control" disabled ="true">
              <html:optionsCollection property="typeSelectBox" name="CommonSession" value="key" label="value" />
            </html:select>
            <html:hidden property="reportType"/>
          </logic:equal>
        </div>
        <div class="col-sm-3"></div>
      </div>

      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="editReport.fact" /></label><!-- 事実 -->
        <div class="col-sm-6">
          <logic:equal property="editMode" name="CommonSession" value="true">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="fact"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
            <html:textarea property="fact" rows="5" styleClass="form-control" />
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="fact"/></p>
          </logic:equal>
          <logic:equal property="editMode" name="CommonSession" value="false">
            <html:textarea property="fact" rows="5" styleClass="form-control" readonly="true" />
          </logic:equal>
        </div>
        <div class="col-sm-3"></div>
      </div>

      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="editReport.guess" /></label><!-- 推察 -->
        <div class="col-sm-6">
          <logic:equal property="editMode" name="CommonSession" value="true">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="guess"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
            <html:textarea property="guess" rows="5" styleClass="form-control" />
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="guess"/></p>
          </logic:equal>
          <logic:equal property="editMode" name="CommonSession" value="false">
            <html:textarea property="guess" rows="5" styleClass="form-control" readonly="true" />
          </logic:equal>
        </div>
        <div class="col-sm-3"></div>
      </div>

      <div class="form-group">
        <label class="col-sm-3 control-label"><bean:message key="editReport.next" /></label><!-- 次回 -->
        <div class="col-sm-6">
          <logic:equal property="editMode" name="CommonSession" value="true">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="next"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
            <html:textarea property="next" rows="5" styleClass="form-control" />
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="next"/></p>
          </logic:equal>
          <logic:equal property="editMode" name="CommonSession" value="false">
            <html:textarea property="next" rows="5" styleClass="form-control" readonly="true" />
          </logic:equal>
        </div>
        <div class="col-sm-3"></div>
      </div>


      <logic:equal property="viewMode" name="EditReportForm" value="3">
        <div class="form-group">
          <label class="col-sm-3 control-label"><bean:message key="editReport.comment.approval" /></label><!-- 承認コメント -->
          <div class="col-sm-6">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="applovalMessage"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
              <html:textarea property="applovalMessage" rows="5" styleClass="form-control" />
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="applovalMessage"/></p>
          </div>
          <div class="col-sm-3"></div>
        </div>

        <logic:iterate id="comment" property="commentList" name="EditReportForm">
          <div class="form-group">
            <label class="col-sm-3 control-label">
              <bean:message key="editReport.comment.to" /><br><bean:write property="commentUser" name="comment" /><bean:message key="editReport.comment.p" />
            </label><!-- コメント投稿者<br>さん -->
            <div class="col-sm-6">
              <html:textarea property="comment" name="comment" rows="3" styleClass="form-control" readonly="true" />
            </div>
            <div class="col-sm-3"></div>
          </div>
        </logic:iterate>
      </logic:equal>


      <logic:equal property="viewMode" name="EditReportForm" value="4">
        <logic:equal property="applyFlag" name="EditReportForm" value="1">
        <div class="form-group">
          <label class="col-sm-3 control-label">
            <bean:write property="applovalUser" name="EditReportForm" />
            <bean:message key="editReport.comment.p" /><br><bean:message key="editReport.comment.from" />
          </label><!-- さん<br>からの承認コメント -->
          <div class="col-sm-6">
            <logic:equal property="editMode" name="CommonSession" value="true">
           <bean:define id="haserr" value=""/>
           <logic:messagesPresent property="applovalMessage"><%haserr="has-error";%></logic:messagesPresent>
            <div class="input-group <%=haserr%>">
              <html:textarea property="applovalMessage" rows="5" styleClass="form-control" />
            <span class="input-group-addon"><bean:message key="label.required" /></span>
            </div>
            <p class="text-danger"><html:errors property="applovalMessage"/></p>
            </logic:equal>
            <logic:equal property="editMode" name="CommonSession" value="false">
              <html:textarea property="applovalMessage" rows="5" styleClass="form-control" readonly="true" />
            </logic:equal>
          </div>
          <div class="col-sm-3"></div>
        </div>
        </logic:equal>
      </logic:equal>

      <html:messages id="msg" message="true"><div class="alert alert-info"><bean:write name="msg" /></div></html:messages>
      <logic:messagesPresent property="value"><div class="alert alert-danger"><html:errors property="value" /></div></logic:messagesPresent>

      <div class="form-group">
        <div class="col-sm-offset-3 col-sm-9">
          <logic:equal property="viewMode" name="EditReportForm" value="1">
            <html:button property="regist" onclick="setAction('regist');" styleClass="btn btn-primary"><bean:message key="button.regist" /></html:button><!-- 登録 -->
            <html:button property="cancel" onclick="doCancel('cancelNew');" styleClass="btn btn-default"><bean:message key="button.cancel" /></html:button><!-- キャンセル -->
          </logic:equal>
          <logic:equal property="viewMode" name="EditReportForm" value="2">
            <html:button property="edit" onclick="setAction('edit');" styleClass="btn btn-primary"><bean:message key="button.update" /></html:button><!-- 変更 -->
            <bean:define id="btn"><bean:message key="button.delete" /></bean:define>
            <bean:define id="msg"><bean:message key="confirm.delete" /></bean:define>
            <% String action = "doDelete('delete','" + msg + "');"; %>
            <input type="button" name="delete" value="<%=btn %>" onclick="<%=action %>" class="btn btn-warning"><!-- 削除 -->
            <html:button property="cancel" onclick="doCancel('cancel');" styleClass="btn btn-default"><bean:message key="button.cancel" /></html:button><!-- キャンセル -->
          </logic:equal>
          <logic:equal property="viewMode" name="EditReportForm" value="3">
            <html:button property="apploval" onclick="setAction('apploval');" styleClass="btn btn-primary"><bean:message key="button.approval" /></html:button><!-- 承認 -->
            <html:button property="cancel" onclick="doCancel('cancel2');" styleClass="btn btn-default"><bean:message key="button.cancel" /></html:button><!-- キャンセル -->
          </logic:equal>
          <logic:equal property="viewMode" name="EditReportForm" value="4">
            <html:button property="cancel" onclick="doCancel('cancel');" styleClass="btn btn-default"><bean:message key="button.back" /></html:button><!-- 戻る -->
          </logic:equal>
        </div>
      </div>

      <html:hidden property="userId" />
      <html:hidden property="viewDate" />
      <html:hidden property="seq" />
      <html:hidden property="applyFlag" />
      <html:hidden property="applovalUserId" />
      <html:hidden property="button" />
      <html:hidden property="viewMode" />
      <html:hidden property="commentUser" />
    </html:form>

    <jsp:include page="../common/footer.jsp" />
  </div>
</body>
</html:html>
