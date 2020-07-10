package com.biz.grade.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import com.biz.grade.config.Lines;
import com.biz.grade.vo.ScoreVO;
import com.biz.grade.vo.StudentVO;

public class ScoreServiceImplV1 implements ScoreService {

	protected List<ScoreVO> scoreList;
	protected List<StudentVO> studentList;
	protected Scanner scan;
	protected String bFileName = "";

	public ScoreServiceImplV1() {
		scoreList = new ArrayList<ScoreVO>();
		studentList = new ArrayList<StudentVO>();
		scan = new Scanner(System.in);
		bFileName = "src/com/biz/grade/exec/data/score.txt";
	}
	
	@Override
	public void loadScoreFile() {

		FileReader fileReader = null;
		BufferedReader buffer = null;

		try {
			fileReader = new FileReader(bFileName);
			buffer = new BufferedReader(fileReader);

			String reader = "";
			while (true) {
				reader = buffer.readLine();
				if (reader == null) {
					break;
				}

				String[] scores = reader.split(":");

				ScoreVO sVO = new ScoreVO();

				sVO.setStrNum(scores[0]);
				sVO.setIntKor(Integer.valueOf(scores[1]));
				sVO.setIntEng(Integer.valueOf(scores[2]));
				sVO.setIntMath(Integer.valueOf(scores[3]));
				sVO.setIntMusic(Integer.valueOf(scores[4]));

				scoreList.add(sVO);

			}

			buffer.close();
			fileReader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("파일이 없으므로 새로운 파일을 생성합니다.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean inputScore() {

		ScoreVO scoreVO = new ScoreVO();
		
		


		System.out.printf("학번 (종료:END) >> ");

		String strNum = scan.nextLine();
		if (strNum.equals("END")) {
			return false;
		}
			
		try {		
			strNum = String.format("%05d", Integer.valueOf(strNum));
			scoreVO.setStrNum(strNum);		
		} catch (Exception e) {
			System.out.println("학번은 정수 다섯자리 이내 값만 가능합니다");
			System.out.println("다시 입력해주세요");
			return true;
		}
		
		for(StudentVO studentVO :studentList) {
			if(scoreVO.getStrNum().equals(studentVO.getStrNum())) {
				scoreVO.setStrNum(strNum);
			}else {
				System.out.println("해당하는 학생의 정보가 없습니다. 다시 처음부터 입력해주세요.");
				return true;
			}
		}
		
		
		
		/*
		 * for (StudentVO studentVO : studentList) { int index = 0;
		 * if(strNum.equals(studentList.get(index).getStrNum())){
		 * scoreVO.setStrNum(strNum); break; }else { index++; continue; }
		 * 
		 * }
		 */

		System.out.printf("국어 >> ");
		String strKor = scan.nextLine();
		int intKor = 0;
		try {
			intKor = Integer.valueOf(strKor);
		} catch (Exception e) {
			System.out.println("국어 점수는 숫자만 입력 가능합니다.");
			return true;
		}

		scoreVO.setIntKor(intKor);

		System.out.printf("영어 >> ");
		String strEng = scan.nextLine();
		int intEng = 0;
		try {
			intEng = Integer.valueOf(strEng);
		} catch (Exception e) {
			System.out.println("영어 점수는 숫자만 입력 가능합니다.");
			return true;
		}

		scoreVO.setIntEng(intEng);

		System.out.printf("수학 >> ");
		String strMath = scan.nextLine();
		int intMath = 0;
		try {
			intMath = Integer.valueOf(strMath);
		} catch (Exception e) {
			System.out.println("수학 점수는 숫자만 입력 가능합니다.");
			return true;
		}
		scoreVO.setIntMath(intMath);

		System.out.printf("음악 >> ");
		String strMusic = scan.nextLine();
		int intMusic = 0;
		try {
			intMusic = Integer.valueOf(strMusic);
		} catch (Exception e) {
			System.out.println("음악 점수는 숫자만 입력 가능합니다.");
			return true;
		}
		scoreVO.setIntMusic(intMusic);

		scoreList.add(scoreVO);

		return true;
	}

	@Override
	public void calcSum() {

		for (ScoreVO scoreVO : scoreList) {

			int intSum = scoreVO.getIntKor();
			intSum += scoreVO.getIntEng();
			intSum += scoreVO.getIntMath();
			intSum += scoreVO.getIntMusic();

			scoreVO.setIntSum(intSum);

		}

	}

	@Override
	public void calcAvg() {

		for (ScoreVO scoreVO : scoreList) {

			int intAvg = scoreVO.getIntSum() / 4;

			scoreVO.setIntAvg(intAvg);
		}

	}

	@Override
	public void scoreList() {

		String[] listTitle = { "학번", "국어", "영어", "수학", "음악", "총점", "평균" };

		System.out.println(Lines.D_LINE);
		System.out.println("학생 성적 리스트");
		System.out.println(Lines.D_LINE);
		System.out.printf("%-8s\t|%8s\t|%8s\t|%8s\t|%8s\t|%10s\t|%8s\t|\n", listTitle[0], listTitle[1], listTitle[2],
				listTitle[3], listTitle[4], listTitle[5], listTitle[6]);

		System.out.println(Lines.S_LINE);

		for (ScoreVO scoreVO : scoreList) {
			System.out.printf("%-8s\t|", scoreVO.getStrNum());
			System.out.printf("%8s\t|", scoreVO.getIntKor());
			System.out.printf("%8s\t|", scoreVO.getIntEng());
			System.out.printf("%8s\t|", scoreVO.getIntMath());
			System.out.printf("%8s\t|", scoreVO.getIntMusic());
			System.out.printf("%10s\t|", scoreVO.getIntSum());
			System.out.printf("%8s\t|\n", scoreVO.getIntAvg());
		}
		System.out.println(Lines.D_LINE);
		int korSum = 0;
		int engSum = 0;
		int mathSum = 0;
		int musicSum = 0;

		for (ScoreVO scoreVO : scoreList) {
			korSum += scoreVO.getIntKor();
			engSum += scoreVO.getIntEng();
			mathSum += scoreVO.getIntMath();
			musicSum += scoreVO.getIntMusic();

		}
		int sumSum = korSum + engSum + musicSum + mathSum;
		int avgAvg = sumSum / 4;
		System.out.printf("%-8s\t|", "과목총점");
		System.out.printf("%8s\t|", korSum);
		System.out.printf("%8s\t|", engSum);
		System.out.printf("%8s\t|", mathSum);
		System.out.printf("%8s\t|", musicSum);
		System.out.printf("%8s\t|", sumSum);
		System.out.printf("%8s\t|\n", avgAvg);

		System.out.println(Lines.S_LINE);
		int size = scoreList.size();
		System.out.printf("%-8s\t|", "과목평균");
		System.out.printf("%8s\t|", korSum / size);
		System.out.printf("%8s\t|", engSum / size);
		System.out.printf("%8s\t|", mathSum / size);
		System.out.printf("%8s\t|", musicSum / size);
		System.out.printf("%8s\t|", sumSum / size);
		System.out.printf("%8s\t|\n", avgAvg / size);
		System.out.println(Lines.D_LINE);

	}

	@Override
	public void saveScoreFile() {

		PrintWriter outPut = null;

		try {
			outPut = new PrintWriter(bFileName);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (ScoreVO stVO : scoreList) {

			String scoreInfo = String.format("%s:%d:%d:%d:%d", stVO.getStrNum(), stVO.getIntKor(), stVO.getIntEng(),
					stVO.getIntMath(), stVO.getIntMusic());

			outPut.println(scoreInfo);
		}
		outPut.close();
		System.out.println("해당 학생 성적 파일에 저장 완료!!! :)");

	}
	public List<StudentVO> getStList() {
		return studentList;
	}

}
