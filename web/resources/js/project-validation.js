/* author: 赵正阳 */

// 项目详细信息表单验证规则：项目成果及产业化前期信息表文件大小不超过16 MB，选择的技术领域在1~5个之间
function projectValidate(form, msg) {
    if (form.checkValidity() == false)
        return false;
    else if (!checkFileSize(form["industrial-input"], 16, "项目成果及产业化前期信息表"))
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

// 确认删除产业化信息表，并隐藏下载/删除按钮
function confirmDeleteIndustrial(projectId) {
    if (confirm("确认删除？") == true)
        $.ajax({
            type: "GET",
            url: contextPath + "/admin/project/delete-industrial/" + projectId,
            success: function (response) {
                $("#industrial-div").hide();
            }
        });
}