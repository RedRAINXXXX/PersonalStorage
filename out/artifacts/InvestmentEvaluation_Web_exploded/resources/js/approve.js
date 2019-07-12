/* author: 赵正阳 */

$(function () {
    setFormValidation(document.getElementById("approve-form"));
    setExitAlert();
});

function approveValidation(form) {
    if (form.checkValidity() == false)
        return false;
    else if (document.getElementById("role-admin-radio").checked) {
        if (confirm("确认将该用户设置为管理员？") == true) {
            alert("审核提交成功");
            removeExitAlert();
            return true;
        }
        else
            return false;
    }
    else {
        alert("审核提交成功");
        removeExitAlert();
        return true;
    }
}