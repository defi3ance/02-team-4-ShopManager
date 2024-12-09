package com.lion.a02_team_4_ShopManager.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.lion.a02_team_4_ShopManager.MainActivity
import com.lion.a02_team_4_ShopManager.R
import com.lion.a02_team_4_ShopManager.databinding.FragmentSearchShopBinding
import com.lion.a02_team_4_ShopManager.databinding.RowText1Binding
import com.lion.a02_team_4_ShopManager.repository.ShopRepository
import com.lion.a02_team_4_ShopManager.viewmodel.ShopModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SearchShopFragment(val mainFragment: MainFragment) : Fragment() {


    lateinit var fragmentSearchShopBinding: FragmentSearchShopBinding
    lateinit var mainActivity: MainActivity

    // 리사이클러 뷰 구성을 위한 임시 데이터
//    val tempData = Array(100){
//        "상품 ${it + 1}"
//    }

    // 리사클리어뷰 구성을 위한 리스트
    var shopList = mutableListOf<ShopModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentSearchShopBinding = FragmentSearchShopBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        // 툴바를 구성하는 메서드를 호출한다.
        settingToolbarSearchShop()
        // recyclerView를 구성하는 메서드
        settingRecyclerViewSearchShop()
        // 입력 요소 설정 메서드를 호출한다.
        //settingTextField()


        return fragmentSearchShopBinding.root
    }

    // 툴바를 구성하는 메서드
    fun settingToolbarSearchShop(){
        fragmentSearchShopBinding.apply {
            toolbarSearchShop.title = "상품 검색"

            toolbarSearchShop.setNavigationIcon(R.drawable.arrow_back_24px)
            toolbarSearchShop.setNavigationOnClickListener {
                mainFragment.removeFragment(MainFragment.EnumFragmentName.SEARCH_SHOP_FRAGMENT)
            }
        }
    }

    // recyclerView를 구성하는 메서드
    fun settingRecyclerViewSearchShop(){
        fragmentSearchShopBinding.apply {
            recyclerViewSearchShop.adapter = RecyclerViewShopSearchAdapter()
            recyclerViewSearchShop.layoutManager = LinearLayoutManager(mainActivity)
            val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
            recyclerViewSearchShop.addItemDecoration(deco)
        }
    }

    // Recyclerview의 어뎁터
    inner class RecyclerViewShopSearchAdapter : RecyclerView.Adapter<RecyclerViewShopSearchAdapter.ViewHolderShopSearch>(){
        inner class ViewHolderShopSearch(val rowText1Binding: RowText1Binding) : RecyclerView.ViewHolder(rowText1Binding.root), OnClickListener{
            override fun onClick(v: View?) {
                // 상품 정보를 보는 화면으로 이동한다.
                Log.d("test11", "onClick_search")
                mainFragment.replaceFragment(MainFragment.EnumFragmentName.SHOW_SHOP_FRAGMENT, true, true, null)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderShopSearch {
            val rowText1Binding = RowText1Binding.inflate(layoutInflater, parent, false)
            val viewHolderShopSearch = ViewHolderShopSearch(rowText1Binding)
            rowText1Binding.root.setOnClickListener(viewHolderShopSearch)
            return viewHolderShopSearch
        }

        override fun getItemCount(): Int {
            return shopList.size
        }

        override fun onBindViewHolder(holder: ViewHolderShopSearch, position: Int) {
            holder.rowText1Binding.textViewRow.text = shopList[position].shopName
        }
    }




}