package easy.tuto.mytablayoutapplication

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kernelbyte.ytastyle_app.ui.fragments.ArticulosFragment
import com.kernelbyte.ytastyle_app.ui.fragments.CategoriasFragment
import com.kernelbyte.ytastyle_app.ui.fragments.InventarioFragment
import com.kernelbyte.ytastyle_app.ui.producto.ProductFragment

class MyViewPagerAdapter(fragmentActivity: ProductFragment) : FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ArticulosFragment()
            1 -> InventarioFragment()
            2 -> CategoriasFragment()
            else -> ArticulosFragment()
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}