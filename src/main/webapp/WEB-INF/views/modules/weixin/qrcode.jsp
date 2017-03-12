<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>

<html>
<head>
    <title>二维码</title>
    <script type="text/javascript" src="${ctxStatic}/eqcode/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="${ctxStatic}/eqcode/jquery.qrcode.js"></script>
    <style type="text/css">
        img {
            width: 100%;
        }
    </style>
</head>
<body>

<div>
    <input id="hiddenQr" value="${qrCodeInfo.eqCodeText}" type="hidden"/>
</div>

<img id="qrcode" src="${qrCodeInfo.imgPath}">
<div id="remind">

</div>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function createQrCode() {
        var codeText = $("#hiddenQr").val();
        if (remind == "") {
            $('#remind').html("暂无数据，请联系管理员");
            $('#qrcode').hide();
        }
    });

</script>
