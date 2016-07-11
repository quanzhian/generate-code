<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${r"$"}{pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${table.entityComment}修改</title>
</head>
<body class="gray-bg">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>${table.entityComment}修改</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="form_basic.html#">选项1</a>
                                </li>
                                <li><a href="form_basic.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="signupForm"  action="${r"$"}{ctx}/admin/${table.entityNameFirst}/update" method="post">
                            <#list table.tableFields as tableField>
                            <#if (table.primaryKey == tableField.columnName)>
                            <input type="hidden" id="${table.primaryKeyEntityName}" name="${table.primaryKeyEntityName}" value="${r"$"}{${table.entityNameFirst}.${table.primaryKeyEntityName}}" />
                            <#else>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">${tableField.comment}：</label>
                                <div class="col-sm-8">
                                    <input id="${tableField.entityField}" name="${tableField.entityField}" class="form-control" type="text" value="${r"$"}{${table.entityNameFirst}.${tableField.entityField}}">
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> ${tableField.comment}必填</span>
                                </div>
                            </div>                            
                            </#if>                            
                            </#list>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">提交</button>
                                    <input id="cancel_btn" class="btn btn-default" type="button" value="返回" onclick="history.back()" />
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

</body>
</html>