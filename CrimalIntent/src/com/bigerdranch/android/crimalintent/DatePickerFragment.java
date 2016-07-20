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
	private static final String EXTRA_DATE = "com.bignerdranch.android.criminalintent.date"; //������б���id ��ֵ�Եļ�
	private Date mDate;

	/*
	 * ��ȡDate���󲢳�ʼ��DatePicker
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		//date����õ�����һ��ʱ�����������Ҫ�õ������������գ���Ҫ����Calendar����
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
		//��ʼ��datePicker���󣬲���������һ��������
		datePicker.init(year, month, day, new OnDateChangedListener() {

			@Override
			public void onDateChanged(DatePicker view, int year, int month,
					int day) {
				mDate = new GregorianCalendar(year, month, day).getTime();
				//Ϊ��ֹ�豸��תʱDate���ݶ�ʧ����date���ݱ��浽Argument
				getArguments().putSerializable(EXTRA_DATE, mDate);

			}

		});

		return new AlertDialog.Builder(getActivity()).setView(v)	//ʹ��AlertDialog.Builder��setView()������DatePicker�����ӵ�AlertDialog�Ի���
				.setTitle(R.string.date_picker_title)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { //���������Ok֮��
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						sendResult(Activity.RESULT_OK);
					}
				}).create();

	}

	/*
	 * ���newInstance(Date)���� ����������ʱ������
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
