<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>自定义表单设计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=path%>/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%=path%>/validation/presetValidators.js"></script>
    <script type="text/javascript" src="<%=path%>/validation/validation.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.4.2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/jquery-easyui-1.2/themes/default/easyui.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-easyui-1.2/jquery.easyui.min.js"></script>
    
	<script type="text/javascript">
       $(function() {
			$("#testarea").ckeditor();
			$("#testarea").ckeditorGet().on("instanceReady", function() {
				if (!window.ckToolbarStyleLoaded) {
				    $('<link type="text/css" rel="stylesheet" href="styles/cktoolbar.css" />').appendTo($("head"));
				    window.ckToolbarStyleLoaded = true;
				}
			});
			$("#testbtn").click(function() {
				$("#testform").submit();
			});
			$("#testform").submit(function() {
				$("#testtext").html($("#testarea").val());
				return false;
			});
		});
    </script>
    <script type="text/javascript">
		function showError() {
			var msg = obj.errorMessage;
			if (msg)
				alert(msg);
		}
		
		function objectToForm() {
			$("#col1").val(obj.columns[0].value);
			$("#col2").val(obj.columns[1].value);
		}
		
		function formToObject() {
		    obj.columns[0].value = $("#col1").val();
		    obj.columns[1].value = $("#col2").val();
		}
	
		function validate() {
			try {
				formToObject();
				doValidation(obj);
			} catch (e) {
				alert(e.message);
				return false;
			}
			var doSubmit = obj.is_errornous ? false : true;
				alert(obj.errorMessage);
			return doSubmit;
		}
		
		$(objectToForm);
		$(showError);
     </script>
    <script type="text/javascript">
$(function() {
	$("#testarea").ckeditor();
	$("#testarea").ckeditorGet().on("instanceReady", function() {
		if (!window.ckToolbarStyleLoaded) {
		    $('<link type="text/css" rel="stylesheet" href="styles/cktoolbar.css" />').appendTo($("head"));
		    window.ckToolbarStyleLoaded = true;
		}
	});
	$("#testbtn").click(function() {
		$("#testform").submit();
	});
	$("#testform").submit(function() {
		$("#testtext").html($("#testarea").val());
		return false;
	});
});
</script>

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
     <script type="text/javascript">
			CKEDITOR.replace('TextArea1');		
     </script>
  </head>
  
  <body>
  <form action="form/create-table.action" method="post" enctype="application/x-www-form-urlencoded">
                  表单中文名称：<input type="text" name="formname"/> 
                  表名(字母) ：      <input type="text" name="tablename"/><br/>
                  表单描述 ：<textarea rows="5" cols="50" name="ms"></textarea>  <br/>  
                 
                  设计表单:
      <textarea id="TextArea1" cols="20" rows="2" class="ckeditor" name="textArea"></textarea> 
      <button name="subtable" type="submit">创建表单</button>
      <button name="validatetable" type="button">表单验证</button>
   </form>    
  </body>
</html>
