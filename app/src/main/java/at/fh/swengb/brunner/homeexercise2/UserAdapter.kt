package at.fh.swengb.brunner.homeexercise2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView

class UserAdapter(val clickListener: (user: User) -> Unit): RecyclerView.Adapter<UserViewHolder>(){
    var userList = mutableListOf<User>()

    override fun onCreateViewHolder(parrent: ViewGroup, position: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parrent.context)
        val userItemView = inflater.inflate(R.layout.user_item, parrent, false)
        return UserViewHolder(clickListener, userItemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(viewHolder: UserViewHolder, position: Int) {
        val user = userList[position]
        viewHolder.bindItem(user)
    }

    fun updateData(newList: List<User>){
        userList = newList.toMutableList()
        notifyDataSetChanged()
    }
}

class UserViewHolder(val clickListener: (user: User) -> Unit, myView: View): RecyclerView.ViewHolder(myView){
    fun bindItem(user: User){

    }
}