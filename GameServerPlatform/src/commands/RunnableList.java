package commands;

import java.util.LinkedList;

public class RunnableList {

	LinkedList<Runnable> list;
	
	private RunnableList() {
list=new LinkedList<>();
	}
	
	public static RunnableList getInstance(){
		return new RunnableList();
	}
	
	public RunnableList add(Runnable r){
		list.add(r);
		return this;
	}
	public void runAll(){
		list.forEach(r->r.run());
	}
	
}
