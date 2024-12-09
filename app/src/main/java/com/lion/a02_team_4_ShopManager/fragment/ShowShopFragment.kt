package com.lion.a02_team_4_ShopManager.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lion.a02_team_4_ShopManager.MainActivity
import com.lion.a02_team_4_ShopManager.R
import com.lion.a02_team_4_ShopManager.databinding.FragmentShowShopBinding

class ShowShopFragment(val mainFragment: MainFragment) : Fragment() {
    lateinit var fragmentShowShopBinding: FragmentShowShopBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentShowShopBinding = FragmentShowShopBinding.inflate(inflater)
        mainActivity = activity as MainActivity
        settingToolbarShowShop()

        // Inflate the layout for this fragment
        return fragmentShowShopBinding.root
    }
    fun settingToolbarShowShop(){
        fragmentShowShopBinding.apply {
            toolbarShowShop.title = "상품 정보 보기"

            toolbarShowShop.setNavigationIcon(R.drawable.arrow_back_24px)
            toolbarShowShop.setNavigationOnClickListener {
                mainFragment.removeFragment(MainFragment.EnumFragmentName.SHOW_SHOP_FRAGMENT)
            }

            toolbarShowShop.inflateMenu(R.menu.toolbar_show_shop_menu)
            toolbarShowShop.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.show_shop_menu_modify -> {
                        mainFragment.replaceFragment(
                            MainFragment.EnumFragmentName.MODIFY_SHOP_FRAGMENT,
                            true, true, null)
                    }
                    R.id.show_shop_menu_remove -> {
                        mainFragment.removeFragment(MainFragment.EnumFragmentName.SHOW_SHOP_FRAGMENT)
                    }
                }
                true
            }
        }
    }
}