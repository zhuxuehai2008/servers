package com.zxh.interfaces.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.util.List;

import com.hankcs.hanlp.HanLP;

public class ArticleController {

	public static void  main(String[] args) throws FileNotFoundException,Exception{
		System.out.println(HanLP.segment("你好，欢迎使用HanLP汉语处理包！"));
		String content = "程序员(英文Programmer)是从事程序开发、维护的专业人员。一般将程序员分为程序设计人员和程序编码人员，但两者的界限并不非常清楚，特别是在中国。软件从业人员分为初级程序员、高级程序员、系统分析员和项目经理四大类。";
		FileInputStream fileInputStream = new FileInputStream(new File("c:/a.txt"));
		String readText = readText("c:/a.txt");
		System.out.println(readText);
		List<String> keywordList  = HanLP.extractKeyword(readText, 10);
		System.out.println(keywordList);
		List<String> phraseList = HanLP.extractPhrase(readText, 10);
		System.out.println(phraseList);
		String document = "算法可大致分为基本算法、数据结构的算法、数论算法、计算几何的算法、图的算法、动态规划以及数值分析、加密算法、排序算法、检索算法、随机化算法、并行算法、厄米变形模型、随机森林算法。\n" +
		        "算法可以宽泛的分为三类，\n" +
		        "一，有限的确定性算法，这类算法在有限的一段时间内终止。他们可能要花很长时间来执行指定的任务，但仍将在一定的时间内终止。这类算法得出的结果常取决于输入值。\n" +
		        "二，有限的非确定算法，这类算法在有限的时间内终止。然而，对于一个（或一些）给定的数值，算法的结果并不是唯一的或确定的。\n" +
		        "三，无限的算法，是那些由于没有定义终止定义条件，或定义的条件无法由输入的数据满足而不终止运行的算法。通常，无限算法的产生是由于未能确定的定义终止条件。";
		List<String> sentenceList = HanLP.extractSummary(document, 3);
		System.out.println(sentenceList);
	}
	public static String readText(String pathname) throws IOException{
        File filename = new File(pathname); // 要读取以上路径的input。txt文件  
        //StringBufferInputStream stringBufferInputStream = new StringBufferInputStream(pathname);
        ///stringBufferInputStream.
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename),"GBK"); // 建立一个输入流对象reader  
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
        StringBuffer buffer  = new StringBuffer();
        String line = ""; 
        line = br.readLine(); 
        buffer.append(line);
        while (line != null) {  
            line = br.readLine(); // 一次读入一行数据  
            buffer.append(line);
            //if(line != null)buffer.append(new String(line.getBytes(),"GBK"));
        } 
        return buffer.toString();
	}
}
