package com.technoelevate.hibernate;

import java.util.Scanner;

public class StudentDriver {
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		Student student=new Student();
		boolean exit=false;
		do {
			System.out.println("Press 1 to see all data\r\n"
					+ "Press 2 to see any particular data\r\n"
					+ "Press 3 to update any particular data\r\n"
					+ "Press 4 to delete data\r\n"
					+ "Press 5 to Exit");
			int input=scanner.nextInt();
			switch(input) {
			case 1: {
				student.seeAllTheData();
				break;
				
			}
			case 2: {
				student.seeParticularData();
				break;
				
			}
			case 3:{
				student.updateData();
				break;
			}
			case 4:{
				student.deleteData();
				break;
			}
			case 5:{
				exit=true;
				System.out.print("*********THANK YOU**********");
				break;
			}
			
			
			}
			
		}
		while(!exit);
	}

}
