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
		 * ��adapter��mCrimes����ListView
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
	 * �����б������¼�
	 */
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Crime c = (Crime) (getListAdapter().getItem(position));
		Crime c = ((CrimeAdapter) getListAdapter()).getItem(position);

		/*
		 * �ѵ���¼���Crime��ID����CrimeActivity
		 */
		Intent i = new Intent(getActivity(), CrimePagerActivity.class);
		i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());

		startActivityForResult(i, REQUEST_CRIME);
	}

	/*
	 * ��crime����Ϣͨ��inflateװ���б��� CrimeAdapter�����Ƶ�Adapter
	 */
	private class CrimeAdapter extends ArrayAdapter<Crime> { // ��crimeAdapter��Crime
		CrimeAdapter(ArrayList<Crime> crimes) {
			super(getActivity(), 0, crimes);
		}

		@Override
		public View getView(int position, View converView, ViewGroup parent) {
			if (converView == null) { // ���û��һ��converView��ͨ��inflate����һ��
				converView = getActivity().getLayoutInflater().inflate(
						R.layout.list_item_crime, null);
			}
			//�������¶����Ǹ��ö��󣬶�Ӧ�õ���Adapter��getItem(int)������ȡ�б��е�ǰposition��Crime����
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
