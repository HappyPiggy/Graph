import java.util.ArrayList;
import java.util.List;

public class Vertice implements Comparable<Vertice>{
        
        private String description;
        private int distance;
        private boolean visited = false;
        private Vertice father;
        
        //±ß¼¯ µã¼¯
        private List<Edge> edges = new ArrayList<Edge>();
        private List<Vertice> neighbors = new ArrayList<Vertice>();
        
        public void SetDescription(String name){
                this.description = name;
        }
        
        public String GetDescription(){
                return description;
        }
        
        public void Visit (){
                this.visited = true;
        }
        
        public boolean CheckVisit(){
                return visited;
        }
        
        public void SetDistance(int distance){
                this.distance = distance;
        }
        
        public int GetDistance(){
                return this.distance;
        }
        
        public void SetFather(Vertice father){
                this.father = father;
        }
        
        public Vertice GetFather(){
                return this.father;
        }

        public void SetNeighbors(List<Vertice> neighbors) {
                this.neighbors.addAll(neighbors);
        }
        
        public List<Vertice> GetNeighbors(){
                return this.neighbors;
        }
        
        public void  SetEdges(List <Edge> edges){
                this.edges.addAll(edges);
        }
        
        public List<Edge> GetEdges() {
                return edges;
        }

        public int compareTo(Vertice vertice) {
                  if(this.GetDistance() < vertice.GetDistance()) 
                	  return -1;
          else if(this.GetDistance() == vertice.GetDistance()) 
        	  		  return 0;
          
          return 1;
        }
        
        @Override
        public boolean equals(Object obj) {
                if(obj instanceof Vertice){
                        Vertice vRef = (Vertice) obj;
                        if(this.GetDescription().equals(vRef.GetDescription())) return true;
                }
                return false;
        }
        
        @Override
        public String toString() {
                String s = " ";
                s+= this.GetDescription();
                return s;
        }
        
        
        
        
}