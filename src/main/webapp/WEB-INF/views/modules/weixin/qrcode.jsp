<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>

<html>
<head>
    <title>二维码</title>
    <script type="text/javascript" src="${ctxStatic}/eqcode/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="${ctxStatic}/eqcode/jquery.qrcode.js"></script>
    <script type="text/javascript" src="${ctxStatic}/eqcode/qrcode.js"></script>
</head>
<body>

<div>
    <div>实际url:>
    <input id="hiddenQr" value="${qrCodeInfo.eqCodeText}" /></div></div>
<div id="qrcode">

</div>
</body>
</html>
<script type="text/javascript">

    $(document).ready( function createQrCode() {
        var codeText = $("#hiddenQr").val();
        if (codeText == "") {
            $('#qrcode').html("暂无数据，请联系管理员");
        } else {
           jQuery('#qrcode').qrcode(codeText);
        }
    });

</script>
