package petri.node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import petri.elements.Connection;

public abstract class Node {

	public List<Connection> connList;//有向弧集合
	
	public Node() {
		connList = new ArrayList<Connection>();
	}
	//添加指向节点node的有向弧
	public abstract void addConn(Node node);
	//移除指向节点node的有向弧
	public void removeConn(Node node) {
		Iterator<Connection> it = connList.iterator();
		while(it.hasNext()) {
			Connection conn = (Connection) it.next();
			if(conn.getNextNode() == node)
				it.remove();
		}
	}
	
	public int getConnNum() {
		return connList.size();
	}
}
