package com.bigerdranch.android.crimalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {
	private UUID mId;
	private Date mDate;// �¼��ķ���ʱ��
	private boolean mSolved; // ֵ��ʾCrime�Ƿ��Ѿ�����
	private String mTitle;

	public boolean isSolved() {
		return mSolved;
	}

	public void setSolved(boolean solved) {
		mSolved = solved;
	}

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public UUID getId() {
		return mId;
	}

	public Crime() {
		mId = UUID.randomUUID();
		mDate = new Date();
	}

	public String toString() {
		return mTitle;
	}
}
