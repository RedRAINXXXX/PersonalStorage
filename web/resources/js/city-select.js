/* author: 赵正阳 */

COUNTRY_LIST = ["中国", "其他"];
PROVINCE_CITY_LIST = [
    ["北京市", ["东城", "西城", "崇文", "宣武", "朝阳", "丰台", "石景山", "海淀", "门头沟", "房山", "通州", "顺义", "昌平", "大兴", "平谷", "怀柔", "密云", "延庆"]],
    ["上海市", ["黄浦", "卢湾", "徐汇", "长宁", "静安", "普陀", "闸北", "虹口", "杨浦", "闵行", "宝山", "嘉定", "浦东", "金山", "松江", "青浦", "南汇", "奉贤", "崇明"]],
    ["天津市", ["和平", "东丽", "河东", "西青", "河西", "津南", "南开", "北辰", "河北", "武清", "红挢", "塘沽", "汉沽", "大港", "宁河", "静海", "宝坻", "蓟县"]],
    ["重庆市", ["万州", "涪陵", "渝中", "大渡口", "江北", "沙坪坝", "九龙坡", "南岸", "北碚", "万盛", "双挢", "渝北", "巴南", "黔江", "长寿", "綦江", "潼南", "铜梁", "大足", "荣昌", "壁山", "梁平", "城口", "丰都", "垫江", "武隆", "忠县", "开县", "云阳", "奉节", "巫山", "巫溪", "石柱", "秀山", "酉阳", "彭水", "江津", "合川", "永川", "南川"]],
    ["河北省", ["石家庄", "邯郸", "邢台", "保定", "张家口", "承德", "廊坊", "唐山", "秦皇岛", "沧州", "衡水"]],
    ["山西省", ["太原", "大同", "阳泉", "长治", "晋城", "朔州", "吕梁", "忻州", "晋中", "临汾", "运城"]],
    ["内蒙古自治区", ["呼和浩特", "包头", "乌海", "赤峰", "呼伦贝尔盟", "阿拉善盟", "哲里木盟", "兴安盟", "乌兰察布盟", "锡林郭勒盟", "巴彦淖尔盟", "伊克昭盟"]],
    ["辽宁省", ["沈阳", "大连", "鞍山", "抚顺", "本溪", "丹东", "锦州", "营口", "阜新", "辽阳", "盘锦", "铁岭", "朝阳", "葫芦岛"]],
    ["吉林省", ["长春", "吉林", "四平", "辽源", "通化", "白山", "松原", "白城", "延边"]],
    ["黑龙江省", ["哈尔滨", "齐齐哈尔", "牡丹江", "佳木斯", "大庆", "绥化", "鹤岗", "鸡西", "黑河", "双鸭山", "伊春", "七台河", "大兴安岭"]],
    ["江苏省", ["南京", "镇江", "苏州", "南通", "扬州", "盐城", "徐州", "连云港", "常州", "无锡", "宿迁", "泰州", "淮安"]],
    ["浙江省", ["杭州", "宁波", "温州", "嘉兴", "湖州", "绍兴", "金华", "衢州", "舟山", "台州", "丽水"]],
    ["安徽省", ["合肥", "芜湖", "蚌埠", "马鞍山", "淮北", "铜陵", "安庆", "黄山", "滁州", "宿州", "池州", "淮南", "巢湖", "阜阳", "六安", "宣城", "亳州"]],
    ["福建省", ["福州", "厦门", "莆田", "三明", "泉州", "漳州", "南平", "龙岩", "宁德"]],
    ["江西省", ["南昌市", "景德镇", "九江", "鹰潭", "萍乡", "新馀", "赣州", "吉安", "宜春", "抚州", "上饶"]],
    ["山东省", ["济南", "青岛", "淄博", "枣庄", "东营", "烟台", "潍坊", "济宁", "泰安", "威海", "日照", "莱芜", "临沂", "德州", "聊城", "滨州", "菏泽"]],
    ["河南省", ["郑州", "开封", "洛阳", "平顶山", "安阳", "鹤壁", "新乡", "焦作", "濮阳", "许昌", "漯河", "三门峡", "南阳", "商丘", "信阳", "周口", "驻马店", "济源"]],
    ["湖北省", ["武汉", "宜昌", "荆州", "襄樊", "黄石", "荆门", "黄冈", "十堰", "恩施", "潜江", "天门", "仙桃", "随州", "咸宁", "孝感", "鄂州"]],
    ["湖南省", ["长沙", "常德", "株洲", "湘潭", "衡阳", "岳阳", "邵阳", "益阳", "娄底", "怀化", "郴州", "永州", "湘西", "张家界"]],
    ["广东省", ["广州", "深圳", "珠海", "汕头", "东莞", "中山", "佛山", "韶关", "江门", "湛江", "茂名", "肇庆", "惠州", "梅州", "汕尾", "河源", "阳江", "清远", "潮州", "揭阳", "云浮"]],
    ["广西壮族自治区", ["南宁", "柳州", "桂林", "梧州", "北海", "防城港", "钦州", "贵港", "玉林", "南宁地区", "柳州地区", "贺州", "百色", "河池"]],
    ["海南省", ["海口", "三亚"]],
    ["四川省", ["成都", "绵阳", "德阳", "自贡", "攀枝花", "广元", "内江", "乐山", "南充", "宜宾", "广安", "达川", "雅安", "眉山", "甘孜", "凉山", "泸州"]],
    ["贵州省", ["贵阳", "六盘水", "遵义", "安顺", "铜仁", "黔西南", "毕节", "黔东南", "黔南"]],
    ["云南省", ["昆明", "大理", "曲靖", "玉溪", "昭通", "楚雄", "红河", "文山", "思茅", "西双版纳", "保山", "德宏", "丽江", "怒江", "迪庆", "临沧"]],
    ["西藏自治区", ["拉萨", "日喀则", "山南", "林芝", "昌都", "阿里", "那曲"]],
    ["陕西省", ["西安", "宝鸡", "咸阳", "铜川", "渭南", "延安", "榆林", "汉中", "安康", "商洛"]],
    ["甘肃省", ["兰州", "嘉峪关", "金昌", "白银", "天水", "酒泉", "张掖", "武威", "定西", "陇南", "平凉", "庆阳", "临夏", "甘南"]],
    ["宁夏回族自治区", ["银川", "石嘴山", "吴忠", "固原"]],
    ["青海省", ["西宁", "海东", "海南", "海北", "黄南", "玉树", "果洛", "海西"]],
    ["新疆维吾尔族自治区", ["乌鲁木齐", "石河子", "克拉玛依", "伊犁", "巴音郭勒", "昌吉", "克孜勒苏柯尔克孜", "博尔塔拉", "吐鲁番", "哈密", "喀什", "和田", "阿克苏"]],
    ["香港特别行政区", ["香港特别行政区"]],
    ["澳门特别行政区", ["澳门特别行政区"]],
    ["台湾省", ["台北", "高雄", "台中", "台南", "屏东", "南投", "云林", "新竹", "彰化", "苗栗", "嘉义", "花莲", "桃园", "宜兰", "基隆", "台东", "金门", "马祖", "澎湖"]]
];

// 设置国家下拉列表的选项，并将countryName对应的选项设置为已选择
function setCountryOptions(countrySelect, countryName) {
    var countryIndex = COUNTRY_LIST.indexOf(countryName);
    countrySelect.innerHTML = "";
    for (var i = 0; i < COUNTRY_LIST.length; ++i) {
        var optionElement = document.createElement("option");
        if (i == countryIndex)
            optionElement.setAttribute("selected", "");
        optionElement.appendChild(document.createTextNode(COUNTRY_LIST[i]));
        countrySelect.appendChild(optionElement);
    }
}

// 查找省份名称对应的下标，若未找到则返回-1
function indexOfProvince(name) {
    for (var i = 0; i < PROVINCE_CITY_LIST.length; ++i)
        if (PROVINCE_CITY_LIST[i][0] == name)
            return i;
    return -1;
}

// 设置省份下拉列表的选项，并将provinceName对应的选项设置为已选择
function setProvinceOptions(provinceSelect, provinceName) {
    var index = indexOfProvince(provinceName);
    provinceSelect.innerHTML = "";
    for (var i = 0; i < PROVINCE_CITY_LIST.length; ++i) {
        var optionElement = document.createElement("option");
        if (i == index)
            optionElement.setAttribute("selected", "");
        optionElement.appendChild(document.createTextNode(PROVINCE_CITY_LIST[i][0]));
        provinceSelect.appendChild(optionElement);
    }
    return index;
}

// 根据省份序号设置城市下拉列表的选项，并将cityName对应的选项设置为已选择
// 若省份序号越界则默认为0，若城市名不存在则默认为第一个城市
function setCityOptions(citySelect, provinceIndex, cityName) {
    if (provinceIndex < 0 || provinceIndex >= PROVINCE_CITY_LIST.length)
        provinceIndex = 0;
    var cityIndex = PROVINCE_CITY_LIST[provinceIndex][1].indexOf(cityName);
    citySelect.innerHTML = "";
    for (var i = 0; i < PROVINCE_CITY_LIST[provinceIndex][1].length; ++i) {
        var optionElement = document.createElement("option");
        if (i == cityIndex)
            optionElement.setAttribute("selected", "");
        optionElement.appendChild(document.createTextNode(PROVINCE_CITY_LIST[provinceIndex][1][i]));
        citySelect.appendChild(optionElement);
    }
}

// 设置国家下拉列表的改变事件：当选择“中国”时显示省份下拉列表、城市下拉列表和邮政编码输入框，当选择“其他”时隐藏
function setCountryOnchange(countrySelect, provinceSelect, citySelect, zipInput) {
    countrySelect.onchange = function () {
        if (this.value == "中国") {
            setProvinceCityZipDisplay(provinceSelect, citySelect, zipInput, true);
            setProvinceOptions(provinceSelect, "");
            setCityOptions(citySelect, -1, "");
        }
        else {
            setProvinceCityZipDisplay(provinceSelect, citySelect, zipInput, false);
            provinceSelect.value = "";
            citySelect.value = "";
            zipInput.value = "";
        }
    };
}

// 设置省份下拉列表的改变事件：将城市下拉列表的选项设置为对应的城市列表
function setProvinceOnchange(provinceSelect, citySelect) {
    provinceSelect.onchange = function() {
        setCityOptions(citySelect, indexOfProvince(this.value), "");
    };
}

// 显示或隐藏省份下拉列表、城市下拉列表和邮政编码输入框及对应的标签
function setProvinceCityZipDisplay(provinceSelect, citySelect, zipInput, show) {
    if (show) {
        provinceSelect.style.display = "";
        $("label[for='" + provinceSelect.id + "']").show();
        citySelect.style.display = "";
        $("label[for='" + citySelect.id + "']").show();
        zipInput.style.display = "";
        $("label[for='" + zipInput.id + "']").show();
    }
    else {
        provinceSelect.style.display = "none";
        $("label[for='" + provinceSelect.id + "']").hide();
        citySelect.style.display = "none";
        $("label[for='" + citySelect.id + "']").hide();
        zipInput.style.display = "none";
        $("label[for='" + zipInput.id + "']").hide();
    }
}

// 将下拉列表选项中与给定值相等的设置为已选择
function setOptionSelected(select, value) {
    var options = select.getElementsByTagName("option");
    for (var i = 0; i < options.length; ++i)
        if (options[i].innerText == value) {
            options[i].setAttribute("selected", "");
            break;
        }
}