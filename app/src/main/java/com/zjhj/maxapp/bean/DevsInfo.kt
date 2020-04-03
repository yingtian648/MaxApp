package com.zjhj.maxapp.bean

/**
 * CreateTime 2020/4/2 09:52
 * Author LiuShiHua
 * Description：
 * 主构造函数传参为空，次构造函数传递了所有值
 */
class DevsInfo() {
    var evOrder: String? = null
    var regCode: String? = null
    var lastMT: String? = null
    var nextAT: String? = null
    var helpPhone: String? = null

    constructor(evOrder: String, regCode: String, lastMT: String, nextAT: String, helpPhone: String) : this() {
        this.evOrder = evOrder
        this.regCode = regCode
        this.lastMT = lastMT
        this.nextAT = nextAT
        this.helpPhone = helpPhone
    }

}