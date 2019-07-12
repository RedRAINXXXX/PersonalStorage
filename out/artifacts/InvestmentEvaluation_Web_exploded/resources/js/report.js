/* author: 赵正阳 */

$(function () {
    setFormValidation(document.getElementById("report-form"));
    setExitAlert();
});

// 项目评价报告表单验证规则：评价报告大小不超过16 MB
function reportValidate(form) {
    if (form.checkValidity() == false)
        return false;
    else if (!checkReportFileSize()) {
        alert("评价报告大小不能超过16 MB");
        return false;
    }
    else {
        alert("上传成功");
        removeExitAlert();
        return true;
    }
}

// 检查评价报告大小是否小于等于上限：16 MB
function checkReportFileSize() {
    return getFileSize(document.getElementById("report-input")) <= 16 * 1024 * 1024;
}

// 根据项目id删除评价报告，并隐藏相关文字
function confirmDeleteReport(projectId, investorId) {
    if (confirm("确认删除评价报告？") == true)
        $.ajax({
            type: "GET",
            url: contextPath + "/admin/report/delete?projectId=" + projectId + "&investorId=" + investorId,
            success: function (response) {
                $("#report-div").hide();
            }
        });
}