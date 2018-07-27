<form method="post" id="toPage" action="${r'${contextPath}'}/redirct/toPage">
	<input type="hidden" name="pageStr" value="/htglxt/pages/${tableInfo.tableName}Edit">
	<input type="submit" value="新增">
</form>
<form method="post" id="postForm" action="<%=request.getContextPath() %>/${tableInfo.tableName?uncap_first}/queryList">
<#list tableInfo.javaColmBeans as bean>
${bean.comment}:<input type="text" name="${bean.javaName}" value= "${r'${data.'}${bean.javaName} }">
</#list>
<input type="submit" value="查询"><br/><br/>
<table id="table" data-toggle="table">
	<thead style="background: rgba(228,228,228,1);">
		<tr>
		<#list tableInfo.javaColmBeans as bean>
			<th data-field="${bean.javaName}">${bean.comment}</th>
		</#list>
			<th data-field="caozuo">操作</th>
		</tr>
	</thead>
	<c:forEach items="${r'${data.data}'}" var="oneRec">
		<tr data_post="${r'${oneRec'}.${tableInfo.javaColmBeans[0].javaName} }">
		<#list tableInfo.javaColmBeans as bean>
			<td>
			<#if bean.typeName = "Date">
				<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${r'${oneRec'}.${bean.javaName}}" />
				<#else>
				${r'${oneRec'}.${bean.javaName}}
			</#if>
			</td>
		</#list>
			<td>
				<a href="javaScript:void(0);" class="js_edit" onclick="getOne('${r'$'}{oneRec.${tableInfo.javaColmBeans[0].javaName}}')">编辑</a>&nbsp;
				<a href="javaScript:void(0);" onclick="deleteOne(${r'${oneRec'}.${tableInfo.javaColmBeans[0].javaName} })">删除</a>&nbsp;
			</td>
		</tr>
	</c:forEach>
</table>
<jsp:include page="common/page.jsp"></jsp:include>										
</form>										

