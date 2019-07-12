/* author: 赵正阳 */

// 将技术领域下拉列表中对应的领域设置为已选择
function setFieldOptionSelected(fieldsSelect, fields) {
    var fieldList = fields.split(",");
    for (var i = 0; i < fieldsSelect.options.length; ++i)
        if (fieldList.indexOf(fieldsSelect.options[i].value) >= 0)
            fieldsSelect.options[i].selected = true;
}

// 获取技术领域下拉列表中已选择项的个数
function getNumberOfSelectedFields(fieldsSelect) {
    var count = 0;
    for (var i = 0; i < fieldsSelect.options.length; ++i)
        if (fieldsSelect.options[i].selected)
            ++count;
    return count;
}

// 获取技术领域下拉列表中已选择的项，用","连接
function getSelectedFields(fieldsSelect) {
    var fieldList = [];
    for (var i = 0; i < fieldsSelect.options.length; ++i)
        if (fieldsSelect.options[i].selected)
            fieldList.push(fieldsSelect.options[i].value);
    return fieldList.join();
}