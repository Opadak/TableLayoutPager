package com.example.tablelayoutpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_tab_pager.*
import kotlinx.android.synthetic.main.activity_tab_pager2.*

class TabPager2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_pager)


        tab_layout.addTab(tab_layout.newTab().setText("ONE"))
        tab_layout.addTab(tab_layout.newTab().setText("TWO"))
        tab_layout.addTab(tab_layout.newTab().setText("THREE"))

        val adapter = ThreePageAdpater(LayoutInflater.from(this@TabPager2Activity))
        view_pager.adapter = adapter
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))


        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) { //탭이 다시 선택 되었을 때
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager.setCurrentItem(tab!!.position)
                //Tab이 Null값이 올 수 있기 때문에 무조건 Null이 아니다 라는 !를 적어주었다.
                //currentItem에는 Null값을 넣을 수 없다.
            }
        })
    }
}


class ThreePageAdpater(
    val layoutInflater: LayoutInflater
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        //실제적으로 view를 그려주는 곳.
        when (position) {
            0 -> {
                val view = layoutInflater.inflate(R.layout.fragment1, container, false)
                container.addView(view)
                return view
            }
            1 -> {
                val view = layoutInflater.inflate(R.layout.fragment2, container, false)
                container.addView(view)
                return view // 현재 사실상 `object`는 이것임!
            }
            2 -> {
                val view = layoutInflater.inflate(R.layout.fragment3, container, false)
                container.addView(view)
                return view
            }
            else -> {
                val view = layoutInflater.inflate(R.layout.fragment1, container, false)
                container.addView(view)
                return view
            }
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //pager가 보이다가 안보일 때 불필요하다 느끼면 이 함수 호출해서 삭제
        //사라지는 view니까 컨테이너에서 떼어줘야 함!
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        //만들고자하는 페이지의 수
        return 3
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        //니가 만든거 맞냐
        return view === `object` as View
        //`object` 이라는 것은 자바에서 object는 별 뜻이 없는 것 하지만 코틀린은 object가 키워드로 잡혀있다.
        // 키워드로 잡혀 있는 것은 키워드로 사용 할 수 없기 때문에
        // 복붙해서 사용하도록!!!!!!!!
        // '==' 각 비교
        // '===' 조금더 정확한 비교 (주솟값을 비교)
        //습관적으로 사용해도 ㄱㅊ
        //`object` as View 'as View'를 붙인 이유 > `object`가 Any로 되어있으니 바꿔주는 것!
    }
}