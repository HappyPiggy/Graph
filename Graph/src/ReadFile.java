import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadFile {

	public static List<Vertice> ReadGraph(String FileName) {

		Graph g = new Graph();
		Vertice v;
		File f = new File(FileName);
		String vertices[];
		String line;
		ArrayList<String[]> s1 = new ArrayList<String[]>();

		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(f));
			Map<String, Vertice> map = new HashMap<String, Vertice>();
			while ((line = br.readLine()) != null) {

				if (line.contains(",")) {
					s1.add(line.split("/"));
					vertices = s1.get(0)[0].split(",");

					v = (Vertice) map.get(vertices[0]);
					if (v == null)
						v = new Vertice();

					List<Vertice> vizinhosAtual = new ArrayList<Vertice>();
					List<Edge> arestasAtual = new ArrayList<Edge>();
					v.SetDescription(vertices[0]);
					map.put(vertices[0], v);

						for (int i = 1; i < vertices.length; i++) {
							Vertice vit;
							vit = map.get(vertices[i]);
							if (vit == null)
								vit = new Vertice();
							vit.SetDescription(vertices[i]);
							vizinhosAtual.add(vit);
							map.put(vertices[i], vit);

							Edge ait = new Edge(v, vit);
							ait.SetWeight(1);
							arestasAtual.add(ait);
						}
						v.SetNeighbors(vizinhosAtual);
						v.SetEdges(arestasAtual);

				}
				else {
					v = (Vertice) map.get(line);
					if (v == null)
						v = new Vertice();
					v.SetDescription(line);
					map.put(line, v);
				}
				g.AddVertex(v);
				s1.clear();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return g.GetVertices();
	}



}