/* author: 赵正阳 */

$(function () {
    setFormValidation(document.getElementById("login-form"));
});

// 登录表单验证规则：用户名和密码正确
function loginValidate(form) {
    var result;
    if (form.checkValidity() == false)
        return false;
    result = checkLogin($("#username-input").val(), $("#password-input").val());
    if (result == "success")
        return true;
    else if (result == "error") {
        alert("用户名或密码错误");
        return false;
    }
    else if (result == "unapproved") {
        alert("审核尚未通过，请耐心等待");
        return false;
    }
    else {
        alert("未知错误：" + result);
        return false;
    }
}

// 检查用户名和密码是否正确，若正确则返回"success"，否则返回"error"
function checkLogin(username, password) {
    var result = "error";
    if (username && password)
        $.ajax({
            type: "POST",
            url: contextPath + "/check-login",
            data: {
                username: username,
                password: password
            },
            success: function (response) { result = response },
            async: false
        });
    return result;
}