package com.lion.a02_team_4_ShopManager.fragment

import android.content.ContentResolver
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import com.lion.a02_team_4_ShopManager.MainActivity
import com.lion.a02_team_4_ShopManager.R
import com.lion.a02_team_4_ShopManager.databinding.FragmentInputShopBinding


class InputShopFragment(val mainFragment: MainFragment) : Fragment() {
    //private val contentResolver: ContentResolver
    lateinit var fragmentInputShopBinding: FragmentInputShopBinding
    lateinit var mainActivity: MainActivity

    lateinit var albumLauncher: ActivityResultLauncher<Intent>

    val permissionList = arrayOf(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_MEDIA_LOCATION
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentInputShopBinding = FragmentInputShopBinding.inflate(inflater)
        mainActivity = activity as MainActivity
        settingToolbarInputShop()
        settingTextField()
        requestPermissions(permissionList, 0)
        createAlbumLauncher()

        return fragmentInputShopBinding.root
    }
    // 툴바를 구성하는 메서드

    fun settingToolbarInputShop(){
        fragmentInputShopBinding.apply {
            toolbarInputShop.title = "상품 정보 입력"
            toolbarInputShop.setNavigationIcon(R.drawable.arrow_back_24px)
            toolbarInputShop.setNavigationOnClickListener {
                mainFragment.removeFragment(MainFragment.EnumFragmentName.INPUT_SHOP_FRAGMENT)
            }
        }
    }
    fun settingTextField(){
        fragmentInputShopBinding.apply {
            mainActivity.showSoftInput(textFieldInputShopName.editText!!)
        }
    }
    // 런처를 생성하는 메서드
    fun createAlbumLauncher(){
        albumLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            // 사진을 고르고 왔다면
            if(it.resultCode == RESULT_OK){
                // 가져온 데이터가 있다면
                if(it.data != null){
                    // 선택한 이미지에 접근할 수 있는 Uri 객체가 있다면
                    if(it.data?.data != null){
                        // android 10 버전 이상이라면
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                            // 이미지 객체를 생성할 수 있는 디코드를 생성한다.
                            val source = ImageDecoder.createSource(mainActivity.contentResolver, it.data?.data!!)
                            // Bitmap 객체를 생성한다.
                            val bitmap = ImageDecoder.decodeBitmap(source)
                            //fragmentInputShopBinding.imageView.setImageBitmap(bitmap)
                        } else {
                            // ContentProvider를 통해 사진 데이터를 가져온다.
                            val cursor = mainActivity.contentResolver.query(it.data?.data!!, null, null, null, null)
                            if(cursor != null){
                                cursor.moveToNext()

                                // 이미지의 경로를 가져온다.
                                val idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                                val source = cursor.getString(idx)

                                // 이미지를 생성한다.
                                val bitmap = BitmapFactory.decodeFile(source)
                                //fragmentInputShopBinding.imageView.setImageBitmap(bitmap)
                            }
                        }
                    }
                }
            }
        }
    }




}
