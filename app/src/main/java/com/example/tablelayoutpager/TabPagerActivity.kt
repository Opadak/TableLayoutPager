package com.example.tablelayoutpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_tab_pager.*
import java.util.*

class TabPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_pager)

        //taplayout 설정
        tab_layout.addTab(tab_layout.newTab().setText("ONE"))
        tab_layout.addTab(tab_layout.newTab().setText("TWO"))
        tab_layout.addTab(tab_layout.newTab().setText("THREE"))
        //pagerAdapter 생성
        val pagerAdapter = PagerAdapter(supportFragmentManager,3)
        view_pager.adapter = pagerAdapter

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) { //탭이 다시 선택 되었을 때

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager.currentItem = tab!!.position //Tab이 Null값이 올 수 있기 때문에 무조건 Null이 아니다 라는 !를 적어주었다.
                //currentItem에는 Null값을 넣을 수 없다.
            }
        })


        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        // -> pager가 이동했을 대 탭을 이동시키는 코드
    }
}


// 페이저 탭 레이아웃이 상호작용하기 위해서는 Listener를 사용한다.
// adapter는 pager를 위한것.

class PagerAdapter(
    fragmentManager: FragmentManager,  //fragment를 사용하겠다.
    val tabCount: Int
) :FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment { //아이템 하나를 찾을 때 이것!  하나를 리턴해줌
        when(position){
            0 -> {
                return Fragmnet1()
            }
            1 -> {
                return Fragment2()
            }
            2 -> {
                return Fragment3()
            }
            else ->{
                return Fragmnet1()
            }
        }
    }

    override fun getCount(): Int { //전체 크기
        return tabCount

    }
}