package main;

/*
 * ����һ������ ���Դ���vorlesung ���͵����� ������
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
