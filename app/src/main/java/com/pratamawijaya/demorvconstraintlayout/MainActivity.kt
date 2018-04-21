package com.pratamawijaya.demorvconstraintlayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.pratamawijaya.demorvconstraintlayout.entity.User
import com.pratamawijaya.demorvconstraintlayout.rvitem.UserItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.rvUser

class MainActivity : AppCompatActivity() {

    private val dummyJson = "https://gist.githubusercontent.com/pratamawijaya/daf6e06ac9621762e0e50789250a1877/raw/98381212f8ed94a967070c4677bd5eee4ec61626/mydata.json"

    private var groupadapter: GroupAdapter<ViewHolder> = GroupAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRv()

        loadJsonData(dummyJson)
    }

    /**
     * konfigurasi recyclerview
     */
    private fun setupRv() {
        val linearLayoutManager = LinearLayoutManager(this)
        rvUser.apply {
            adapter = groupadapter
            layoutManager = linearLayoutManager
        }
    }

    /**
     * load data dari internet
     */
    private fun loadJsonData(url: String) {
        url.httpGet().responseString { _, _, result ->
            when (result) {
                is Result.Failure -> {
                    Log.e("tag", "error ${result.getException()}")
                }
                is Result.Success -> {
                    val jsonString = result.value
                    val listUser = Gson().fromJson(jsonString, Array<User>::class.java).toList()
                    displayData(listUser)
                }
            }
        }
    }

    /**
     * tampilkan data ke recyclerview
     */
    private fun displayData(userList: List<User>?) {
        Section().apply {
            userList?.map {
                add(UserItem(it))
            }
            groupadapter.add(this)
        }
    }
}
