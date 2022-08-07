package com.example.foodapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentKampanyaBinding
import com.example.foodapp.entity.Kampanya
import com.example.foodapp.ui.adapter.KampanyaAdapter
import com.example.foodapp.ui.viewmodel.KampanyaFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KampanyaFragment : Fragment() {
    private lateinit var tasarim: FragmentKampanyaBinding
    private lateinit var viewModel: KampanyaFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim =DataBindingUtil.inflate(inflater,R.layout.fragment_kampanya, container, false)
        tasarim.kampanyaFragment = this
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarKampanya)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        viewModel.kampanyaListesi.observe(viewLifecycleOwner){
            val adapter = KampanyaAdapter(requireContext(),it,viewModel)
            tasarim.kampanyaAdapter = adapter
        }



        return tasarim.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel: KampanyaFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }



    override fun onResume() {
        super.onResume()
        viewModel.kampanyaYukle()
    }




}