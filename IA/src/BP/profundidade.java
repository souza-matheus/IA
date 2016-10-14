package BP;

import java.util.ArrayList;
import java.util.Stack;

public class profundidade {
	static ArrayList<No> no = new ArrayList<No>();

	static class No {
		String data;
		boolean visited;

		No(String data) {
			this.data = data;

		}
	}

	public ArrayList<No> procurarVizinhos(int matriz[][], No x) {
		int noIndex = -1;
		ArrayList<No> vizinhos = new ArrayList<No>();
		for (int i = 0; i < no.size(); i++) {
			if (no.get(i).equals(x)) {
				noIndex = i;
				break;
			}
		}
		if (noIndex != -1) {
			for (int j = 0; j < matriz[noIndex].length; j++) {
				if (matriz[noIndex][j] == 1) {
					vizinhos.add(no.get(j));
				}
			}
		}
		return vizinhos;
	}

	public void fazerProfundidade(int matriz[][], No no) {
		System.out.print(no.data + "\t");
		ArrayList<No> vizinhos = procurarVizinhos(matriz, no);
		for (int i = 0; i < vizinhos.size(); i++) {
			No n = vizinhos.get(i);
			if (n != null && !n.visited) {
				fazerProfundidade(matriz, n);
				n.visited = true;
			}
		}
	}
	public static void main(String arg[]) {
		No noA = new No("A");
		No noB = new No("B");
		No noC = new No("C");
		No noD = new No("D");
		No noE = new No("E");
		No noF = new No("F");
		No noG = new No("G");
		No noH = new No("H");

		no.add(noA);
		no.add(noB);
		no.add(noC);	
		no.add(noD);
		no.add(noE);
		no.add(noF);
		no.add(noG);
		no.add(noH);
		int matriz[][] = { 
				{ 0, 1, 1, 0, 0, 0, 0, 0 }, // No 1: A
				{ 0, 0, 0, 1, 1, 0, 0, 0 }, // No 2 :B
				{ 0, 0, 0, 0, 0, 1, 0, 0 }, // No 3: C
				{ 0, 0, 0, 0, 0, 0, 1, 0 }, // No 4: D
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, // No 5: E
				{ 0, 0, 0, 0, 0, 0, 0, 1 }, // No 6: F
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, // No 7: G
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, // No 8: H
		};
		profundidade testeProfundidade = new profundidade();
		System.out.println("Busca em profundidade: ");
		testeProfundidade.fazerProfundidade(matriz, noA);
		
	}
}
