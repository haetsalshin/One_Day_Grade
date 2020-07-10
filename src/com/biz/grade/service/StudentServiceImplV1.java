package com.biz.grade.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biz.grade.config.Lines;
import com.biz.grade.vo.StudentVO;

public class StudentServiceImplV1 implements StudentService {

	protected List<StudentVO> studentList;
	protected Scanner scan;
	protected String bFileName="";
	
	public StudentServiceImplV1() {
		studentList = new ArrayList<StudentVO>();
		scan = new Scanner(System.in);
		bFileName ="src/com/biz/grade/exec/data/student.txt";
	}
	
	
	@Override
	public boolean inputStudent() {
		
		StudentVO studentVO = new StudentVO();
		
		System.out.printf("학번 (종료:END) >> ");
		String strNum = scan.nextLine();
		if(strNum.equals("END")) {
			return false;
		}
		
		try {
			strNum = String.format("%05d", Integer.valueOf(strNum));
		} catch (Exception e) {
			System.out.println("학번은 정수 다섯자리 이내 값만 가능합니다");
			System.out.println("다시 입력해주세요");
			return true;
		}
		
		
		studentVO.setStrNum(strNum);
		

		
		System.out.printf("이름 >> ");
		String strName = scan.nextLine();
		
		studentVO.setStrName(strName);
		
		
		System.out.printf("학과 >> ");
		String strDept = scan.nextLine();
		
		studentVO.setStrDept(strDept);
		
		
		System.out.printf("학년(1~4학년) >> ");
		String strGrade = scan.nextLine();
		int intGrade = 0;
		try {
			intGrade = Integer.valueOf(strGrade);
		} catch (Exception e) {
			System.out.println("학년은 숫자로만 입력 가능합니다.");
			return true;
		}
		
		if(intGrade>5 || intGrade <1) {
			System.out.println("학년은 1~4학년으로만 입력 가능합니다.");
			return true;
		}
		
		studentVO.setIntGrade(intGrade);
		
		System.out.printf("전화번호(000-0000-0000) >> ");
		String strPhone = scan.nextLine();
		
		studentVO.setStrPhone(strPhone);
		
		studentList.add(studentVO);
		
		
		
		return true;
	}

	@Override
	public void studentList() {

		String[] listTile = {"학번","이름","학과","학년","전화번호"};
		
		System.out.println(Lines.D_LINE);
		System.out.println("학생 정보");
		System.out.println(Lines.D_LINE);
		//System.out.println("학번\t\t|이름\t\t|학과\t\t|학년\t\t|전화번호\t\t|");
		System.out.printf("%-8s\t|%8s\t|%30s\t|%10s\t|%40s\t|\n"
				,listTile[0],listTile[1],listTile[2],listTile[3],listTile[4]);
		
		System.out.println(Lines.S_LINE);
		
		//DecimalFormat df = new DecimalFormat("###"+"-"+"####"+"-"+"####");
		//DecimalFormatSymbols de = new DecimalFormatSymbols();
		//de.setGroupingSeparator('-'); 
		//DecimalFormat phoneNumberFormat = new DecimalFormat("82)###,####,####", de);
		
		for(StudentVO sVO : studentList) {
			System.out.printf("%-8s\t|",sVO.getStrNum());
			System.out.printf("%8s\t|",sVO.getStrName());
			System.out.printf("%30s\t|",sVO.getStrDept());
			System.out.printf("%10s\t|",sVO.getIntGrade());
			System.out.printf("%40s\t|\n",sVO.getStrPhone());
			//System.out.printf("%40s\t|\n",phoneNumberFormat.format(sVO.getIntPhone()));
			
		}
		
		System.out.println(Lines.S_LINE);
		
		
	}
	
	

	@Override
	public void loadStudentFile() {

		
		FileReader fileReader = null;
		BufferedReader buffer = null;
		
		try {
			fileReader = new FileReader(bFileName);
			buffer = new BufferedReader(fileReader);
			
			String reader ="";
			while(true) {
				reader = buffer.readLine();
				if(reader == null) {
					break;
				}
				
				String[] students = reader.split(":");
				
				StudentVO stuVO = new StudentVO();
				
				stuVO.setStrNum(students[0]);
				stuVO.setStrName(students[1]);
				stuVO.setStrDept(students[2]);
				stuVO.setIntGrade(Integer.valueOf(students[3]));
				stuVO.setStrPhone(students[4]);
				
				studentList.add(stuVO);
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
	public void saveStudentFile() {

		PrintWriter outPut = null;
		
		
		try {
			outPut = new PrintWriter(bFileName);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(StudentVO stuVO : studentList) {
			
			String studentInfo = String.format("%s:%s:%s:%d:%s", stuVO.getStrNum(),stuVO.getStrName()
					,stuVO.getStrDept(),stuVO.getIntGrade(),stuVO.getStrPhone());
			
			
			outPut.println(studentInfo);
		}
		outPut.close();
		System.out.println("해당 학생 정보 파일에 저장 완료!!! :)");
		
	}
	


}
