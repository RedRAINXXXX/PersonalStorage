/* author: 赵正阳 */

// 自定义表单验证样式
// https://mdbootstrap.com/docs/jquery/forms/validation/
function setFormValidation(form) {
    form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        }
        form.classList.add('was-validated');
    }, false);
}

// 设置离开页面提醒
function setExitAlert() {
    window.onbeforeunload = function (event) {
        event = event || window.event;
        if (event)
            event.returnValue = "确定离开当前页面？";
        return "确定离开当前页面？";
    }
}

// 取消页面离开提醒
function removeExitAlert() {
    window.onbeforeunload = null;
}

// 表单验证
// 当表单验证成功时提示信息，取消页面离开提醒，返回true；否则返回false
function alertValidate(form, msg) {
    if (form.checkValidity() == false)
        return false;
    else {
        alert(msg);
        removeExitAlert();
        return true;
    }
}

// 返回上传的文件大小（字节）
function getFileSize(fileInput) {
    if (fileInput.value == "")
        return 0;
    if (/msie/i.test(navigator.userAgent)) {
        // IE浏览器
        var fileSystemObject = new ActiveXObject("Scripting.FileSystemObject");
        var fileObject = fileSystemObject.getFile(fileInput.value);
        return fileObject.size;
    }
    else
        return fileInput.files[0].size;
}