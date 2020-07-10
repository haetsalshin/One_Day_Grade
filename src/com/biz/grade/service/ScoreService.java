package com.biz.grade.service;

public interface ScoreService {
	
	public void loadScoreFile();
	public boolean inputScore();
	public void calcSum();
	public void calcAvg();
	public void scoreList();
	public void saveScoreFile();

}
