package com.revature.eval.java.core;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TestDriver {

	public static void main(String[] args) {
	
		EvaluationService eval = new EvaluationService();
		
		System.out.println(eval.solveWordProblem("-1 plus -10"));
		
	}
	
	private static boolean strIsNum(String s) {
		
		try {
			
			Integer.parseInt(s);
			return true;
			
		} catch(Exception e) {
			
			return false;
			
		}
	}

}
