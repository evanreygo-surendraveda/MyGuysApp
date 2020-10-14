package com.example.myguysapp

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.my_guys_fragment.*
import java.util.ArrayList

class MyGuysFragment : Fragment() {
    companion object{
        fun newInstance() : MyGuysFragment{
            return MyGuysFragment()
        }
    }

    private var listKawan : ArrayList<MyGuys>? = null

    private var db: AppDatabase? = null
    private var myGuysDao: MyGuysDao? = null

    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.my_guys_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLocalDB()
        initView()
    }

    private fun initLocalDB(){
        db = AppDatabase.getAppDataBase(activity!!)
        myGuysDao = db?.myGuysDao()
    }

    private fun initView(){
        fabAddGuys.setOnClickListener{(activity as MainActivity).tampilMyGuysAddFragment()}

        ambilDataKawan()
    }

    private fun ambilDataKawan(){

        listKawan = ArrayList()
        myGuysDao?.ambilSemuaKawan()?.observe(this, Observer { r -> listKawan = r as ArrayList<MyGuys>?

        when {
            listKawan?.size == 0 -> tampilToast("Belum ada data kawan")

            else -> {
                tampilKawan()
            }
        }})

    }

    private fun tampilToast(message: String){
        Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

    //lateinit var listKawan : MutableList<MyGuys>

    //private fun  simulasiDataKawan() {
        //listKawan = ArrayList()

        //listKawan.add(MyGuys("Muhammad", "Laki-laki", "ade@gmail.com", "085719004268", "Bandung"))
        //listKawan.add(MyGuys("Evanreygo Surendraveda","Laki-laki", "ereygos22@gmail.com", "082336156818", "Tulungagung"))
    //}

    private fun tampilKawan(){
        listMyGuys.layoutManager = LinearLayoutManager(activity)
        listMyGuys.adapter = MyGuysAdapter(activity!!, listKawan!!)
    }

}