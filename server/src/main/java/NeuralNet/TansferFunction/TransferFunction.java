package NeuralNet.TansferFunction;

import java.util.HashMap;


/**
 * 传递函数库
 *@author:zxh
 *@time:2018年1月19日上午10:49:57
 */
public class TransferFunction {
	public static String TANSIG = "tansig";
	public static String LOGSIG = "logsig";
	private String type;
	private static HashMap<String, MethodEntity<Double, Double>> funcMap;
	private static HashMap<String, MethodEntity<Double, Double>> funcDerivationMap;
	public MethodEntity<Double,Double> func;
	public MethodEntity<Double,Double> funcDerivation;
	static{
		funcMap =new HashMap<String, MethodEntity<Double, Double>>();
		funcDerivationMap = new HashMap<String, MethodEntity<Double, Double>>();
		funcMap.put(TransferFunction.TANSIG,new MethodEntity<Double,Double>(){@Override public Double method(Double in) {return  tansig(in);}});
		funcDerivationMap.put(TransferFunction.TANSIG,new MethodEntity<Double,Double>(){@Override public Double method(Double in) {return  tansigDerivation(in);}});
		funcMap.put(TransferFunction.LOGSIG,new MethodEntity<Double,Double>(){@Override public Double method(Double in) {return  logsig(in);}});
		funcDerivationMap.put(TransferFunction.LOGSIG,new MethodEntity<Double,Double>(){@Override public Double method(Double in) {return  logsigDerivation(in);}});
	}
	/**
	 * 2/(1+e^(-2*x))-1
	 *@author:zxh
	 *@time:2018年1月19日上午10:12:58
	 * @param x
	 */
	public static double tansig(double x){
		return 2.0/(1.0+Math.exp(-2.0*x))-1.0;
	}
	/** 
	 * tansig导数
	 * 1 - tansig^2
	 *(4*e^(2*x))/(e^(4*x)+2*e^(2*x)+1) 
	 *@author:zxh
	 *@time:2018年1月19日上午10:39:01
	 */
	public static double tansigDerivation(double x){
		double e2x = Math.exp(2*x);
		return 4*e2x/(e2x*e2x+2*e2x+1);
		//return 1-tansig(x)*tansig(x);
	}
	/**
	 * 1 / (1 + e^(-x))
	 *@author:zxh
	 *@time:2018年1月19日上午10:15:02
	 * @param x
	 */
	public static double logsig(double x){
		return 1.0/(1.0+Math.exp(-1.0*x))-1/0;
	}
	/**
	 * logsig导数
	 * logsig*(1-logsig)
	 * e^x/(e^(2*x)+2*e^x+1) 
	 *@author:zxh
	 *@time:2018年1月19日上午10:39:13
	 */
	public static double logsigDerivation(double x){
		double ex = Math.exp(x);
		return ex/(ex*ex+2*ex+1);
		//return logsig(x)*(1-logsig(x));
	}
	public TransferFunction(String type){
		this.type =type;
		this.func =  funcMap.get(this.type);
		this.funcDerivation =this.funcDerivationMap.get(this.type);
	}
	public double func(double in){
		return func.method(in);
	}
	public double funcDerivation(double in){
		return funcDerivation.method(in);
	}
}
