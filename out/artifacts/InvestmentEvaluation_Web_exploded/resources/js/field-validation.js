/* author: 赵正阳 */

// 专家个人信息、投资方详细信息表单验证规则：选择的技术领域在1~5个之间
function fieldValidate(form, msg) {
    if (form.checkValidity() == false)
        return false;
    else {
        var fieldCount = getNumberOfSelectedFields(form["fields-select"]);
        if (fieldCount < 1) {
            alert("至少选择1个领域");
            return false;
        }
        else if (fieldCount > 5) {
            alert("至多选择5个领域");
            return false;
        }
        else {
            alert(msg);
            removeExitAlert();
            return true;
        }
    }
}