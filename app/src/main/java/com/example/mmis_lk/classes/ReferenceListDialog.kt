package com.example.mmis_lk.classes

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.mmis_lk.R
import com.example.mmis_lk.retrofit.RetrofitClient
import com.example.mmis_lk.retrofit.interfaces.mmisApi
import com.example.mmis_lk.retrofit.models.Reference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReferenceListDialog(savedToken: String?) : DialogFragment() {
    val savedToken = savedToken
//    val sharedPref: SharedPreferences? = activity!!.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
//    val savedToken = sharedPref?.getString("token", "").toString()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val builder = AlertDialog.Builder(it)
            val listRefNames = arrayOf("Справка об обучении", "Справка о стипендии")
            val client = RetrofitClient.getClient(resources.getString(R.string.local_base_url))
            val api = client.create(mmisApi::class.java)
            val listRefs: MutableList<Reference> = arrayListOf()
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val references = api.getAllReferences(savedToken.toString())
                    listRefs.addAll(references)
                        references.forEachIndexed { index, reference -> listRefNames[index] = reference.name }
                } catch (error: Exception){

                }
            }
            builder.setTitle(R.string.references)
//                    .setAdapter(ReferenceListAdapter(listRefs))
                    .setItems(listRefNames,
                        DialogInterface.OnClickListener { dialog, which ->
                            // The 'which' argument contains the index position
                            // of the selected item
                            val selectedRef = Reference(which+1, listRefNames[which].toString())
                            CoroutineScope(Dispatchers.IO).launch {
                                try {
                                    api.orderReferenceForMe(savedToken.toString(), selectedRef)
                                } catch (error: Exception){
                                    Toast.makeText(activity, error.toString(), Toast.LENGTH_LONG)
                                }
                            }
                        })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}