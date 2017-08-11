<#setting number_format="#">
<#import "../res/macro.ftl" as macro>
<script id="_tpl_permission_list_tbody" type="text/x-jquery-tmpl">


{{each(i,v) json}}
    <tr class="list-users">
        <td>@{i}</td>
        <td><span class="label label-warning">@{org_name}</span></td>
        <td>@{login_name}</td>
        <td>@{user_name}</td>
        <td>@{job}</td>
        <td>@{sex}</td>
        <td>@{role_name}</td>
        <td>@{dept_name}</td>

        <td>
            {{if state==0}}
                <button class="label label-error permission_state">启用</button>
            {{else}}
                <button class="label label-success permission_state">禁用</button>
            {{/if}}
        </td>


    </tr>
	 <#--<tr>-->
        <#--<td>-->
            <#--<input type="checkbox">-->
        <#--</td>-->
        <#--<td>@{title}</td>-->
         <#--<td>@{publishState}</td>-->
         <#--<td>@{createTime}</td>-->
        <#--<td>@{viewNum}</td>-->
          <#--<td>@{lastModifyTime}</td>-->
         <#--<td>@{modifyTimes}</td>-->
        <#---->
        <#--<td>@{version}</td>-->
        <#--<td>-->
        	<#--<a href="#blog.blog-edit!bid=@{id}"><img  src="${rc.contextPath}/dev-lib/assets/images/icn_edit.png" /></a>-->
            <#--<a href="" id="blog_del" c-id="@{id}"><img  src="${rc.contextPath}/dev-lib/assets/images/icn_trash.png" /></a>-->
            <#--<a href="" id="blog_preview" c-id="@{id}"><img  src="${rc.contextPath}/dev-lib/assets/images/icn_search.png" /></a>-->
        <#--</td>-->
    <#--</tr>-->
{{/each}}


</script>

<script id="_tpl_permission_pagination" type="text/x-jquery-tmpl">
	 <#--<ul class="fr">-->
	    <#--<li {{if hasPrevious5Page}}onclick="blog.blogListing(@{previousPage})"{{else}}class="disabled"{{/if}}><a href="javascript:void(0);">&lt;&lt;</a></li>-->
	    <#--{{each(i,page) pages}}-->
	    <#--<li {{if page==currPage}}class="active"{{else}}onclick="blog.blogListing(@{page})"{{/if}} ><a href="javascript:void(0);">@{page}</a></li>-->
	    <#--{{/each}}-->
	    <#--<li {{if hasNext5Page}}onclick="blog.blogListing(@{nextPage})"{{else}}class="disabled"{{/if}}><a href="javascript:void(0);">&gt;&gt;</a></li>-->
	    <#--<li class="disabled"><a href="#">总数：@{records}</a></li>-->
    <#--</ul>-->

    <nav aria-label="...">
      <ul class="pager">
        <li class="previous disabled"><a href="#"><span aria-hidden="true">&larr;</span> 上一页</a></li>
        <li class="next"><a href="#">下一页 <span aria-hidden="true">&rarr;</span></a></li>
      </ul>
    </nav>


</script>