package com.lion.a02_team_4_ShopManager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ShopVO::class], version = 1, exportSchema = true)
abstract class ShopDataBase : RoomDatabase(){
    // dao
    abstract fun shopDao() : ShopDao

    companion object{
        // 데이터 베이스 객체를 담을 변수
        var shopDatabase:ShopDataBase? = null
        @Synchronized
        fun getInstance(context: Context) : ShopDataBase?{
            // 만약 데이터베이스 객체가 null이라면 객체를 생성한다.
            // 데이터베이스 파일 이름 꼭 변경!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            synchronized(ShopDataBase::class){
                shopDatabase = Room.databaseBuilder(
                    context.applicationContext, ShopDataBase::class.java,
                    "Shop.db"
                ).build()
            }
            return shopDatabase
        }

        // 데이터 베이스 객체가 소멸될 때 호출되는 메서드
        fun destroyInstance(){
            shopDatabase = null
        }
    }
}