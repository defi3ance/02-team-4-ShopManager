package com.lion.a02_team_4_ShopManager.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ShopTable")
data class ShopVO(
    @PrimaryKey(autoGenerate = true)
    // 상품 번호
    var shopIdx:Int = 0,
    // 상품 이름
    var shopName:String = "",
    // 학년
    var shopGrade:Int = 0,
    // 운동부
    var shopType:Int = 0,
    // 성별
    var shopGender:Int = 0,
    // 국어점수
    var shopKorean:Int = 0,
    // 영어점수
    var shopEnglish:Int = 0,
    // 수학점수
    var shopMath:Int = 0
)