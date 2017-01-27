/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Plaza;

public class Nodo implements Comparable<Nodo> {

	    char id;

	    double  distancia   = Integer.MAX_VALUE;

	    Nodo procedencia = null;

	    Nodo(char x, double d, Nodo p) { id=x; distancia=d; procedencia=p; }

	    Nodo(char x) { this(x, 0, null); }

	    public int compareTo(Nodo tmp) { return (int) (this.distancia-tmp.distancia); }

	    public boolean equals(Object o) {

	        Nodo tmp = (Nodo) o;

	        if(tmp.id==this.id) return true;

	        return false;
            }
}