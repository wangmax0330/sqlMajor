<#setting number_format="#">
<#import "../res/macro.ftl" as macro>
<script id="_tpl_customer_list_tbody" type="text/x-jquery-tmpl">
{{each(i,v) json}}
    <tr class="list-users">
        <td>@{i}</td>
        <td><span class="label label-warning">@{customer_mobile}</span></td>
        <td>@{company}</td>
        <td>@{customer_name}</td>
        <td>@{customer_code}</td>
        <td>@{sex}</td>
        <td><span class="label label-success">@{customer_status}</span></td>
        <td><span class="label label-success">@{project_status}</span></td>
        <td>@{customer_create_date}</td>
        <td>@{customer_first_date}</td>
        <td>@{building_name}</td>
        <td>@{door_number}</td>
        <td>@{structure_area}</td>
        <td>@{inner_area}</td>

        <td>@{seller_name}</td>
        <td>@{seller_role}</td>
        <td>@{seller_dept}</td>

        <td>@{not_seller_name}</td>
        <td>@{not_seller_role}</td>
        <td>@{not_seller_dept}</td>

        <td>@{designer_name}</td>
        <td>@{designer_role}</td>
        <td>@{designer_dept}</td>

        <td>
            <div class="btn-group">
                <a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">Actions <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#"><i class="icon-pencil"></i> Edit</a></li>
                    <li><a href="#"><i class="icon-trash"></i> Delete</a></li>
                    <li><a href="#"><i class="icon-user"></i> Details</a></li>
                    <li class="nav-header">Permissions</li>
                    <li><a href="#"><i class="icon-lock"></i> Make <strong>Admin</strong></a></li>
                    <li><a href="#"><i class="icon-lock"></i> Make <strong>Moderator</strong></a></li>
                </ul>
            </div>
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

<script id="_tpl_customer_pagination" type="text/x-jquery-tmpl">
	 <#--<ul class="fr">-->
	    <#--<li {{if hasPrevious5Page}}onclick="blog.blogListing(@{previousPage})"{{else}}class="disabled"{{/if}}><a href="javascript:void(0);">&lt;&lt;</a></li>-->
	    <#--{{each(i,page) pages}}-->
	    <#--<li {{if page==currPage}}class="active"{{else}}onclick="blog.blogListing(@{page})"{{/if}} ><a href="javascript:void(0);">@{page}</a></li>-->
	    <#--{{/each}}-->
	    <#--<li {{if hasNext5Page}}onclick="blog.blogListing(@{nextPage})"{{else}}class="disabled"{{/if}}><a href="javascript:void(0);">&gt;&gt;</a></li>-->
	    <#--<li class="disabled"><a href="#">总数：@{records}</a></li>-->
    <#--</ul>-->

     <ul>
        <li><a href="#">Prev</a></li>
        <li class="active">
            <a href="#">1</a>
        </li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">Next</a></li>
    </ul>
</script>