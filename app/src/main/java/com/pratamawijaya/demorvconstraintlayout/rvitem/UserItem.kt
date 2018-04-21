package com.pratamawijaya.demorvconstraintlayout.rvitem

import com.pratamawijaya.demorvconstraintlayout.R
import com.pratamawijaya.demorvconstraintlayout.entity.User
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_user.view.tvUserBalance
import kotlinx.android.synthetic.main.item_user.view.tvUserDesc
import kotlinx.android.synthetic.main.item_user.view.tvUserEmail
import kotlinx.android.synthetic.main.item_user.view.tvUserName

class UserItem constructor(private val user:User) :Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tvUserName.text = user.name
        viewHolder.itemView.tvUserBalance.text = user.balance
        viewHolder.itemView.tvUserDesc.text = user.about
        viewHolder.itemView.tvUserEmail.text = user.email
    }

    override fun getLayout(): Int = R.layout.item_user
}