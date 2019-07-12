/* author: 赵正阳 */

$(function () {
    setFormValidation(document.getElementById("invest-form"));
});

// 投资表单验证规则：投资评估要件文件大小不超过16 MB且存在项目技术领域的专家
function investValidate(form, fields, msg) {
    if (form.checkValidity() == false)
        return false;
    else if (!checkFileSize(form["evidence-input"], 16, "投资评估要件证据材料"))
        return false;
    else if (checkExpert(fields) != "success") {
        alert("目前没有该领域的专家，不能投资该项目");
        return false;
    }
    else {
        alert(msg);
        removeExitAlert();
        return true;
    }
}

// 确认删除投资评估要件，隐藏下载/删除按钮并将文件上传设置为必需
function confirmDeleteEvidence(projectId) {
    if (confirm("确认删除？") == true)
        $.ajax({
            type: "GET",
            url: contextPath + "/investor/delete-evidence/" + projectId,
            success: function (response) {
                $("#evidence-div").hide();
                $("#evidence-input").attr("required", "");
            }
        });
}

// 检查指定技术领域的专家是否存在，若存在则返回"success"，否则返回"error"
function checkExpert(fields) {
    var result = "error";
    if (fields) {
        $.ajax({
            type: "POST",
            url: contextPath + "/investor/check-expert",
            data: {fields: fields},
            success: function (response) { result = response },
            async: false
        });
    }
    return result;
}