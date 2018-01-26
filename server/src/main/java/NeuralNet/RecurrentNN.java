package NeuralNet;

import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import NeuralNet.Entity.StockStructure;
import NeuralNet.TansferFunction.TransferFunction;
import NeuralNet.dataSource.Http;
import net.sf.json.JSONArray;
/**
 * 循环神经网络，拓扑三层结构，类似bp神经网络，区别是隐藏层会将序列化输入数据在t-1时刻的隐藏层输出值传入t时刻的隐藏层。
 *@author:zxh
 *@time:2018年1月19日下午1:00:43
 */
public class RecurrentNN extends TemplateNN{
	public Neuron[] inputNet;
	public Neuron[] outputNet;
	public Neuron[]  hiddenNet;
	public double[][] inputWeight;//输入层到隐藏层权重
	public double[][] outputWeight;//隐藏层到输出层权重
	public double[][] TWeight;//隐藏层到下一时刻隐藏层权重，前一个[]表示前时间隐藏层默认用k，第二个[]表示下一时刻隐藏层，默认用k1
	public double[] inputVec;
	public double[] outputVec;
	public double[][] hiddenTout;
	public double[] outputMean;
	private int inputNum;
	private int outputNum;
	private int hiddenNum;
	TransferFunction transferFunction;
	private ObjectMapper objectMapper = new ObjectMapper();
	public RecurrentNN(int inputNum,int outputNum,int hiddenNum) {
		transferFunction = new TransferFunction(TransferFunction.TANSIG);
		this.inputVec = new double[inputNum];
		this.inputNum = inputNum;
		this.outputNum = outputNum;
		this.hiddenNum = hiddenNum;
		this.outputVec = new double[inputNum];
		this.inputNet = new Neuron[inputNum];
		this.outputNet = new Neuron[outputNum];
		this.hiddenNet = new Neuron[hiddenNum];
		this.inputWeight = new double[inputNum][hiddenNum];
		this.outputWeight = new double[hiddenNum][outputNum];	
		this.TWeight = new double[hiddenNum][hiddenNum];
		for(int i=0;i<this.inputNet.length;i++){
			this.inputNet[i] = new Neuron(transferFunction);
		}
		for(int i=0;i<this.outputNet.length;i++){
			this.outputNet[i] = new Neuron(transferFunction);
		}
		for(int i=0;i<this.hiddenNet.length;i++){
			this.hiddenNet[i] = new Neuron(transferFunction);
		}
		for(int i=0 ;i<this.inputWeight.length;i++){
			for(int j=0;j<this.inputWeight[i].length;j++){
				this.inputWeight[i][j]=randomSqrtWeight(inputNum);
			}
		}
		for(int k=0;k<this.hiddenNum;k++){
			for(int k1=0;k1<this.hiddenNum;k1++){
				this.TWeight[k][k1] = randomSqrtWeight(hiddenNum);
			}	
		}
		for(int i=0 ;i<this.outputWeight.length;i++){
			for(int j=0;j<this.outputWeight[i].length;j++){
				this.outputWeight[i][j]=randomSqrtWeight(hiddenNum);
			}
		}
	}
	public void setTs(int t){//t为数组大小,时间序列大小。
		 this.hiddenTout = new double[t][this.hiddenNum];
	}
	public void train(TrainDataSupervised<double[]> trainDataSupervised){
			setTs(trainDataSupervised.inputVec.size());
			for(int j=0;j<trainDataSupervised.inputVec.size();j++){//个体数据序列化vecList列表
				this.inputVec = trainDataSupervised.inputVec.get(j);//个体序列化单个时间数据向量
				propagateForwordUnit(j);
			}
			propagateBackUnit$realTime(trainDataSupervised);
	}
	public double[] simulate(TrainDataSupervised<double[]> trainDataSupervised){
		setTs(trainDataSupervised.inputVec.size());
		for(int j=0;j<trainDataSupervised.inputVec.size();j++){//个体数据序列化vecList列表
			this.inputVec = trainDataSupervised.inputVec.get(j);//个体序列化单个时间数据向量
			propagateForwordUnit(j);
		}
		for(int j=0;j<this.outputNum;j++){
			this.outputVec[j] = this.outputNet[j].out;
		}
		return this.outputVec;
	}
	/**
	 * 默认k代表隐藏层，i代表输入层 ，j代表输出层。k1代表后一时刻，k代表前一时刻，在此处k1代表当前时刻。
	 *@author:zxh
	 *@time:2018年1月19日下午1:08:12
	 */
	public void propagateForwordUnit(int t){//t从零开始
		for(int k1=0;k1<this.hiddenNum;k1++){
			double sum = 0.0;double tsum=0.0;
			for(int i=0;i<this.inputNum;i++){
				sum += this.inputVec[i]*this.inputWeight[i][k1];
			}
			for(int k=0;k<this.hiddenNum;k++){
				if(t!=0){
					tsum += +this.hiddenTout[t-1][k]*this.TWeight[k][k1];
				}else{
					tsum=0.0;
				}
			}
			this.hiddenNet[k1].in(sum+tsum);
			this.hiddenNet[k1].out();
		}
		//存储隐含层的值，为t+1时刻准备数据。
		for(int k=0;k<this.hiddenNum;k++){
			this.hiddenTout[t][k] = this.hiddenNet[k].out;
		}
		for(int j=0;j<this.outputNum;j++){
			double sum = 0.0;
			for(int k=0;k<this.hiddenNum;k++){
				sum+=this.outputWeight[k][j]*this.hiddenNet[k].getOut();
			}
			this.outputNet[j].in(sum);
			this.outputNet[j].out();
		}
	}
	/**
	 * Th'(S1)(Y-Y')
	 * BPTT的Real_Time方法
	 * 函数求导使用的是tansig函数1-TANSIG(X)^2
	 *@author:zxh
	 *@time:2018年1月19日下午4:12:24
	 */
	public void propagateBackUnit$realTime(TrainDataSupervised<double[]> data){
		double[][] hiddenTDelta=new double[data.inputVec.size()][this.hiddenNum];
		double[][] hiddenDeltaWeight = new double[this.hiddenNum][this.hiddenNum];
		double[][] inputDeltaWeight = new double[this.inputNum][this.hiddenNum];
		double[] outputDelta = new double[this.outputNum];
		double n= 0.1;//学习速率；
		for(int j=0;j<this.outputNum;j++){
			outputDelta[j] = (1-this.outputNet[j].out*this.outputNet[j].out)*(data.outputMean[j]-this.outputNet[j].out);
		}
		double[] endHiddenDelta = new double[this.hiddenNum];
		int endT = data.inputVec.size()-1;
		for(int k=0;k<this.hiddenNum;k++){
			for(int j=0;j<this.outputNum;j++){
				endHiddenDelta[k] += (1-this.hiddenTout[endT][k]*this.hiddenTout[endT][k])*this.outputWeight[k][j]*outputDelta[j];
			}
		}
		//计算每个时刻delta
		for(int t=endT;t>=0;t--){
			double[] outputMeanT = data.inputVec.get(t);
			//k1是下一时刻的隐含层，向前一时刻反向传播
			for(int k=0;k<this.hiddenNum;k++){
				if(t==endT){
					hiddenTDelta[t][k] = endHiddenDelta[k];
				}else{
					for(int k1=0;k1<this.hiddenNum;k1++){
						hiddenTDelta[t][k] += (1-this.hiddenTout[t][k]*this.hiddenTout[t][k])*hiddenTDelta[t+1][k1]*this.TWeight[k][k1];
					}
				}
			}
		}
		//计算总隐藏层误差delta ,考虑学习速率n的影响
		for(int t=endT;t>=0;t--){
			for(int k=0;k<this.hiddenNum;k++){
				for(int k1=0;k1<this.hiddenNum;k1++){
					hiddenDeltaWeight[k][k1] =hiddenDeltaWeight[k][k1] + n*hiddenTDelta[t][k1]*this.hiddenTout[t][k];
				}
			}
			for(int i=0;i<this.inputNum;i++){
				for(int k =0;k<this.hiddenNum;k++){
					inputDeltaWeight[i][k] +=n*hiddenTDelta[t][k]*data.inputVec.get(t)[i];
				}
			}
		}
		//更新隐藏层之间权重
		for(int k=0;k<this.hiddenNum;k++){
			for(int k1=0;k1<this.hiddenNum;k1++){
				this.TWeight[k][k1] += hiddenDeltaWeight[k][k1];
			}
		}
		//更新输入层权重
		for(int i=0;i<this.inputNum;i++){
			for(int k =0;k<this.hiddenNum;k++){
				this.inputWeight[i][k] += inputDeltaWeight[i][k];
			}
		}
		for(int j=0;j<this.outputNum;j++){
			for(int k =0;k<this.hiddenNum;k++){
				this.outputWeight[k][j]+=n*outputDelta[j]*this.hiddenTout[endT][k];
			}
		}
	}
	
	@Override
	public String toString() {
		String result = "";
		try {
			result += "RecurrentNN [\n";
			result += "input:"+objectMapper.writeValueAsString(this.inputVec)+"\n";
			result += "hiddenout:"+objectMapper.writeValueAsString(this.hiddenNet)+"\n";
			result += "output:"+objectMapper.writeValueAsString(this.outputNet)+"\n";
			result += "]\n";
		} catch (JsonProcessingException e) {e.printStackTrace();}
		return  result;
	}
	public void printHiddenWeight(){
		ObjectMapper objectMapper2 = new ObjectMapper();
		String result = "";
		try {
			result += "RecurrentNN hiddenweight[\n";
			result += ""+objectMapper2.writeValueAsString(this.outputWeight).replaceAll("]", "]\n")+"\n";
			result += "]\n";
		} catch (JsonProcessingException e) {e.printStackTrace();}
		System.out.println(result);
	}
	public void printNet(){
		String result = toString();
		System.out.println(result);
	}
	public static void main(String[] args) throws JsonProcessingException{
		String[] arr = new String[]{"600106","300305","000039","002441","300005","300004","600362"};
		double[] mean = new double[4];
		int minserialNum =0;int minhiddenNum=0;;double var =100.0;
		ArrayList<StockStructure> arrayList = new ArrayList<StockStructure>();
		for(int i=0;i<arr.length;i++){
			StockStructure list = Http.getDataFromEastMoney(arr[i]);
			arrayList.add(list);
		}
		for(int serialNum=6;serialNum<20;serialNum+=1){
			ArrayList<double[]> templist = arrayList.get(0).list;
			ArrayList<double[]> meanserial = new ArrayList<double[]>();
			for(int m =templist.size()-serialNum-2;m<templist.size()-2;m++){
				meanserial.add(templist.get(m));
			}
			mean = templist.get(templist.size()-1);
			for(int hiddenNum=4;hiddenNum<20;hiddenNum+=1){
				//ArrayList<Double> arrayList = new ArrayList<Double>();
				double sum = 0.0;
				for(int i = 0;i<100;i++){
					double[] testStockList = testStockList(hiddenNum, serialNum,arrayList,meanserial);
					double variance = variance(testStockList,mean);
					sum+=variance;
					//arrayList.add(variance);
				}
				if(var>sum/100.0){var=sum/100.0;minserialNum = hiddenNum;minserialNum = serialNum;}
				System.out.println("@:serialNum"+serialNum+"     hiddenNum:"+hiddenNum+"     var:"+sum/100.0);
			}
		}
		System.out.println("@min:serialNum"+minserialNum+"     hiddenNum:"+minhiddenNum+"     var:"+var);
		/*String[] arr = new String[]{"600106","300305","000039"};
		RecurrentNN recurrentNN = new RecurrentNN(4, 4, 12);
		for(int i=0;i<arr.length;i++){
			StockStructure list = Http.getDataFromEastMoney(arr[i]);
			recurrentNN =RecurrentNN.trainList(recurrentNN, list, 10);
			System.out.println(recurrentNN.toString());
		}
		ArrayList<double[]> arrayList2 = new ArrayList<double[]>();
		arrayList2.add(new double[]{-0.40406101782088516,0.0,0.9091372900969894,-0.10101525445521906});
		arrayList2.add(new double[]{-0.12149134784615989,0.668202413153866,0.668202413153866,-0.3037283696153916});
		arrayList2.add(new double[]{0.30860669992418965,0.30860669992417594,0.46291004988626394,-0.7715167498104673});
		arrayList2.add(new double[]{-0.16744367165578494,0.5581455721859482,0.7814038010603264,-0.22325822887437827});
		TrainDataSupervised<double[]> trainDataSupervised = new TrainDataSupervised<double[]>(null, arrayList2);
		double[] simulate = recurrentNN.simulate(trainDataSupervised);
		System.out.println(JSONArray.fromObject(simulate));*/
		//testbasic();
	}
	public static ArrayList<HashMap<String ,Number>> analysisArgs(int maxSerialNum,int maxhiddenNum,ArrayList<StockStructure> arrayList){
		ArrayList<HashMap<String ,Number>> result = new ArrayList<HashMap<String ,Number>>();
		double[] mean = new double[4];
		int minserialNum =0;int minhiddenNum=0;;double var =100.0;
		for(int serialNum=6;serialNum<=maxSerialNum;serialNum+=1){
			ArrayList<double[]> templist = arrayList.get(0).list;
			ArrayList<double[]> meanserial = new ArrayList<double[]>();
			for(int m =templist.size()-serialNum-2;m<templist.size()-2;m++){
				meanserial.add(templist.get(m));
			}
			mean = templist.get(templist.size()-1);
			for(int hiddenNum=4;hiddenNum<=maxhiddenNum;hiddenNum+=1){
				//ArrayList<Double> arrayList = new ArrayList<Double>();
				double sum = 0.0;
				for(int i = 0;i<100;i++){
					double[] testStockList = testStockList(hiddenNum, serialNum,arrayList,meanserial);
					double variance = variance(testStockList,mean);
					sum+=variance;
					//arrayList.add(variance);
				}
				if(var>sum/100.0){var=sum/100.0;minserialNum = hiddenNum;minserialNum = serialNum;}
				HashMap<String, Number> map = new HashMap<String ,Number>();
				map.put("serialNum",serialNum);
				map.put("hiddenNum",hiddenNum);
				map.put("value",sum/100.0);
				result.add(map);
				System.out.println("@:serialNum"+serialNum+"     hiddenNum:"+hiddenNum+"     var:"+sum/100.0);
			}
		}
		System.out.println("@min:serialNum"+minserialNum+"     hiddenNum:"+minhiddenNum+"     var:"+var);
		return result;
	}
	public static ArrayList<HashMap<String ,Number>> analysisHiddenNum(int serialNum,int maxhiddenNum,ArrayList<StockStructure> arrayList){
		ArrayList<HashMap<String ,Number>> result = new ArrayList<HashMap<String ,Number>>();
		double[] mean = new double[4];
		int minserialNum =0;int minhiddenNum=0;;double var =100.0;
			ArrayList<double[]> templist = arrayList.get(0).list;
			ArrayList<double[]> meanserial = new ArrayList<double[]>();
			for(int m =templist.size()-serialNum-2;m<templist.size()-2;m++){
				meanserial.add(templist.get(m));
			}
			mean = templist.get(templist.size()-1);
			for(int hiddenNum=4;hiddenNum<=maxhiddenNum;hiddenNum+=1){
				//ArrayList<Double> arrayList = new ArrayList<Double>();
				double sum = 0.0;
				for(int i = 0;i<100;i++){
					double[] testStockList = testStockList(hiddenNum, serialNum,arrayList,meanserial);
					double variance = variance(testStockList,mean);
					sum+=variance;
					//arrayList.add(variance);
				}
				if(var>sum/100.0){var=sum/100.0;minserialNum = hiddenNum;minserialNum = serialNum;}
				HashMap<String, Number> map = new HashMap<String ,Number>();
				map.put("serialNum",serialNum);
				map.put("hiddenNum",hiddenNum);
				map.put("value",sum/100.0);
				result.add(map);
				System.out.println("@:serialNum"+serialNum+"     hiddenNum:"+hiddenNum+"     var:"+sum/100.0);
			}
		System.out.println("@min:serialNum"+minserialNum+"     hiddenNum:"+minhiddenNum+"     var:"+var);
		return result;
	}
	public static ArrayList<StockStructure> getData(){
		String[] arr = new String[]{"600106","300305","000039","002441","300005","300004","600362"};
		double[] mean = new double[4];
		int minserialNum =0;int minhiddenNum=0;;double var =100.0;
		ArrayList<StockStructure> arrayList = new ArrayList<StockStructure>();
		for(int i=0;i<arr.length;i++){
			StockStructure list = Http.getDataFromEastMoney(arr[i]);
			arrayList.add(list);
		}
		return arrayList;
	}
	public static double variance(double[] o,double[] mean){
		double var = 0.0;
		for(int i=0;i<o.length;i++){
			var+=(o[i]-mean[i])*(o[i]-mean[i]);
		}
		return var ;
	}
	public static void testbasic() throws JsonProcessingException{
		RecurrentNN recurrentNN = new RecurrentNN(2,2,4);
		ArrayList<double[]> arrayList3 = new ArrayList<double[]>();
		ArrayList<double[]> arrayList4 = new ArrayList<double[]>();
		arrayList3.add(new double[]{1.0,0.0});
		arrayList3.add(new double[]{0.0,1.0});
		arrayList4.add(new double[]{0.0,1.0});
		arrayList4.add(new double[]{1.0,0.0});
		TrainDataSupervised<double[]> trainDataSupervised3 = new TrainDataSupervised<double[]>(new double[]{1.0,0.0}, arrayList3);
		TrainDataSupervised<double[]> trainDataSupervised4 = new TrainDataSupervised<double[]>(new double[]{0.0,1.0}, arrayList4);
		for(int i=0;i<100;i++){
		recurrentNN.train(trainDataSupervised3);
		//System.out.println(recurrentNN.toString());
		//recurrentNN.printHiddenWeight();
		recurrentNN.train(trainDataSupervised4);
		//System.out.println(recurrentNN.toString());
		//recurrentNN.printHiddenWeight();
		}
		double[] simulate = recurrentNN.simulate(trainDataSupervised4);
		recurrentNN.printHiddenWeight();
		System.out.println(JSONArray.fromObject(simulate));
	}
	public static void testExample(){
		/*
		 * [-0.40406101782088516,0.0,0.9091372900969894,-0.10101525445521906],
		 * [-0.12149134784615989,0.668202413153866,0.668202413153866,-0.3037283696153916],
		 * [0.30860669992418965,0.30860669992417594,0.46291004988626394,-0.7715167498104673],
		 * [-0.16744367165578494,0.5581455721859482,0.7814038010603264,-0.22325822887437827],
		 * [0.0,-0.27668578554642964,0.830057356639289,-0.48420012470625345]
		 */
		StockStructure dataFromEastMoney = Http.getDataFromEastMoney("300305");
		int size = dataFromEastMoney.size;
		RecurrentNN recurrentNN =null;
			recurrentNN = new RecurrentNN(size,size,4);
		int serialSize=4;
		ArrayList<double[]> arrayList2 = new ArrayList<double[]>();
		arrayList2.add(new double[]{-0.40406101782088516,0.0,0.9091372900969894,-0.10101525445521906});
		arrayList2.add(new double[]{-0.12149134784615989,0.668202413153866,0.668202413153866,-0.3037283696153916});
		arrayList2.add(new double[]{0.30860669992418965,0.30860669992417594,0.46291004988626394,-0.7715167498104673});
		arrayList2.add(new double[]{-0.16744367165578494,0.5581455721859482,0.7814038010603264,-0.22325822887437827});
		for(int i=0;i<dataFromEastMoney.list.size()-serialSize-1;i++){
			ArrayList<double[]> arrayList = new ArrayList<double[]>();
			for(int j=i;j<serialSize+i;j++){
				arrayList.add(dataFromEastMoney.list.get(j));
			}
			TrainDataSupervised<double[]> trainDataSupervised = new TrainDataSupervised<double[]>(dataFromEastMoney.list.get(serialSize+i), arrayList);
			recurrentNN.train(trainDataSupervised);
		}
		TrainDataSupervised<double[]> trainDataSupervised = new TrainDataSupervised<double[]>(null, arrayList2);
		double[] simulate = recurrentNN.simulate(trainDataSupervised);
		System.out.println(JSONArray.fromObject(simulate));
	}
	public static double[] testStockList(int hiddenNum ,int serialNum ,ArrayList<StockStructure> arr,ArrayList<double[]> meanserial){
		
		RecurrentNN recurrentNN = new RecurrentNN(4, 4, hiddenNum);
		for(int i=0;i<arr.size();i++){
			StockStructure list = arr.get(i);
			recurrentNN =RecurrentNN.trainList(recurrentNN, list, serialNum);
			//System.out.println(recurrentNN.toString());
		}
		TrainDataSupervised<double[]> trainDataSupervised = new TrainDataSupervised<double[]>(null, meanserial);
		double[] simulate = recurrentNN.simulate(trainDataSupervised);
		//System.out.println(JSONArray.fromObject(simulate));
		return simulate;
	} 
	public static RecurrentNN trainList(RecurrentNN rnn,StockStructure arr,int serialSize){
		for(int i=0;i<arr.list.size()-serialSize-1;i++){
			ArrayList<double[]> arrayList = new ArrayList<double[]>();
			for(int j=i;j<serialSize+i;j++){
				arrayList.add(arr.list.get(j));
			}
			TrainDataSupervised<double[]> trainDataSupervised = new TrainDataSupervised<double[]>(arr.list.get(serialSize+i), arrayList);
			rnn.train(trainDataSupervised);
		}
		return rnn;
	}
}
