package com.bigerdranch.android.crimalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class CrimeLab {
	private static CrimeLab sCrimeLab; // ����CrimeLab�Ķ���
	private Context mAppContext;
	private ArrayList<Crime> mCrimes;	//����Crime��ģ�Ͳ�mCrimes

	private CrimeLab(Context appContext) {
		mAppContext = appContext;
		mCrimes = new ArrayList<Crime>();
		for(int i=0;i<100;i++){
			Crime c=new Crime();
			c.setTitle("#Crime"+i);
			c.setSolved(i%2==0);
			mCrimes.add(c);
		}
	}

	public static CrimeLab get(Context c) { //���еõ�ʵ��
		if (sCrimeLab == null) {
			sCrimeLab = new CrimeLab(c.getApplicationContext());

		}
		return sCrimeLab;
	}

	public ArrayList<Crime> getCrimes() { //���������б�
		return mCrimes;
	}

	public Crime getCrime(UUID id) {	//����Crime����
		for (Crime c : mCrimes) {
			if (c.getId().equals(id))
				return c;

		}
		return null;
	}
	
}
