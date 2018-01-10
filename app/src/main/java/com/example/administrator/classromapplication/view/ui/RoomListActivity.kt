package com.example.administrator.classromapplication.view.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.administrator.classromapplication.R
import com.example.administrator.classromapplication.adapter.RoomListAdapter
import com.example.administrator.classromapplication.databinding.ActivityRoomListBinding
import com.example.administrator.classromapplication.model.ApplicationStatueEmun
import com.example.administrator.classromapplication.viewmodel.ApplicationRoomItemViewModel
import com.example.administrator.classromapplication.viewmodel.ApplicationViewModel
import com.tool.util.CollectionUtils
import com.tool.util.widget.CustomTitleBar
import kotlinx.android.synthetic.main.activity_room_list.*

/**
 * Created by Ervin on 2018/1/10.
 */
class RoomListActivity : AbsActivity<ActivityRoomListBinding>() {

    private var statusKey: Int = 0
    private var isRoot: Boolean = false
    lateinit var mAdapter: RoomListAdapter
    override fun getLayoutId(): Int {
        return R.layout.activity_room_list
    }

    companion object {
        val ROOM_KEY: String = "room_key"
        val ROOT_KEY: String = "root_key"
    }

    override fun initData() {
    }

    override fun initView() {
        (titleBar as CustomTitleBar).title = ApplicationStatueEmun.getState(statusKey) + "列表"
        statusKey = intent.extras.getInt(ROOM_KEY)
        isRoot = intent.extras.getBoolean(ROOT_KEY)
        rv_applicationList.layoutManager = LinearLayoutManager(this)
        mAdapter = RoomListAdapter()
        rv_applicationList.adapter = mAdapter
    }

    override fun initListener() {
        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            val bundle = Bundle()
            bundle.putSerializable("applicationViewModel", mAdapter.data[position].viewModel)
            if (!isRoot) {
                goActivity(ApplicationDetailActivity::class.java, bundle)
            } else {
                goActivity(RootApplicationDetailActivity::class.java, bundle)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        queryFromBmob()
    }

    fun queryFromBmob() {
        val query = BmobQuery<ApplicationViewModel>()
        query.addWhereEqualTo("applicationStatus", statusKey)
        query.order("updateAt")
        val queries = ArrayList<BmobQuery<ApplicationViewModel>>()
        if (!isRoot) {
            val query1 = BmobQuery<ApplicationViewModel>()
            query1.addWhereEqualTo("userId", BmobUser.getCurrentUser().objectId)
            queries.add(query1)
        }
        queries.add(query)
        val queryAnd = BmobQuery<ApplicationViewModel>()
        queryAnd.and(queries)
        showLoading()
        queryAnd.findObjects(object : FindListener<ApplicationViewModel>() {
            override fun done(models: List<ApplicationViewModel>?, e: BmobException?) {
                closeLoading()
                if (e == null) {
                    if (CollectionUtils.collectionState(models) == CollectionUtils.COLLECTION_UNEMPTY) {
                        val itemList = ArrayList<ApplicationRoomItemViewModel>()
                        for (model in models!!) {
                            val itemModel = ApplicationRoomItemViewModel()
                            itemModel.useReason = model.useReason
                            itemModel.applicationUser = model.userId
                            itemModel.applicationTime = model.createdAt
                            itemModel.applicationId = model.objectId
                            itemModel.setApplicationStatus(model.applicationStatus)
                            itemModel.viewModel = model
                            itemList.add(itemModel)
                        }
                        mAdapter.setNewData(itemList)
                    } else {
                        mAdapter.setNewData(ArrayList<ApplicationRoomItemViewModel>())
                        showToast("查询不到数据")
                    }
                } else {
                    showToast(e.message)
                }
            }
        })
    }
}