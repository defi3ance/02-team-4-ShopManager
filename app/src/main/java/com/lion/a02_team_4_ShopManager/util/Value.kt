package com.lion.a02_team_4_ShopManager.util

class Value {
}

enum class ShopGrade(val number:Int, val str:String){
    SHOP_GRADE_1(1, "1학년"),
    SHOP_GRADE_2(2, "2학년"),
    SHOP_GRADE_3(3, "3학년"),
}

enum class ShopType(val number:Int, val str:String){
    SHOP_TYPE_BASKETBALL(1, "농구부"),
    SHOP_TYPE_SOCCER(2, "축구부"),
    SHOP_TYPE_BASEBALL(3, "야구부"),
}

enum class ShopGender(val number:Int, val str:String){
    SHOP_GENDER_MALE(1, "남자"),
    SHOP_GENDER_FEMALE(2, "여자"),
}

fun numberToShopGrade(shopGrade:Int) = when(shopGrade){
    1 -> ShopGrade.SHOP_GRADE_1
    2 -> ShopGrade.SHOP_GRADE_2
    else -> ShopGrade.SHOP_GRADE_3
}

fun numberToShopType(shopType:Int) = when(shopType){
    1 -> ShopType.SHOP_TYPE_BASKETBALL
    2 -> ShopType.SHOP_TYPE_SOCCER
    else -> ShopType.SHOP_TYPE_BASEBALL
}

fun numberToShopGender(shopGender:Int) = when(shopGender){
    1 -> ShopGender.SHOP_GENDER_MALE
    else -> ShopGender.SHOP_GENDER_FEMALE
}