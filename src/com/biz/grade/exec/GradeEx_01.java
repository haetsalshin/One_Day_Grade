package com.biz.grade.exec;

import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import com.biz.grade.config.Lines;
import com.biz.grade.service.ScoreService;
import com.biz.grade.service.ScoreServiceImplV1;
import com.biz.grade.service.StudentService;
import com.biz.grade.service.StudentServiceImplV1;

public class GradeEx_01 {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		StudentService stuService = new StudentServiceImplV1();
		ScoreService scService = new ScoreServiceImplV1();
		
		while(true) {
			
			
			System.out.println(Lines.D_LINE);
			System.out.println("학생 정보 리스트V1");
			System.out.println(Lines.D_LINE);
			System.out.println("아래 중 원하는 번호를 입력하세요");
			System.out.println("1. 학생 정보 입력");
			System.out.println("2. 학생 성적 입력");
			System.out.println("3. 학생 정보 및 성적 확인");
			System.out.println("-1. 프로그램 종료");
			System.out.println(Lines.S_LINE);
			System.out.print("번호입력 >> ");
			String strMenu = scan.nextLine();
			int intMenu = 0;
			try {
				intMenu = Integer.valueOf(strMenu);	
			} catch (Exception e) {
				System.out.println("메뉴선택은 숫자로만 입력하세요");
				continue;
			}
			
			
			if(intMenu == 1) {
				
				while(true) {
					
					if(!stuService.inputStudent()) {
						break;
					}
					stuService.loadStudentFile();
					stuService.studentList();
					
					stuService.saveStudentFile();
				}
				
			}
			
			if(intMenu == 2) {
				

				while(true) {
					if(!scService.inputScore()) {
						break;
					}
					scService.loadScoreFile();
					scService.calcSum();
					scService.calcAvg();
					scService.scoreList();

					scService.saveScoreFile();
					
				}	
				
			}
			
			if(intMenu == 3) {
				
			}
			
			if(intMenu == -1) {
				System.out.println("해당프로그램이 종료됩니다");
				break;
			}else if(intMenu >3 || intMenu <-1) {
				System.out.println("메뉴는 -1번부터 3번까지만 선택 가능합니다. 다시 선택해주세요.");
				continue;
			}
		}
		

	}
	

}
