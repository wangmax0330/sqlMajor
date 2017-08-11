<#setting number_format="#">
    <!doctype html>
    <html>

    <head>
        <meta charset="utf-8" />
            <#import "../res/macro.ftl" as calendarMacro>
                <style>
                .error {
                    padding: 3px 0 3px 20px;
                    width: 210px;
                    display: none;
                    color: red
                }
                
                .loading {
                    color: #999999;
                    display: none;
                    font-size: 12px;
                    margin-left: 10px;
                }
                </style>
                <link rel="stylesheet" href="${rc.contextPath}/dev-lib/assets/js/kindeditor-4.1.10/themes/default/default.css" />
				<script charset="utf-8" src="${rc.contextPath}/dev-lib/assets/js/kindeditor-4.1.10/kindeditor-min.js"></script>
				<script charset="utf-8" src="${rc.contextPath}/dev-lib/assets/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
		
                <link rel="stylesheet" href="${rc.contextPath}/dev-lib/assets/js/dataSelector/dataSelector.css" type="text/css" />
                <script src="${rc.contextPath}/dev-lib/assets/js/dataSelector/dataSelector.js"></script>
                
                 <link rel="stylesheet" href="${rc.contextPath}/dev-lib/assets/js/uploadify/css/uploadify.css" type="text/css" />
                <script type="text/javascript" src="${rc.contextPath}/dev-lib/assets/js/uploadify/swfobject.js"></script>
				<script type="text/javascript" src="${rc.contextPath}/dev-lib/assets/js/uploadify/jquery.uploadify-3.1.min.js"></script>
    			<script type="text/javascript" src="${rc.contextPath}/res/plugin/blog/js/blog-edit.js"></script>
    </head>

    <body>
        <div class="container-fluid">
            <form class="" id="form-blog-edit">
            	<input type="hidden" name="id" value="<#if blogDomain.id??>${blogDomain.id!''}<#else>0</#if>"/>
                <div class="form-group">
                    <label class="control-label" for="inputSuccess1">标题</label>
                    <input type="text" name="title" class="form-control"  id="inputSuccess1" style="width:70%" value="<#if blogDomain??>${blogDomain.title!''}</#if>">
                	<span class="error">信息不能为空</span>
                </div>
                
                <div class="form-group">
                    <label for="blog_img_uploadify">临时上传图片</label>
	 				<input type="file" name="pic" id="blog_img_uploadify" style="width:60px;height:30px;" value="上传" class="btn">
	 				<input type="hidden" name="pic"  value="">
	 				<label class="error">图片上传失败</label>	
				    <p class="help-block">编辑博客填图片地址的时候,这里上传可以获取 [需要链接图片的尽量不要填外站的图片地址]</p>
				    <label for="">图片地址</label>
				    <p id="pic-address" style="font-size:22px">
				     
				    </p>
                	<a href="javascript:void(0)" target="_blank">
	 					<img class="blog-detail-pic-img" style="max-width:120px;max-height:120px;vertical-align:middle;" src=""/>
	 				</a>
                </div>
                
                <div class="form-group has-warning">
                    <label class="control-label" for="blog_assort">选择标签</label>
                   <#if blogDomain.tags??>
	 					<@calendarMacro.dataSelector single=false inputId="tagsId" initIds=blogDomain.tags title="选择新的标签" initVals=blogDomain.tags action="/d/tag/list"/>
	 				<#else>
	 					<@calendarMacro.dataSelector single=false inputId="tagsId" initIds=""  title="选择标签" initVals="" action="/d/tag/list"/>
	 				</#if>
                    <span  class="">尽量填选[展示不支持选择标签功能]</span>
                </div>
                <div class="form-group">
                    <div>
                       <textarea name="content" style="width:100%;height:400px;visibility:hidden;">
                           	 <#if blogDomain.content??>
                           	  	${blogDomain.content!''}
                           	 <#else>
                           	  	 <h1>Hello world</h1>
                           	  	 <br />
	                             <p>[提醒(保存时请把我删除):]  这个下划线请不要删除,下划线上面的内容会被截取作文章摘要,不影响保存</p>
	                             <hr />
	                             <p>...</p>
                           	 </#if>
                       </textarea>
                    </div>
                </div>
                <input type="button" id="submit_save" class="btn btn-primary" value="保存">
                <span class='loading'><img src="${rc.contextPath}/dev-lib/assets/images/reflesh.gif"/>正在保存...</span>
                <input type='button' id="submit_publish" class="btn btn-default" value="发布">
                <span class='loading'><img src="${rc.contextPath}/dev-lib/assets/images/reflesh.gif"/>正在保存...</span>
            	<input type="button" name="previewHtml" class="btn btn-default" value="预览" />
            	<input type="button" name="showHtml"class="btn btn-default" value="取得HTML" />
            	<hr />
            	<div id="preview_blog">
            	
            	<div>
            </form>
        </div>
    </body>

    </html>
