	<div class="panel-heading">
		信息修改
	</div>
	<form method="post" id="toPage" action="${r'$'}{contextPath}/${tableInfo.tableName?uncap_first}/${r'$'}{data.${tableInfo.javaColmBeans[0].javaName} == null ? 'insert':'edit' }">
	<div class="panel-body">
		<div class="col-lg-12 col-md-12 col-xs-12" id="inlinefirst">
			<div class="formgroup">
			<#list tableInfo.javaColmBeans as bean>
				<div class="${bean.javaName}">
				<label>${bean.comment}</label><label><input type="text" id="${bean.javaName}" name="${bean.javaName}" value="${r'${data'}.${bean.javaName}}" placeholder="" maxlength="${bean.dataLength}" /></label><i id="${bean.javaName}_txt"></i></div>
			</#list>
				<div class="submit"><input type="submit" value="提交" id="submit" style="background: #EA5529;" /></div>
			</div>
		</div>
	</div>
	</form>
					