/* author: 赵正阳 */

$(function () {
    setFormValidation(document.getElementById("evaluate-form"));

    $("#evaluate-table input").change(function () {
        $("#total-input").val(calcTotalScore().toFixed(2));
    });
});

// 计算项目总得分：所有指标得分的加权平均
function calcTotalScore() {
    var sum = 0;
    var inputCollection = document.getElementById("evaluate-table").getElementsByTagName("input");
    for (var i = 0; i < inputCollection.length; ++i)
        sum += Number(inputCollection[i].value) * Number(inputCollection[i].nextElementSibling.innerText);
    return sum;
}

function confirmSubmitEvaluation(form) {
    if (form.checkValidity() == false)
        return false;
    else if (confirm("提交后将无法修改，确认提交评价？") == false)
        return false;
    else {
        alert("提交评价成功");
        removeExitAlert();
        return true;
    }
}