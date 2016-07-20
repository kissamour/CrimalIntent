package com.bigerdranch.android.crimalintent;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

public class DatePickerFragment extends DialogFragment {
	private static final String EXTRA_DATE = "com.bignerdranch.android.criminalintent.date"; //被点击列表项id 键值对的键
	private Date mDate;

	/*
	 * 获取Date对象并初始化DatePicker
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		//date对象得到的是一个时间戳，我们需要得到整数的年月日，需要操作Calendar对象
		mDate = (Date) getArguments().getSerializable(EXTRA_DATE);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		View v = getActivity().getLayoutInflater().inflate(
				R.layout.dialog_date, null);

		DatePicker datePicker = (DatePicker) v
				.findViewById(R.id.dialog_date_datePicker);
		//初始化datePicker对象，并给它设置一个监听器
		datePicker.init(year, month, day, new OnDateChangedListener() {

			@Override
			public void onDateChanged(DatePicker view, int year, int month,
					int day) {
				mDate = new GregorianCalendar(year, month, day).getTime();
				//为防止设备旋转时Date数据丢失，将date数据保存到Argument
				getArguments().putSerializable(EXTRA_DATE, mDate);

			}

		});

		return new AlertDialog.Builder(getActivity()).setView(v)	//使用AlertDialog.Builder的setView()方法把DatePicker组件添加到AlertDialog对话框
				.setTitle(R.string.date_picker_title)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { //监听，点击Ok之后
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						sendResult(Activity.RESULT_OK);
					}
				}).create();

	}

	/*
	 * 添加newInstance(Date)方法 ，用来保存时间数据
	 */
	public static DatePickerFragment newInstance(Date date) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_DATE, date);

		DatePickerFragment fragment = new DatePickerFragment();
		fragment.setArguments(args);
		return fragment;

	}
	
	private void sendResult(int resultCode){
		if(getTargetFragment()==null){
			return;}
			
			Intent i =new Intent();
			i.putExtra(EXTRA_DATE, mDate);
			
			getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
		}
	}

}
