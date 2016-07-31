import java.awt.*;
import java.awt.geom.*;
import java.util.List;

import javax.swing.*;

import org.jgraph.*;
import org.jgraph.graph.*;

import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

// resolve ambiguity
import org.jgrapht.graph.DefaultEdge;

 

public class DrawPanel
    extends JPanel
{
    private static final long serialVersionUID = 3256444702936019250L;
    private static final Dimension DEFAULT_SIZE = new Dimension(650, 320);

    private JGraphModelAdapter<String, MyEdge> jgAdapter;

    public DrawPanel(){
        this.setBackground(Color.white);
        this.setSize(650,320);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
     //  this.setResizable(true);
    }
     
    public void draw(List<Vertice> vertices){

        ListenableGraph<String, MyEdge> g =
            new ListenableDirectedMultigraph<String, MyEdge>(
                MyEdge.class);


        jgAdapter = new JGraphModelAdapter<String, MyEdge>(g);

        JGraph jgraph = new JGraph(jgAdapter);
        adjustDisplaySettings(jgraph);
        
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(jgraph,BorderLayout.CENTER);
        
        
        extracted();
        
        for(Vertice v:vertices){
        	g.addVertex(v.GetDescription().trim());
        }
        
        for(Vertice v1:vertices){
        	for(Edge a: v1.GetEdges())
        		g.addEdge(v1.GetDescription().trim(), a.getDestination().GetDescription().trim(), new MyEdge(""));
        }
        
        
        
        int size = vertices.size();
        int i = 0;
        int x = 10;
        int y = 50;
        
        for(Vertice v: vertices){
            
            positionVertexAt(v.GetDescription().trim(),x,y);
            x+=180;
            if(i==size/2){
            	x = 20;
            	y+=200;
            }
            	
            i++;
        }
        	
    }

	@SuppressWarnings("deprecation")
	private void extracted() {
		resize(DEFAULT_SIZE);
	}

    private void adjustDisplaySettings(JGraph jg)
    {
        jg.setPreferredSize(DEFAULT_SIZE);

        Color c = Color.white;
 

        jg.setBackground(c);
    }


    @SuppressWarnings("unchecked")
	private void positionVertexAt(Object vertex, int x, int y)
    {
        DefaultGraphCell cell = jgAdapter.getVertexCell(vertex);
        AttributeMap attr = cell.getAttributes();
        Rectangle2D bounds = GraphConstants.getBounds(attr);

        Rectangle2D newBounds =
            new Rectangle2D.Double(
                x,
                y,
                bounds.getWidth()+60,
                bounds.getHeight());

        GraphConstants.setBounds(attr, newBounds);

        AttributeMap cellAttr = new AttributeMap();
        cellAttr.put(cell, attr);
        jgAdapter.edit(cellAttr, null, null, null);
    }


    private static class ListenableDirectedMultigraph<V, E>
        extends DefaultListenableGraph<V, E>
        implements DirectedGraph<V, E>
    {
        private static final long serialVersionUID = 1L;

        ListenableDirectedMultigraph(Class<E> edgeClass)
        {
            super(new DirectedMultigraph<V, E>(edgeClass));
        }
    }
    
    class MyEdge extends DefaultEdge{
    	
    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String label;
    	
    	public MyEdge(String label) {
			this.label = label;
		}
    	
    	@Override
    	public String toString() {
    		return label;
    	}
    }
}
