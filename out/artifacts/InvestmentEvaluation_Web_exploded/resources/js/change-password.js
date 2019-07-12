/* author: 赵正阳 */

$(function () {
    setFormValidation(document.getElementById("password-form"));
});

// 修改密码表单验证规则：旧密码正确以及两次密码相同
function passwordValidate(form) {
    var result;
    if (form.checkValidity() == false)
        return false;
    result = checkPassword($("#old-password-input").val());
    if (result != "success") {
        alert("旧密码错误");
        return false;
    }
    else if ($("#new-password-input").val() != $("#repeat-password-input").val()) {
        alert("两次密码不一致");
        return false;
    }
    else {
        alert("修改密码成功");
        removeExitAlert();
        return true;
    }
}

// 检查旧密码是否正确，若正确则返回"success"，否则返回"error"
function checkPassword(password) {
    var result = "error";
    if (password)
        $.ajax({
            type: "POST",
            url: contextPath + "/check-password",
            data: {password: password},
            success: function (response) { result = response },
            async: false
        });
    return result;
}