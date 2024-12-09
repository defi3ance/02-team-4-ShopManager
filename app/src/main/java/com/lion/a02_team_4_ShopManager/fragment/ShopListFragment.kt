package com.lion.a02_team_4_ShopManager.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.lion.a02_team_4_ShopManager.MainActivity
import com.lion.a02_team_4_ShopManager.R
import com.lion.a02_team_4_ShopManager.databinding.FragmentShopListBinding
import com.lion.a02_team_4_ShopManager.databinding.RowText1Binding
import com.lion.a02_team_4_ShopManager.repository.ShopRepository
import com.lion.a02_team_4_ShopManager.viewmodel.ShopModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class shopListFragment(val mainFragment: MainFragment) : Fragment() {

    lateinit var fragmentShopListBinding: FragmentShopListBinding
    lateinit var mainActivity: MainActivity

    // RecyclerView 구성을 위한 임시데이터
    val tempShopSubject = Array(100){
        "갤럭시 ${it + 1}"
    }
    val tempShopPrice = Array(100){
        " ${it + 1000}원"
    }
    val tempShopImage = Array(100){
        R.drawable.galaxy2
    }


    // 상품 데이터를 담고 있는 리스트
    var shopList = mutableListOf<ShopModel>()

    // InputStudenFragment 를 갔다 왔는지 구분하기 위한 변수
    var isBackToInputShopFragment = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentShopListBinding = FragmentShopListBinding.inflate(inflater, container, false)
        mainActivity = activity as MainActivity

        // InputShopFragment에서 돌아온 경우라면
        if(isBackToInputShopFragment == true){
            // 필요한 작업을 한다.

            isBackToInputShopFragment = false
        }

        // 툴바를 구성하는 메서드를 호출한다.
        settingToolbarShopList()
        // RecyclerView를 구성하는 메서드를 호출한다.
        settingRecyclerViewShopList()
        Log.d("test11", "test11122")
        // FAB를 구성하는 메서드를 호출한다.
        settingFabShopList()

        return fragmentShopListBinding.root
    }

    // 툴바를 구성하는 메서드
    fun settingToolbarShopList(){
        fragmentShopListBinding.apply {
            toolbarShopList.title = "상품 목록"
            toolbarShopList.inflateMenu(R.menu.toolbar_shop_list_menu)

            toolbarShopList.setOnMenuItemClickListener {
                when(it.itemId){
//                    R.id.Shop_list_menu_filter ->{
//                        // 필터 다이얼로그를 띄우는 메서드를 호출한다.
//                        showFilterDialog()
//                    }
                    R.id.shop_list_menu_search -> {
                        // 검색화면으로 이동한다.
                        mainFragment.replaceFragment(
                            MainFragment.EnumFragmentName.SEARCH_SHOP_FRAGMENT,
                            true, true, null)
                    }
                }
                true
            }

        }
    }

    // RecyclerView를 구성하는 메서드
//    fun settingRecyclerViewShopList(){
//        fragmentShopListBinding.apply {
//            recyclerViewShopList.adapter = RecyclerViewShopListAdapter()
//            recyclerViewShopList.layoutManager = LinearLayoutManager(mainActivity)
//            val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
//            recyclerViewShopList.addItemDecoration(deco)
//
//            // 데이터를 읽어와 리사이클러 뷰를 갱신한다.
//            refreshRecyclerView()
//        }
//    }

    // 필터 다이얼로그를 띄우는 메서드

    // FAB를 구성하는 메서드
    fun settingFabShopList(){
        fragmentShopListBinding.apply {
            fabShopList.setOnClickListener {
                // 상품 정보 입력 화면으로 이동한다.
                isBackToInputShopFragment = true
                mainFragment.replaceFragment(MainFragment.EnumFragmentName.INPUT_SHOP_FRAGMENT, true, true, null)
            }
        }
    }

    // 데이터 베이스에서 데이터를 읽어와 RecyclerView를 갱신한다.
    fun refreshRecyclerView(){
        CoroutineScope(Dispatchers.Main).launch {
            val work1 = async(Dispatchers.IO){
                // 데이터를 읽어온다.
                //ShopRepository.selectShopDataAll(mainActivity)
            }
            //shopList = tempData as ShopModel
            //shopList = work1.await()
            // 데이터를 필터링한다.
            //filteringData()
            // RecyclerView를 갱신한다.
            fragmentShopListBinding.recyclerViewShopList.adapter?.notifyDataSetChanged()
        }
    }
    inner class RecyclerViewShopListAdapter : RecyclerView.Adapter<RecyclerViewShopListAdapter.ViewHolderShopList>(){
        // ViewHOlder
        inner class ViewHolderShopList(val rowText1Binding: RowText1Binding) : RecyclerView.ViewHolder(rowText1Binding.root), OnClickListener{
            override fun onClick(v: View?) {
                // adapterPosition : ViewHolder를 통해 구성된 항목의 순서 값
                // 사용자가 누른 항목의 순서 값으로 사용하였다
                //fragmentShopListBinding.textView.text = strArray[adapterPosition]
                Log.d("test11", "onClick")
                mainFragment.replaceFragment(
                    MainFragment.EnumFragmentName.SHOW_SHOP_FRAGMENT,
                    true,
                    true,
                    null

                )

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderShopList {
            val rowText1Binding = RowText1Binding.inflate(layoutInflater, parent, false)
            val viewHolderShopList = ViewHolderShopList(rowText1Binding)
            rowText1Binding.root.setOnClickListener(viewHolderShopList)
            //Log.d("test11", "onCreateViewHolder")
            return viewHolderShopList
        }

        override fun getItemCount(): Int {
            //Log.d("test11", "getItemCount")

            return tempShopSubject.size
        }

        override fun onBindViewHolder(holder: ViewHolderShopList, position: Int) {
            holder.rowText1Binding.imageViewRow.setImageResource(tempShopImage[position])
            holder.rowText1Binding.textViewRow.text = tempShopSubject[position]
            holder.rowText1Binding.textViewRow2.text = tempShopPrice[position]
            //Log.d("test11", "onBinding")

        }
    }

    fun settingRecyclerViewShopList(){
        fragmentShopListBinding.apply {
            recyclerViewShopList.adapter = RecyclerViewShopListAdapter()
            recyclerViewShopList.layoutManager = GridLayoutManager(mainActivity, 2)
            val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)


            // 디바이더 좌측 여백설정
            deco.dividerInsetStart = 300
            // 디바이더 우측 여백 설정
            deco.dividerInsetEnd = 50
            recyclerViewShopList.addItemDecoration(deco)
        }
    }
    
    /*
    // RecyclerView의 어뎁터
    inner class RecyclerViewShopListAdapter : RecyclerView.Adapter<RecyclerViewShopListAdapter.ViewHolderShopList>(){
        // ViewHolde

        inner class ViewHolderShopList(val rowText1Binding: RowText1Binding) : RecyclerView.ViewHolder(rowText1Binding.root),
            OnClickListener {
            override fun onClick(v: View?) {
                // 상품의 번호를 담는다.
                val dataBundle = Bundle()
                //dataBundle.putInt("ShopIdx", shopList[adapterPosition].shopIdx)
                dataBundle.putInt("shopIdx", tempData[adapterPosition].toInt())
                // 상품 정보를 보는 화면으로 이동한다.
                mainFragment.replaceFragment(MainFragment.EnumFragmentName.SHOW_SHOP_FRAGMENT, true, true, dataBundle)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderShopList {
            val rowText1Binding = RowText1Binding.inflate(layoutInflater, parent, false)
            val viewHolderShopList = ViewHolderShopList(rowText1Binding)
            rowText1Binding.root.setOnClickListener(viewHolderShopList)
            return viewHolderShopList
        }

        override fun getItemCount(): Int {
            return shopList.size
            //return tempData.size
        }

        override fun onBindViewHolder(holder: ViewHolderShopList, position: Int) {
            holder.rowText1Binding.textViewRow.text = shopList[position].shopName
        }
    }
*/
    // 필터에 선택되어 있는 것만 남겨두는 메서드

}