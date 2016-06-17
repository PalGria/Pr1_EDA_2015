/**
* @author Jose Manuel Palau Alegria
*
Se crea un objeto de tipo DiccA, se invoca su método leeDiccA sobre un diccionario 
sin palabras repetidas de ningún tipo. Se invoca muestraDicc para que muestre el
 diccionario original indicándole cada vez un número de líneas y un idioma
*/
public class p11 {
  public static void main(String[] args){
    DiccA diccio=new DiccA();
    if(args.length>=1){
      diccio.leeDiccA(args[0]);
      System.out.println("-----------------------Diccionario leido-----------------------");
      diccio.muestraDiccA(1);
      System.out.println("-----------------------Diccionario ordenado-----------------------");
      diccio.muestraDiccA(1);
      System.out.println("-----------------------Mas de las palabras que hemos metido-----------------------");
      diccio.muestraDiccA(1, 200);


    }
    else
     System.out.println("Forma uso: java p05 p11.dic");
    
  }
}
