package com.bigerdranch.android.crimalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class CrimeLab {
	private static CrimeLab sCrimeLab; // 定义CrimeLab的对象
	private Context mAppContext;
	private ArrayList<Crime> mCrimes;	//保存Crime的模型层mCrimes

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

	public static CrimeLab get(Context c) { //单列得到实例
		if (sCrimeLab == null) {
			sCrimeLab = new CrimeLab(c.getApplicationContext());

		}
		return sCrimeLab;
	}

	public ArrayList<Crime> getCrimes() { //返回数组列表
		return mCrimes;
	}

	public Crime getCrime(UUID id) {	//返回Crime对象
		for (Crime c : mCrimes) {
			if (c.getId().equals(id))
				return c;

		}
		return null;
	}
	
}
