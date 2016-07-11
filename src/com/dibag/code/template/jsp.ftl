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
<title>${table.entityComment}列表</title>
</head>
<body class="gray-bg">
	<div class="col-sm-12">
		<div class="alert alert-success" role="alert">事件结果</div>
		<div class="btn-group hidden-xs ext-bottom-1" role="group">
			<a class="btn btn-outline btn-default" href="${r"$"}{ctx}/admin/${table.entityNameFirst}/create">
				<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
			</a>
			<a class="btn btn-outline btn-default">
				<i class="glyphicon glyphicon-heart" aria-hidden="true"></i>
			</a>
			<a class="btn btn-outline btn-default">
				<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
			</a>
		</div>
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" class="check-all"></th>
					<#list table.tableFields as tableField>
					<th scope="col" data-column="${tableField.entityField}">${tableField.comment}</th>
					</#list>
					<th scope="col">操作</th>
				</tr>
			</thead>
			<tbody>		
				<c:forEach items="${r"$"}{list}" var="item">
					<tr>
					<td><input type="checkbox" class="check-all-item" value="${r"$"}{item.${table.primaryKeyEntityName}}"></td>
					<#list table.tableFields as tableField>
					<#if (tableField.entityType == "Date")>
					<td><fmt:formatDate value="${r"$"}{item.${tableField.entityField}}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<#else>
					<td>${r"$"}{item.${tableField.entityField}}</td>
					</#if>
					</#list>
					<td>
					<a class="btn btn-sm yellow btn-outline" href="${r"$"}{ctx}/admin/${table.entityNameFirst}/detail/${r"$"}{item.${table.primaryKeyEntityName}}">编辑</a>
					<a class="btn btn-sm red btn-outline" href="${r"$"}{ctx}/admin/${table.entityNameFirst}/delete/${r"$"}{item.${table.primaryKeyEntityName}}">删除</a>
					</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<tg:pagination searchFormId="searchForm" paginator="${r"$"}{paginator}"></tg:pagination>
	</div>
</body>
</html>