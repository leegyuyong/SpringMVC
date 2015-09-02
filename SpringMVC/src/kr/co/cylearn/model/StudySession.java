package kr.co.cylearn.model;

import kr.co.cylearn.common.base.BaseObject;


public class StudySession extends BaseObject {

	private static final long serialVersionUID = -3787411303748126883L;
	private String userId;
	private String subj;
	private String year;
	private String subjSeq;
	private int logSeq;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSubj() {
		return subj;
	}
	public void setSubj(String subj) {
		this.subj = subj;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getSubjSeq() {
		return subjSeq;
	}
	public void setSubjSeq(String subjSeq) {
		this.subjSeq = subjSeq;
	}
	public int getLogSeq() {
		return logSeq;
	}
	public void setLogSeq(int logSeq) {
		this.logSeq = logSeq;
	}
	@Override
	public String toString() {
		return "StudySession [userId=" + userId + ", subj=" + subj + ", year=" + year + ", subjSeq=" + subjSeq + ", logSeq=" + logSeq + "]";
	}
}
