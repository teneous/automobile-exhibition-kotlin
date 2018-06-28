package ecommerce.customer.databean


/**
 * Created by syoka on 2018/6/26.
 */
class CrProductDto {
    var sequenceNo:String= ""
    var productId:String=""
    var productName:String = ""
    var productNumber:Int = 0
    var productImg:String = ""
    var productDescr:String =""

    constructor()

    constructor(productName:String,productImg:String):this(){
        this.productName = productName
        this.productImg = productImg
    }

    constructor(productName:String,productImg:String,sequenceNo:String):this(productName,productImg){
        this.sequenceNo = sequenceNo
    }

    constructor(productName:String,productImg:String,
                productNumber:Int,productDescr:String):this(productName,productImg){
        this.productNumber = productNumber
        this.productDescr = productDescr
    }
}