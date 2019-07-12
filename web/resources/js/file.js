/* author: 赵正阳 */

// 检查文件大小是否超过指定大小（MB）
function checkFileSize(fileInput, size, type) {
    if (getFileSize(fileInput) > size * 1024 * 1024) {
        alert(type + "文件大小不能超过" + size + " MB");
        return false;
    }
    else
        return true;
}