package com.zxh.interfaces.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxh.core.entity.ResponseObj;
import com.zxh.core.exception.GlobalException;
import com.zxh.interfaces.mapper.NeuralMapper;

import NeuralNet.RecurrentNN;
import NeuralNet.Entity.StockStructure;
import net.sf.json.JSONObject;

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
	@RequestMapping("test")
	public String test() throws Exception{
		HashMap findAll = neuralMapper.findAll();
		System.out.println(JSONObject.fromObject(findAll));
		System.out.println("@@@@@@@@@");
		//FileInputStream fileInputStream = new FileInputStream(new File("c://aaaa.txtx"));
		//throw new GlobalException("asddsf");
		return "index";
	}
	@Autowired private NeuralMapper neuralMapper; 
}
