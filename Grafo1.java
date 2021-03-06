
package Plaza;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;



public class Grafo1 {

	    char[]  nodos; 
	    double[][] grafo;
            

	    String  rutaMasCorta;                           
	    int     longitudMasCorta = Integer.MAX_VALUE;   
	     List<Nodo>  listos=null;                        

	 

	    

	    Grafo1(String serieNodos) {
	        nodos = serieNodos.toCharArray();
	        grafo = new double[nodos.length][nodos.length];
	    }


	    public void agregarRuta(char origen, char destino, double d) {
	        int n1 = posicionNodo(origen);
	        int n2 = posicionNodo(destino);
	        grafo[n1][n2]=d;
	        grafo[n2][n1]=d;
	     
	     System.out.println(grafo[n1][n2]);
	        
	    }

	 

	    

	    private int posicionNodo(char nodo) {
	        for(int i=0; i<nodos.length; i++) {
	            if(nodos[i]==nodo) return i;
	        }
	        return -1;
	    }

	    
	    public String encontrarRutaMinimaDijkstra(char inicio, char fin) {
	        encontrarRutaMinimaDijkstra(inicio);
	        Nodo tmp = new Nodo(fin);
	        if(!listos.contains(tmp)) {
	            System.out.println("Error, nodo no alcanzable");
	            return "Bye";
	        }
	        tmp = listos.get(listos.indexOf(tmp));
	        double distancia = tmp.distancia;  
	        Stack<Nodo> pila = new Stack<Nodo>();
	        while(tmp != null) {
	            pila.add(tmp);
	            tmp = tmp.procedencia;
	        }
	        String ruta = "";
	        while(!pila.isEmpty()) ruta+=(pila.pop().id + " ");
	        return distancia + ": " + ruta;
	    }

	 
	    public void encontrarRutaMinimaDijkstra(char inicio) {
	        Queue<Nodo>   cola = new PriorityQueue<Nodo>();
	        Nodo            ni = new Nodo(inicio);                 
	        listos = new LinkedList<Nodo>();
	        cola.add(ni);                  
	        while(!cola.isEmpty()) {        
	            Nodo tmp = cola.poll();    
	            listos.add(tmp);            
	            int p = posicionNodo(tmp.id);   
	            for(int j=0; j<grafo[p].length; j++) {  
	                if(grafo[p][j]==0) continue;        
	                if(estaTerminado(j)) continue;     
	                Nodo nod = new Nodo(nodos[j],tmp.distancia+grafo[p][j],tmp);	        
	                if(!cola.contains(nod)) {
	                    cola.add(nod);
	                    continue;
	                }	             
                for(Nodo x: cola) {	                   
	                    if(x.id==nod.id && x.distancia > nod.distancia) {
	                        cola.remove(x); 
	                        cola.add(nod);
	                        break;          
	                    }
	                }
	            }
	        }
	    }
	  

	    public boolean estaTerminado(int j) {
	        Nodo tmp = new Nodo(nodos[j]);
	        return listos.contains(tmp);
            }


	    public void encontrarRutaMinimaFuerzaBruta(char inicio, char fin) {
	        int p1 = posicionNodo(inicio);
	        int p2 = posicionNodo(fin);
	        Stack<Integer> resultado = new Stack<Integer>();
	        resultado.push(p1);
	        recorrerRutas(p1, p2, resultado);
	    }

	 


	    private void recorrerRutas(int nodoI, int nodoF, Stack<Integer> resultado){
	        if(nodoI==nodoF) {
int respuesta1= evaluar(resultado);
	            int respuesta = evaluar(resultado);
	            if(respuesta < longitudMasCorta) {
	               longitudMasCorta = respuesta;
	                rutaMasCorta     = "";
	               for(int x: resultado) rutaMasCorta+=(nodos[x]+" ");
	            }
	            return;
	        }
	        List<Integer> lista = new Vector<Integer>();
	        for(int i=0; i<grafo.length;i++) {
	            if(grafo[nodoI][i]!=0 && !resultado.contains(i))lista.add(i);
	        }
	        for(int nodo: lista) {
	            resultado.push(nodo);
	            recorrerRutas(nodo, nodoF, resultado);
	            resultado.pop();
	        }
	    }

	 

	    

	    public int evaluar(Stack<Integer> resultado) {
	        int  resp = 0;
                int resp2 = 0;
	        int[]   r = new int[resultado.size()];
                 int[]   re = new int[resultado.size()];
	        int     i = 0;
	        for(int x: resultado) r[i++]=x;
	        for(i=1; i<r.length; i++) resp+=grafo[r[i]][r[i-1]];
	        System.out.println(resp+=grafo[r[i]][r[i-1]]);
	        return resp;
  
            }

	    public static void main(String[] args) {
	        Grafo1 g = new Grafo1("abcdef");
System.out.println("Romblas a alameda");
	        g.agregarRuta('a','b', 1.6);
System.out.println("Alameda a Club plaza");
	        g.agregarRuta('b','d', 1.7);
System.out.println("Alameda a Patria");
	        g.agregarRuta('b','c',1.2);
System.out.println("Patria a Plaza mexico");
	        g.agregarRuta('c','e', 4.3);
System.out.println("Club plaza a Plaza mexico");
	        g.agregarRuta('d','e', 2.8);
System.out.println("Galerias a Plaza mexico");
	        g.agregarRuta('f','e', 18);
System.out.println("Romblas a Galerias");
	        g.agregarRuta('a','f', 9.2);

	   
	        char Elcomienzo = 'b';
	        char Elfinal    = 'd';
	       // char bajo=

	        String respuesta = g.encontrarRutaMinimaDijkstra(Elcomienzo, Elfinal);
               
	     
	        System.out.println("-MENU DE PLAZAS- \nºa Romblas \nºb Alameda \nºc Patria \nºd Club plaza \nºe Plaza mexico \nºf Galerias");
	        System.out.println("\nReferencia: Ruta mas corta de ramblaz a Plaza mexico\n"+"Suma Total(tambien con las rutas) :"+ respuesta);
             
                
	        
	       

	    

}
        
}