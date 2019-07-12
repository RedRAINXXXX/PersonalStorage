/* author: 赵正阳 */

// 根据输入的查询条件筛选项目
function queryProject() {
    var title = $("#title-input").val();
    var fields = $("#fields-select").val();
    var techStage = $("#techStage-select").val();
    var industry = $("#industry-input").val();
    var industryStage = $("#industryStage-select").val();
    $("#project-table tbody tr").each(function () {
        if ((title == "" || $(this).children("td:first-child").text() == title)
            && (fields == "" || $(this).find("td:nth-child(6) p:nth-child(1)").text().split(",").indexOf(fields) >= 0)
            && (techStage == "" || $(this).find("td:nth-child(6) p:nth-child(2)").text() == techStage)
            && (industry == "" || $(this).find("td:nth-child(6) p:nth-child(3)").text() == industry)
            && (industryStage == "" || $(this).find("td:nth-child(6) p:nth-child(4)").text() == industryStage))
            $(this).show();
        else
            $(this).hide();
    });
}