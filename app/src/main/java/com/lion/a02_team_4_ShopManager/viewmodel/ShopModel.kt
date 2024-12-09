package com.lion.a02_team_4_ShopManager.viewmodel

class ShopModel(
    val shopIdx: Int,
    val shopName: String,
    val shopKorean: Int,
    val shopEnglish: Int,
    val shopMath: Int,
    var shopTotal: Int = 0,
    var shopAvg: Int = 0
)