package main;

/*
 * 创建一个泛型 可以带入vorlesung 类型的数据 并调用
 */
public class Tool <T> {

	private T e;
	
	public Tool(T e) {
		this.e= e;
	}
	
	public T getT() {
		return e;
	}
	public void setT(T e) {
		this.e = e;
	}
}
