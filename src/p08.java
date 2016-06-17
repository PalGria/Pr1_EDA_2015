/*Author Jose Manuel Palau Alegria
Se crea un objeto de tipo DiccA, se invoca su método leeDiccA sobre un diccionario sin repetidas de ningún tipo. Se invoca su método busqueda con diferentes
palabras, de manera que unas están en el diccionario y otras no 
Se crea un objeto de tipo DiccA, se invoca su método leeDiccA sobre un diccionario sin repetidas de ningún tipo. Se invoca su método busquedaOptima con
diferentes palabras, de manera que unas están en el diccionario y otras no.
 */
public class p08 {

	public static void main(String[] args){
	    DiccA diccio=new DiccA();
	    if(args.length>=1){
	      diccio.leeDiccA(args[0]);
	      
	      diccio.borraPalabra("torrezon");
	      System.out.println("busqueda matter " +diccio.busqueda("matter"));
	      System.out.println("busqueda low " +diccio.busqueda("low"));
	      System.out.println("busqueda toy " +diccio.busqueda("toy"));
	      System.out.println("busqueda optima matter " +diccio.busquedaOptima("matter"));
	      System.out.println("busqueda optima low " +diccio.busquedaOptima("low"));
	      System.out.println("busqueda optima toy " +diccio.busquedaOptima("toy"));
	      System.out.println("busqueda fallida " +diccio.busqueda("torrezno"));
	      System.out.println("busqueda optima fallida " +diccio.busquedaOptima("torrezno"));
	      System.out.println("busqueda optima TEM(ultima palabra) " +diccio.busquedaOptima("TEM"));
	      System.out.println("busqueda TEM(ultima palabra) " +diccio.busqueda("TEM"));
	      System.out.println("busqueda optima adress(primera palabra) " +diccio.busquedaOptima("adress"));
	      System.out.println("busqueda adress(primera palabra) " +diccio.busqueda("adress"));


	    }
	    else
	        System.out.println("Forma uso: java p05 p06.dic");

	}
}
