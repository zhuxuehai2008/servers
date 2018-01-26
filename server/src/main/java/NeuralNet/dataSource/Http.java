package NeuralNet.dataSource;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import NeuralNet.Entity.StockStructure;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Http {
	public static  void main(String [] args){
/*		String url="http://www.baidu.com";
		CloseableHttpClient httpClient  = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        HttpPost httpPost = new HttpPost(url);
        try {
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			resultString = EntityUtils.toString(entity,"utf-8");
			System.out.println(resultString);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		/*ArrayList<Integer> arrayList = new ArrayList<Integer>();
		ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
		arrayList.add(1);
		arrayList.add(2);
		arrayList.add(3);
		arrayList.add(4);
		for(int i=0;i<arrayList.size();i++){
			Integer integer = arrayList.get(i);
			arrayList2.add(integer);q
		}
		System.out.println(arrayList2);
		System.out.println(arrayList);*/
		getDataFromEastMoney("300305");
	}
	public static String httpGET(String url){
		CloseableHttpClient httpClient  = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		HttpGet httpPost = new HttpGet(url);
		try {
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			resultString = EntityUtils.toString(entity,"utf-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultString;
	}
	public static StockStructure getDataFromEastMoney(String stock){
		if(stock.toCharArray()[0]=='6'){
			stock+="1";
		}else{
			stock+="2";
		}
		//["2017-12-14 10:30,7.81,7.77,7.85,7.77,2181,170万,-","2017-12-14 11:30,7.77,7.81,7.81,7.77,1268,98.8万,0.51%"]
		//时间0，开盘1，收盘2，最高3，最低4。
		String url = "http://pdfm2.eastmoney.com/EM_UBG_PDTI_Fast/api/js?id=${id}&TYPE=k&js=(x)&rtntype=5&isCR=false&authorityType=fa&fsData1516867554146=fsData1516867554146";
		url = url.replace("${id}", stock);
		String httpGET = httpGET(url);
		JSONObject fromObject = JSONObject.fromObject(httpGET);
		JSONArray list = JSONArray.fromObject(fromObject.get("data"));
		String[] item = list.get(0).toString().split(",");
		Double YClose = Double.valueOf(item[2]);
		ArrayList<double[]> listParsed = new ArrayList<double[]>();
		ArrayList<String> listDate = new ArrayList<String>();
		for(int i=1;i<list.size();i++){
			item = list.get(i).toString().split(",");
			double[] it = new double[4];
			it[0]=Double.valueOf(item[1])-YClose;
			it[1]=Double.valueOf(item[2])-Double.valueOf(item[1]);
			it[2]=Double.valueOf(item[3])-Double.valueOf(item[1]);
			it[3]=Double.valueOf(item[4])-Double.valueOf(item[1]);
			listParsed.add(it);
			listDate.add(item[0].toString());
			YClose = Double.valueOf(item[2]);
		}
		ArrayList<double[]> normalizeList = normalizeList(listParsed);
		StockStructure stockStructure = new StockStructure(normalizeList, listDate,4);
		return stockStructure;
	}
	public static ArrayList<double[]> normalizeList(ArrayList<double[]> o){
		ArrayList<double[]> arrayList = new ArrayList<double[]>();
		for(int i=0;i<o.size();i++){
			double[] ds = o.get(i);
			arrayList.add(normalize(ds));
		}
		return arrayList;
	}
	public static double[] normalize(double[] v){
		double sum =0.0;
		double[] result  = new double[v.length];
		for(int i=0;i<v.length;i++){
			sum+=v[i]*v[i];
		}
		double sumsqrt = Math.sqrt(sum);
		for(int i=0;i<v.length;i++){
			if(sumsqrt!=0.0){
				result[i] = v[i]/sumsqrt;
			}else{
				result[i]=0.0;
			};
		}
		return result;
	}
}
