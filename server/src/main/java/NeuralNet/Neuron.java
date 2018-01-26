package NeuralNet;

import NeuralNet.TansferFunction.TransferFunction;

public class Neuron {
	public double in;
	public double out;
	private TransferFunction func;
	public Neuron(TransferFunction func) {
		this.func = func;
	}
	public double out(){
		this.out =  func.func(in);
		return out;
	}
	public void in(double in){
		this.in =in;
	}
	public double getIn() {
		return in;
	}
	public double getOut() {
		return out;
	}
}
