package com.bigerdranch.android.crimalintent;

import java.util.Date;
import java.util.UUID;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class CrimeFragment extends Fragment {
	private Crime mCrime;
	private EditText mTitleField;
	private Button mDateButton;
	private CheckBox mSolvedCheckBox;
	public static final String EXTRA_CRIME_ID = "com.bignerdranch.android.crinalintent.crime.id";
	private static final String DIALOG_DATE = "date";
	private static final int REQUEST_DATE=0;    //请求代码常量
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		 * 获取传来被点击Crime的id号
		 */
		// UUID mId = (UUID) getActivity().getIntent().getSerializableExtra(
		// CrimeListFragment.EXTRA_CRIME_ID);
		UUID mId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
		mCrime = CrimeLab.get(getActivity()).getCrime(mId);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_crime, parent, false);
		mTitleField = (EditText) v.findViewById(R.id.crime_title);
		mTitleField.setText(mCrime.getTitle());
		mTitleField.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		/*
		 * 日期按钮设置
		 */
		mDateButton = (Button) v.findViewById(R.id.crime_date);
		// mDateButton.setText(DateFormat.getMediumDateFormat(getActivity()).format(mCrime.getDate()));
		// mDateButton.setText(DateFormat.format("EEEE,MMM,dd,yyyy",mCrime.getDate()));
		mDateButton.setText(DateFormat.getLongDateFormat(getActivity()).format(
				mCrime.getDate()));
//		mDateButton.setEnabled(false);	设置为不点击状态
		
		mDateButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fm=getActivity().getSupportFragmentManager();
//				DatePickerFragment dialog=new DatePickerFragment();
				DatePickerFragment dialog=DatePickerFragment.newInstance(mCrime.getDate());
				dialog.setTargetFragment(CrimeFragment.this,REQUEST_DATE);
				dialog.show(fm, DIALOG_DATE);
				
			}
		});
		

		mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
		mSolvedCheckBox.setChecked(mCrime.isSolved());
		mSolvedCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						mCrime.setSolved(isChecked);
					}
				});
		return v;
	}
	
	/*
	 * 保存值 ID
	 */
	public static Fragment newInstance(UUID crimeID) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_CRIME_ID, crimeID);
		
		CrimeFragment fragment = new CrimeFragment();
		fragment.setArguments(args);
		return fragment;

	}
	
	@Override
	public void onActivityResult(int requestCode,int resultCode,Intent data){
		
	}
}
