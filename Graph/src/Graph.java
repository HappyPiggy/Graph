import java.util.ArrayList;
import java.util.List;

public class Graph {

	private List<Vertice> graph = new ArrayList<Vertice>();

	//�趨����
	public void SetVertices(List<Vertice> vertices) {
		this.graph.addAll(vertices);
	}

	//���Ӷ���
	public void AddVertex(Vertice newVertex) {
		this.graph.add(newVertex);
	}

	//��ȡ��ǰ����
	public List<Vertice> GetVertices() {
		return this.graph;
	}

	// �ҵ�������ͬ�� �����������....
	public Vertice FindVertice(String name) {

		for (int i = 0; i < this.GetVertices().size(); i++) {

			if (name.equalsIgnoreCase(this.GetVertices().get(i).GetDescription())) {

				return this.GetVertices().get(i);

			}
		}

		return null;

	}

}