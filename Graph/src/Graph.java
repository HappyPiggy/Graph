import java.util.ArrayList;
import java.util.List;

public class Graph {

	private List<Vertice> graph = new ArrayList<Vertice>();

	//设定顶点
	public void SetVertices(List<Vertice> vertices) {
		this.graph.addAll(vertices);
	}

	//增加顶点
	public void AddVertex(Vertice newVertex) {
		this.graph.add(newVertex);
	}

	//获取当前顶点
	public List<Vertice> GetVertices() {
		return this.graph;
	}

	// 找到名字相同点 返回这个名字....
	public Vertice FindVertice(String name) {

		for (int i = 0; i < this.GetVertices().size(); i++) {

			if (name.equalsIgnoreCase(this.GetVertices().get(i).GetDescription())) {

				return this.GetVertices().get(i);

			}
		}

		return null;

	}

}