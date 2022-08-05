package com.example.foodapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentProfilBinding
import com.example.foodapp.login.SignInActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class ProfilFragment : Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var tasarim:FragmentProfilBinding
    private lateinit var googleSignInClient : GoogleSignInClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_profil,container,false)
        tasarim.profilFragment = this

        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        firebaseAuth = FirebaseAuth.getInstance()

        //val acct = GoogleSignIn.getLastSignedInAccount(requireActivity())


        val email = firebaseAuth.currentUser!!.email
        val displayName = firebaseAuth.currentUser!!.email

        firebaseAuth.currentUser!!.email
        tasarim.textViewKullanici.text = email + "\n" + displayName


        tasarim.buttonCikis.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(activity, SignInActivity::class.java)
            startActivity(intent)
        }


        return tasarim.root
    }

    fun gecis(){
        Navigation.findNavController(tasarim.buttonKampanya).navigate(R.id.profildenKampanyaGecis)
    }



}