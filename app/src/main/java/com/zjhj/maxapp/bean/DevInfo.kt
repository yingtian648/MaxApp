package com.zjhj.maxapp.bean

/**
 * CreateTime 2020/4/7 15:48
 * Author LiuShiHua
 * Description：
 */
class DevInfo {
    var isOpenVideoChat: Boolean = false
    var names: Names? = null
    var values: Values? = null
    var emergencyPhone: String = ""
    var deviceId: String? = null
}

class Names {
    val mainCompany: String = ""
    val nextAnnualTime: String = ""
    val regCode: String = ""
    val lastMainTime: String = ""
}


class Values {
    val mainCompany: String = ""
    val nextAnnualTime: String = ""
    val regCode: String = ""
    val lastMainTime: String = ""
}