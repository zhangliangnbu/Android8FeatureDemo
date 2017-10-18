package com.zhangliang.android8featuredemo

import android.app.PictureInPictureParams
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.Rational
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
        private const val ACTION = "com.zhangliang.android8featuredemo.action_activitys"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // resolves
        val list = packageManager.queryIntentActivities(Intent(ACTION), PackageManager.MATCH_DEFAULT_ONLY)
        rv_activities.layoutManager = LinearLayoutManager(this)
        rv_activities.adapter = ActivitiesAdapter(list, this)
    }

    internal inner class ActivitiesAdapter(private val list: List<ResolveInfo>, private val context: Context) : RecyclerView.Adapter<ActivitiesAdapter.CViewHolder>() {


        override fun getItemCount(): Int {
            return list.size
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CViewHolder {
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.activity_list_item, parent, false)
            return CViewHolder(view)
        }

        override fun onBindViewHolder(holder: CViewHolder?, position: Int) {
            holder?.tvLabel?.text = list[position].loadLabel(context.packageManager)
        }


        internal inner class CViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
            override fun onClick(v: View?) {
                val resolveInfo = list[adapterPosition]
                val component = ComponentName(context.packageName, resolveInfo.activityInfo.name)
                val intent = Intent()
                intent.component = component
                context.startActivity(intent)
            }

            val tvLabel: TextView = itemView.findViewById(R.id.tv_label)
            init {
                itemView.setOnClickListener(this)
            }
        }
    }

}
