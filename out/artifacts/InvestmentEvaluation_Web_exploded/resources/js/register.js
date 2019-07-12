/* author: 赵正阳 */

$(function () {
    setFormValidation(document.getElementById("register-form"));
    setExitAlert();
});

// 注册表单验证规则：两次密码相同以及用户名不存在
function registerValidate(form) {
    if (form.checkValidity() == false)
        return false;
    else if ($("#password-input").val() != $("#repeat-password-input").val()) {
        alert("两次密码不一致");
        return false;
    }
    else if (checkUsername($("#username-input").val()) != "success") {
        alert("用户名已存在");
        return false;
    }
    else {
        alert("注册信息提交成功，请等待管理员审核");
        removeExitAlert();
        return true;
    }
}

// 检查用户名是否已存在，若已存在则返回"error"，否则返回"success"
function checkUsername(username) {
    var result = "success";
    if (username)
        $.ajax({
            type: "GET",
            url: contextPath + "/check-username/" + username,
            success: function (response) { result = response },
            async: false
        });
    return result;
}