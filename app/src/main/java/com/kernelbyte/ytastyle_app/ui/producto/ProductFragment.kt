package com.kernelbyte.ytastyle_app.ui.producto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.kernelbyte.ytastyle_app.databinding.FragmentProductosBinding
import easy.tuto.mytablayoutapplication.MyViewPagerAdapter

class ProductFragment : Fragment() {

    private var _binding: FragmentProductosBinding? = null
    private lateinit var myViewPagerAdapter: MyViewPagerAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(ProductViewModel::class.java)

        _binding = FragmentProductosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val TabProductos = _binding!!.tabProducts
        val ViewPageProductos = _binding!!.vpProducts
        myViewPagerAdapter = MyViewPagerAdapter(this)
        ViewPageProductos.adapter = myViewPagerAdapter

        TabProductos.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    ViewPageProductos.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

        })

        /*val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}