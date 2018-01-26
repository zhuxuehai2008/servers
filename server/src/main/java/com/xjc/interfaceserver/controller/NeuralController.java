package com.xjc.interfaceserver.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjc.interfaceserver.core.ResponseObj;

import NeuralNet.RecurrentNN;
import NeuralNet.Entity.StockStructure;

@Controller
@RequestMapping("neural/")
public class NeuralController {
	@RequestMapping("index")
	public String index(HttpServletRequest request){
		return "nerualAnalysis";
	}
	@RequestMapping("prepareData/RecurrentNN")
	@ResponseBody
	public ResponseObj prepareDataRecurrentNN(HttpServletRequest request){
		final ArrayList<StockStructure> data = RecurrentNN.getData();
		HttpSession session = request.getSession();
		session.setAttribute("StockStructureList", data);
		System.out.println("@@@@@@@@@@@@@@@");
		return new ResponseObj();
	}
	@RequestMapping("analysisArgs/RecurrentNN")
	@ResponseBody
	public ResponseObj analysisArgsRecurrentNN(HttpServletRequest request,HttpSession session){
		String serialNum = request.getParameter("serialNum");
		String maxhiddenNum = request.getParameter("maxhiddenNum");
		ArrayList<StockStructure> arrayList = (ArrayList<StockStructure>) session.getAttribute("StockStructureList");
		ArrayList<HashMap<String, Number>> analysisHiddenNum = RecurrentNN.analysisHiddenNum(Integer.valueOf(serialNum), Integer.valueOf(maxhiddenNum), arrayList);
		return new ResponseObj(analysisHiddenNum);
	}
}
