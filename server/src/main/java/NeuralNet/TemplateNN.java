package NeuralNet;


public class TemplateNN {
	/**
	 * 获取随机权重的值方差法
	 *@author:zxh
	 *@time:2018年1月19日下午3:50:14
	 */
	public double randomSqrtWeight(int n){
		return Math.random()/Math.sqrt(n);
	}
	/**
	 * 获取随机权重的值标准法
	 *@author:zxh
	 *@time:2018年1月19日下午3:50:14
	 */
	public double randomWeight(double max){
		return Math.random()*max;
	}
	/**
	 * 向量归一化
	 *@author:zxh
	 *@time:2018年1月19日上午11:30:44
	 */
	public double[] normalize(double[] arr){
		double sumx2= 0.0;
		for(double i :arr){
			sumx2+=i*i;
		}
		double n = Math.sqrt(sumx2);
		double[] r = new double[arr.length];
		for(int i=0;i<arr.length;i++){
			r[i]=arr[i]/n;
		}
		return r;
	}
}
