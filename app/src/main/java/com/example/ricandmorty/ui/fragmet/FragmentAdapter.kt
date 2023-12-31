package com.example.ricandmorty.ui.fragmet

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class FragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(
    fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    var fragmentList: ArrayList<Fragment> = ArrayList()
    var fragmentTitle: ArrayList<String> = ArrayList()

    override fun getCount(): Int = (fragmentList.size)

    override fun getItem(position: Int): Fragment = (fragmentList[position])

    override fun getPageTitle(position: Int): CharSequence = (fragmentTitle[position])

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add (fragment)
        fragmentTitle.add(title)
    }

}