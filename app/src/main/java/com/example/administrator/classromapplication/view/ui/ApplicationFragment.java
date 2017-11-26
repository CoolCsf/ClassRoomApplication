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
import com.example.administrator.classromapplication.viewmodel.ApplicationViewModel;
import com.example.administrator.classromapplication.viewmodel.UserViewModel;
import com.tool.util.DateUtils;
import com.tool.util.widget.CustomTitleBar;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
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
                    model.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                showToast("申请成功");
                            } else {
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
