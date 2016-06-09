/**
* @author Jose Manuel Palau Alegria
*
Se crea un objeto de tipo DiccA, se invoca su método leeDiccA sobre un diccionario 
sin palabras repetidas de ningún tipo. Se invoca muestraDicc para que muestre el
 diccionario original indicándole cada vez un número de líneas y un idioma
*/
public class p06 {
  public static void main(String[] args){
    DiccA diccio=new DiccA();
    if(args.length>=1){
      diccio.leeDiccA(args[0]);
      System.out.println("-----------------------Diccionario leido-----------------------");
      diccio.muestraDiccA(1);
      System.out.println("-----------------------Diccionario ordenado-----------------------");
      diccio.muestraDiccA(1);
      System.out.println("-----------------------Primeras diez palabras-----------------------");
      diccio.muestraDiccA(1, 10);
      //probamos cada idioma
      System.out.println("-----------------------Primeras 10 palabras a portugues ordenadas-----------------------");
      diccio.muestraDiccA(1, 10, 'P');    
      System.out.println("-----------------------Primeras 10 palabras a español ordenadas-----------------------");
      diccio.muestraDiccA(1, 10, 'P');    
      System.out.println("-----------------------Primeras 10 palabras a frances ordenadas-----------------------");
      diccio.muestraDiccA(1, 10, 'P');   
      //le metemos un idioma que no existe 
      //duda aqui... ¿Seria basket: o basket?
      System.out.println("-----------------------Primeras 10 palabras a sueco ordenadas-----------------------");
      diccio.muestraDiccA(1, 10, 'S');    
      System.out.println("-----------------------Primeras 10 palabras a portugues sin ordenar-----------------------");
      diccio.muestraDiccA(0, 10, 'P');    
      System.out.println("-----------------------Primeras 10 palabras a español sin ordenar-----------------------");
      diccio.muestraDiccA(0, 10, 'P');    
      System.out.println("-----------------------Primeras 10 palabras a frances sin ordenar-----------------------");
      diccio.muestraDiccA(0, 10, 'P');   
      //le metemos un idioma que no existe 
      //duda aqui... ¿Seria basket: o basket?
      System.out.println("-----------------------Primeras 10 palabras a sueco sin ordenar-----------------------");
      diccio.muestraDiccA(0, 10, 'S'); 
    }
    else
     System.out.println("Forma uso: java p05 p05.dic");
    
  }
}
