package com.bigerdranch.android.crimalintent;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class CrimeListFragment extends ListFragment {
	private ArrayList<Crime> mCrimes;

	public static final int REQUEST_CRIME=1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.crimes_title);
		mCrimes = CrimeLab.get(getActivity()).getCrimes();

		/*
		 * 用adapter把mCrimes填充进ListView
		 */
		// ArrayAdapter<Crime> adapter = new ArrayAdapter<Crime>(getActivity(),
		// R.layout.simple_list_item_1, mCrimes);
		CrimeAdapter adapter = new CrimeAdapter(mCrimes);
		setListAdapter(adapter);

	}
	@Override
	public void onResume(){
		super.onResume();
		((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
		
	}

	/*
	 * 赋予列表项点击事件
	 */
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Crime c = (Crime) (getListAdapter().getItem(position));
		Crime c = ((CrimeAdapter) getListAdapter()).getItem(position);

		/*
		 * 把点击事件，Crime的ID传给CrimeActivity
		 */
		Intent i = new Intent(getActivity(), CrimePagerActivity.class);
		i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());

		startActivityForResult(i, REQUEST_CRIME);
	}

	/*
	 * 把crime的信息通过inflate装进列表项 CrimeAdapter：定制的Adapter
	 */
	private class CrimeAdapter extends ArrayAdapter<Crime> { // 绑定crimeAdapter和Crime
		CrimeAdapter(ArrayList<Crime> crimes) {
			super(getActivity(), 0, crimes);
		}

		@Override
		public View getView(int position, View converView, ViewGroup parent) {
			if (converView == null) { // 如果没有一个converView，通过inflate创建一个
				converView = getActivity().getLayoutInflater().inflate(
						R.layout.list_item_crime, null);
			}
			//无论是新对象还是复用对象，都应该调用Adapter的getItem(int)方法获取列表中当前position的Crime对象
			Crime c = getItem(position);	

			TextView titleTextView = (TextView) converView
					.findViewById(R.id.crime_list_item_titleTextView);
			titleTextView.setText(c.getTitle());
			TextView dateTextView = (TextView) converView
					.findViewById(R.id.crime_list_item_dateTextView);
			dateTextView.setText(c.getDate().toString());
			CheckBox solvedCheckBox = (CheckBox) converView
					.findViewById(R.id.crime_list_item_solvedCheckBox);
			solvedCheckBox.setChecked(c.isSolved());
			return converView;

		}

	}
}
