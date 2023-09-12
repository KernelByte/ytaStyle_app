package com.kernelbyte.ytastyle_app.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.kernelbyte.ytastyle_app.databinding.FragmentArticulosBinding
import com.kernelbyte.ytastyle_app.io.ApiService
import com.kernelbyte.ytastyle_app.model.Products
import com.kernelbyte.ytastyle_app.ui.MenuActivity
import com.kernelbyte.ytastyle_app.ui.producto.RegisterProduct
import com.kernelbyte.ytastyle_app.util.ProductAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ArticulosFragment : Fragment() {

    private var _binding: FragmentArticulosBinding? = null
    private val binding get() = _binding!!

    private val apiService : ApiService by lazy {
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentArticulosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        getProducts()

        _binding!!.btRegistrarProduct.setOnClickListener{
            val i = Intent(requireActivity(), RegisterProduct::class.java)
            startActivity(i)
        }

        return root
    }

    private fun getProducts(){

        val call = apiService.getAllProducts()

        call.enqueue(object : Callback<List<Products>> {
            override fun onResponse(call: Call<List<Products>>, response: Response<List<Products>>) {
                Log.d("Exitoso","onResponse: {${response.body()!![0].idProduct}} ")
                showData(response.body()!!)
            }

            override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                //Log.d("Falla","onFailure")
                Log.d("ArticulosFragment","onFailure: " + t.message)
            }
        })
    }

    private fun showData(produts: List<Products>) {
        /*val itemAdapter=ProductAdapter(produts)
        val recyclerP = binding.rvArticulos
        recyclerP.layoutManager = LinearLayoutManager(context)
        recyclerP.adapter = itemAdapter*/

        binding.rvArticulos.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ProductAdapter(produts)
        }
    }
}