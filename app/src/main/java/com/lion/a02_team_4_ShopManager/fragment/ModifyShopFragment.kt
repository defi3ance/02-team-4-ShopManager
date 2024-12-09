package com.lion.a02_team_4_ShopManager.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lion.a02_team_4_ShopManager.MainActivity
import com.lion.a02_team_4_ShopManager.R
import com.lion.a02_team_4_ShopManager.databinding.FragmentModifyShopBinding

class ModifyShopFragment(val mainFragment: MainFragment) : Fragment() {


    lateinit var fragmentModifyShopBinding: FragmentModifyShopBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentModifyShopBinding = FragmentModifyShopBinding.inflate(inflater)
        mainActivity = activity as MainActivity
        settingToolbarModifyShop()

        return fragmentModifyShopBinding.root
    }
    fun settingToolbarModifyShop(){
        fragmentModifyShopBinding.apply {
            toolbarModifyShop.title = "상품 정보 수정"
            toolbarModifyShop.setNavigationIcon(R.drawable.arrow_back_24px)
            toolbarModifyShop.setNavigationOnClickListener {
                mainFragment.removeFragment(MainFragment.EnumFragmentName.MODIFY_SHOP_FRAGMENT)
            }
        }
    }

}

