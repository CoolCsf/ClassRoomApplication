package com.example.administrator.classromapplication.view.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.administrator.classromapplication.R;
import com.example.administrator.classromapplication.databinding.FragmentApplicationBinding;
import com.example.administrator.classromapplication.model.ApplicationStatueEmun;
import com.example.administrator.classromapplication.model.event.UpdateBadgeNum;
import com.example.administrator.classromapplication.viewmodel.ApplicationViewModel;
import com.example.administrator.classromapplication.viewmodel.UserViewModel;
import com.tool.util.DateUtils;
import com.tool.util.ToastHelp;
import com.tool.util.widget.CustomTitleBar;

import org.greenrobot.eventbus.EventBus;

import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.PushListener;
import cn.bmob.v3.listener.SaveListener;

import static com.tool.util.ToastHelp.showToast;

/**
 * Created by Administrator on 2017/11/14.
 */

public class ApplicationFragment extends BaseFragment<FragmentApplicationBinding> {
    private ApplicationViewModel model;

    @Override
    protected void initView() {
        ((CustomTitleBar) binding.titleBar).setTitle("课室申请");
        ((CustomTitleBar) binding.titleBar).setRightTitle("申请");
    }

    @Override
    protected void initListener() {
        ((CustomTitleBar) binding.titleBar).setRightTitleOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.canSubmit()) {
                    model.setApplicationStatus(ApplicationStatueEmun.PENDING.getStatus());
                    model.setUserId(BmobUser.getCurrentUser().getObjectId());
                    ((MainActivity) getActivity()).showLoading();
                    model.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                EventBus.getDefault().post(new UpdateBadgeNum());
                                pushToRoot();
                            } else {
                                ((MainActivity) getActivity()).closeLoading();
                                showToast("申请失败" + e.getMessage());
                            }
                        }
                    });
                }
            }
        });
        binding.tvUseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMonthDayPicker();
            }
        });
        binding.tvStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTimePicker(binding.tvStartTime, true);
            }
        });
        binding.tvEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTimePicker(binding.tvEndTime, false);
            }
        });
    }

    private void pushToRoot() {
        BmobPushManager bmobPushManager = new BmobPushManager();
        BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
        query.addWhereEqualTo("isRoot", true);
        bmobPushManager.setQuery(query);
        bmobPushManager.pushMessage("有课室申请啦~!请处理!", new PushListener() {
            @Override
            public void done(BmobException e) {
                ((MainActivity) getActivity()).closeLoading();
                if (e == null) {
                    ToastHelp.showToast("已告知老师审批，请等待");
                } else {
                    ToastHelp.showToast("发生错误" + e.getMessage());
                }
            }
        });
    }

    private void onTimePicker(final TextView tv, final boolean isStart) {
        TimePickerDialog pickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                tv.setText(hourOfDay + " 时 " + minute + " 分 ");
                if (isStart)
                    model.setStartTime(hourOfDay + " 时 " + minute + " 分 ");
                else
                    model.setEndTime(hourOfDay + " 时 " + minute + " 分 ");
            }
        }, 0, 0, true);
        pickerDialog.show();
    }

    public void onMonthDayPicker() {
        DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                binding.tvUseDate.setText(year + "年" + (month + 1) + "月" + dayOfMonth + "日");
                model.setUseDate(year + "年" + (month + 1) + "月" + dayOfMonth + "日");
            }
        }, Integer.valueOf(DateUtils.getNowYear()), 0, 1);
        pickerDialog.show();
    }

    @Override
    protected void initData() {
        model = new ApplicationViewModel();
        binding.setData(model);
        model.setText("11");
        model.setApplicaUser(BmobUser.getCurrentUser(UserViewModel.class).name);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_application;
    }


}
