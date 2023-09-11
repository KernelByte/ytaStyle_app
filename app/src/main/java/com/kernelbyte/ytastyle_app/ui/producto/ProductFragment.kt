package com.kernelbyte.ytastyle_app.ui.producto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
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

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        // Cuando se haga scroll cambiara de pagina en el tab
        val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                TabProductos.getTabAt(position)?.select()
            }

           override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                // Este método se llama cuando se realiza un desplazamiento entre páginas
                // Puedes realizar acciones específicas durante el desplazamiento
            }

            override fun onPageScrollStateChanged(state: Int) {
                // Este método se llama cuando cambia el estado del desplazamiento
                // Puedes realizar acciones específicas cuando cambia el estado
            }
        }

// Agrega el OnPageChangeCallback al ViewPager2
        ViewPageProductos.registerOnPageChangeCallback(onPageChangeCallback)

        // Asegúrate de eliminar el callback cuando ya no sea necesario, por ejemplo, en onDestroy
        fun onDestroy() {
            super.onDestroy()
            ViewPageProductos.unregisterOnPageChangeCallback(onPageChangeCallback)
        }


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