package com.example.myguysapp

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.my_guys_add_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyGuysAddFragment : Fragment() {

    companion object {
        fun newInstance(): MyGuysAddFragment {
            return MyGuysAddFragment()
        }
    }

    private var namaInput : String = ""
    private var emailInput : String = ""
    private var telpInput : String = ""
    private var alamatInput : String = ""
    private var genderInput : String = ""

    private var db : AppDatabase? = null
    private var myGuysDao : MyGuysDao? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstancceState: Bundle?): View? {
        return inflater.inflate(R.layout.my_guys_add_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstancceState: Bundle?){
        super.onViewCreated(view, savedInstancceState)
        initLocalDB()
        initView()
    }

    private fun  initLocalDB(){
        db = AppDatabase.getAppDataBase(activity!!)
        myGuysDao = db?.myGuysDao()
    }

    private fun initView(){
        btnSave.setOnClickListener{validasiInput()}

        setDataSpinnerGender()
    }

    private fun setDataSpinnerGender(){
        val adapter = ArrayAdapter.createFromResource(activity!!, R.array.gender_list, android.R.layout.simple_spinner_item)
    }

    private fun validasiInput(){
        namaInput = edtName.text.toString()
        emailInput = edtEmail.text.toString()
        telpInput = edtTelp.text.toString()
        alamatInput = edtAlamat.text.toString()
        genderInput = spinnerGender.selectedItem.toString()

        when{
            namaInput.isEmpty() -> edtName.error = "Nama tidak boleh kosong"
            genderInput.equals("Jenis Kelamin") -> tampilToast("Kelaminn harus dipilih")
            emailInput.isEmpty() -> edtEmail.error = "Email tidak boleh kosong"
            telpInput.isEmpty() -> edtTelp.error = "No. telepon tidak boleh kosong"
            alamatInput.isEmpty() -> edtAlamat.error = "Alamat tidak boleh kosong"

            else -> {
                val kawan = MyGuys(nama = namaInput, kelamin = genderInput, email = emailInput, telp = telpInput, alamat = alamatInput)
                tambahDataKawan(kawan)
            }
        }
    }

    private fun tambahDataKawan(kawan:MyGuys): Job{

        return GlobalScope.launch{
            myGuysDao?.tambahKawan(kawan)
            (activity as MainActivity).tampilMyGuysFragment()
        }
    }

    private fun tampilToast(message: String){
        Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy(){
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}