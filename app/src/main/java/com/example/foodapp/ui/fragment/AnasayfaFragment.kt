package com.example.foodapp.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentAnasayfaBinding
import com.example.foodapp.entity.Yemekler
import com.example.foodapp.ui.adapter.YemeklerAdapter
import com.example.foodapp.ui.viewmodel.AnasayfaFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.yemekler_card_tasarim.*
import kotlinx.android.synthetic.main.yemekler_card_tasarim.view.*

@AndroidEntryPoint

class AnasayfaFragment : Fragment() , SearchView.OnQueryTextListener {
    private lateinit var tasarim:FragmentAnasayfaBinding
    private lateinit var viewModel : AnasayfaFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_anasayfa, container, false)
        tasarim.anasayfaFragment = this

        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarAnasayfa)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        viewModel.yemekleriYukle()

        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
            val adapter = YemeklerAdapter(requireContext(),it,viewModel)
            tasarim.yemeklerAdapter = adapter
        }



        return tasarim.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


        val tempViewModel : AnasayfaFragmentViewModel by viewModels()
        viewModel = tempViewModel

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)


    }

    override fun onQueryTextSubmit(query: String): Boolean { //arama iconu seçilirse çalışır.
        viewModel.ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean { //harf girdikçe ve sildikçe çalışır.
        viewModel.ara(newText)
        return true
    }


    override fun onResume() {
        super.onResume()
        viewModel.yemekleriYukle()
    }

}