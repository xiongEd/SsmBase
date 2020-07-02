<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传页面</title>
</head>
<body>
<form method="post" action="file/upload" enctype="multipart/form-data">
    <p><span>文件：</span>
        <input type="file" name="file">
    </p>

    <p><input type="submit" value="提交"></p>
</form>
</body>
</html>