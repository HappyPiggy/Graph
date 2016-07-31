public class Edge {

	private int weight; //Ȩ
	private Vertice origin;
	private Vertice destination;

	public Edge(Vertice v1, Vertice v2) {
		this.weight = 1;
		this.origin = v1;
		this.destination = v2;
	}

	public void SetWeight(int newWeight) {
		this.weight = newWeight;
	}

	public int getWeight() {
		return weight;
	}

	public void setDestination(Vertice destination) {
		this.destination = destination;
	}

	public Vertice getDestination() {
		return destination;
	}

	public void setOrigin(Vertice origin) {
		this.origin = origin;
	}

	public Vertice getOrigin() {
		return origin;
	}
}