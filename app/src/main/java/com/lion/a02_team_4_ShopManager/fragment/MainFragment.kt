package com.lion.a02_team_4_ShopManager.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.google.android.material.transition.MaterialSharedAxis
import com.lion.a02_team_4_ShopManager.MainActivity
import com.lion.a02_team_4_ShopManager.R
import com.lion.a02_team_4_ShopManager.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        // 첫 화면이 보이도록 설정한다.
        replaceFragment(EnumFragmentName.SHOP_LIST_FRAGMENT, false, false, null)

        return fragmentMainBinding.root
    }
    // 프래그먼트를 교체하는 함수
    fun replaceFragment(fragmentName: EnumFragmentName, isAddToBackStack:Boolean, animate:Boolean, dataBundle: Bundle?){
        // 프래그먼트 객체
        val newFragment = when(fragmentName){
            // 쇼핑 목록 화면
            EnumFragmentName.SHOP_LIST_FRAGMENT -> shopListFragment(this)
            // 쇼핑 정보 검색 화면
            EnumFragmentName.SEARCH_SHOP_FRAGMENT -> SearchShopFragment(this)
            // 쇼핑 정보 보는 화면
            EnumFragmentName.SHOW_SHOP_FRAGMENT -> ShowShopFragment(this)
            // 쇼핑 정보 수정 화면
            EnumFragmentName.MODIFY_SHOP_FRAGMENT ->  ModifyShopFragment(this)
            // 쇼핑 정보 입력 화면
            EnumFragmentName.INPUT_SHOP_FRAGMENT -> InputShopFragment(this)
            // 쇼핑 성적 화면
            //EnumFragmentName.SHOP_POINT_FRAGMENT -> ShopPointFragment(this)
            // 쇼핑 통계 화면
            //EnumFragmentName.SHOP_INFO_FRAGMENT -> ShopInfoFragment(this)

        }

        // bundle 객체가 null이 아니라면
        if(dataBundle != null){
            newFragment.arguments = dataBundle
        }

        // 프래그먼트 교체
        mainActivity.supportFragmentManager.commit {

            if(animate) {
                newFragment.exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
                newFragment.reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
                newFragment.enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
                newFragment.returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
            }

            replace(R.id.fragmentContainerViewNavigation, newFragment)
            if(isAddToBackStack){
                addToBackStack(fragmentName.str)
            }
        }
    }
    // 프래그먼트를 BackStack에서 제거하는 메서드
    fun removeFragment(fragmentName: EnumFragmentName){
        mainActivity.supportFragmentManager.popBackStack(fragmentName.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    // MainFragment를 통해 보여줄 Fragment들의 이름
    enum class EnumFragmentName(val number:Int, val str:String){
        // 쇼핑 목록 화면
        SHOP_LIST_FRAGMENT(1, "ShopListFragment"),
        // 쇼핑 검색 화면
        SEARCH_SHOP_FRAGMENT(2, "SearchShopFragment"),
        // 쇼핑 정보 보는 화면
        SHOW_SHOP_FRAGMENT(3, "ShowShopFragment"),
        // 쇼핑 정보 수정 화면
        MODIFY_SHOP_FRAGMENT(4, "ModifyShopFragment"),
        // 쇼핑 정보 입력 화면
        INPUT_SHOP_FRAGMENT(5, "InputShopFragment"),
        // 쇼핑 성적 화면
        //SHOP_POINT_FRAGMENT(6, "ShopPointFragment"),
        // 쇼핑 통계 화면
        //SHOP_INFO_FRAGMENT(7, "ShopInfoFragment"),

    }

}