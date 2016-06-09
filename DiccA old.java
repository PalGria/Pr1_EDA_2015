//DNI 48727303D PALAU ALEGRIA, JOSE MANUEL

import java.io.*;
public class DiccA {
private char[] lenguas;
private Palabra[] dicc;
private Palabra[] diccord; //almacena diccionario ordenado por origen
private int last, nlenguas; //ultima posicion libre
//constructor por defecto
public DiccA(){
	dicc = new Palabra[10];
	diccord = new Palabra[10];
	nlenguas = -1;
}
//lee diccA leera el texto en el fichero que le pasaremos en f
public void leeDiccA(String f){
	
	FileReader fichero = null ;
	BufferedReader lectura = null ;
	try {
		// esto podria ir al metodo abre
		fichero = new FileReader (f);
		lectura = new BufferedReader ( fichero );
		//leo linea a linea
		String linea = lectura.readLine ();
		// bucle lectura hasta final de fichero
		int i = 0;
		while ( linea != null ) {
			this.procesaLinea(linea, i);
			linea = lectura.readLine(); // procesar linea
			i++; 
			
		}
	} catch ( IOException e ){
		System.err.println(" Error con archivo");
		System.exit(0);
	}
	// esto podria ir al metodo cerrar
	try {
		if ( fichero!=null)
			fichero.close();
		if ( lectura != null )
			lectura . close ();
	} catch (IOException ex){
		System.out.println(ex);
	}
}
public void procesaLinea(String linea, int numLinea) {

	//si la linea que me llega es 0, entonces es el numero de idiomas
	if(numLinea==0){
		nlenguas= Integer.parseInt(linea);
		lenguas = new char[nlenguas];
	}
	//si la linea que me llega es 1, son los idiomas de las palabras
	if(numLinea==1){
		//defino separador " " 
		String separador= " ";
		//separo la linea con el separador, para tener un array de idiomas s
		String [] s = linea.split( separador ) ;
		for(int i=0; i<nlenguas;i++){
			//y meto los idiomas en lenguas
			lenguas[i]= s[i].charAt(0);
		}
	}
	//el resto de lineas seran palabras
	if(numLinea>1){
		//defino separadores
		String separador1= "[ ]*\\*[ ]*";
		String separador2= "/";
		
		String[] s = linea.split(separador1);
		String[] acep;
		Palabra p=new Palabra(s[0], nlenguas);
		p.setLenguas(lenguas);
		for(int i=0;i<s.length-1;i++){
			
			acep = s[i+1].split(separador2);
			for(int j=0; j<acep.length;j++){
				if (acep[j]!=null)
					p.agregaAcepcion(acep[j], lenguas[i]);	
			}
		}
		insertaPalabra(p);
	}
	
}
public boolean insertaPalabra(Palabra p){
	boolean ret = true, hueco = false, encontrado = false;
	if(last < dicc.length){
		hueco = true;
	}
	else{
		//hago dos diccionarios auxiliares
		Palabra[] diccaux = dicc;
		Palabra[] diccordaux = diccord;
		//amplio memoria, pero se borran los diccionarios
		dicc = new Palabra[dicc.length+10];
		diccord = new Palabra[dicc.length+10];
		//copio todos los archivos de diccaux a dicc
		for(int i = 0; i<diccaux.length; i++){
			dicc[i] = diccaux[i];
			diccord[i] = diccordaux[i];
		}
		hueco = true;

	}
	if(hueco == true){
		//meto la palabra en dicc
		for(int i=0; i<last ; i++){
			if(dicc[i].comparaPalabra(p)==0){
				dicc[i].setLenguas(lenguas);
				ret = dicc[i].combinaPalabra(p);
				encontrado = true;
				break;
			}
		}
		
		if(encontrado == true){
			//meto la palabra en diccord
			for(int i=0; i<last ; i++){
				if(diccord[i].comparaPalabra(p)==0){
					if(diccord[i].combinaPalabra(p)){
						ret = true;
					};
					break;
				}

			}
		}
		if(encontrado == false){
			//si no lo encuentro, meto la palabra al final de dicc y diccord y mando a diccord a que se ordene
			dicc[last] = p;
			diccord[last]  = p;
			ordenaDiccord();
			last++;
		}
	}
	return ret;
}

private void ordenaDiccord() {//ordeno usando el algoritmo de burbuja
	
	//vamos a sacar primero el n�mero de palabras que hay en el diccionario.
	int i=0;
	Palabra aux;
	while (i<diccord.length){
		if(diccord[i]==null){
			break;//si encontramos la �ltima palabra insertada salimos del bucle
		}
		i++;
	}
	for (int j=0;j<i;j++){
		for (int k=0; k<i-1;k++){
				if(diccord[j].getOrigen().compareToIgnoreCase(diccord[k].getOrigen())<0){
					aux=diccord[j];
					diccord[j]=diccord[k];
					diccord[k]=aux;
				}
		}
	}
}

public boolean borraPalabra(String s){
	boolean encontredicc=false;
	boolean encontrediccord=false;
	for(int i=0;i<last;i++){
		if (encontredicc == false && s.equalsIgnoreCase(dicc[i].getOrigen())){
			dicc[i]=null;
			dicc[i] = dicc[i+1];
			for (int j = i; j<dicc.length -1; j++){
				dicc[j] = dicc[j+1];
			}
			encontredicc=true;
		}
		if (encontrediccord ==false && s.equalsIgnoreCase(diccord[i].getOrigen())){
			diccord[i]=null;
			for (int j = i; j<diccord.length-1 ; j++){
				diccord[j] = diccord[j+1];
				if(diccord[j]==null){
					break;
				}
				
			}
			encontrediccord=true;
			last--;
		}
	}
	
	return encontrediccord;
}
public int busqueda(String s){
	for(int i=0;i<last;i++){
		if (s.equalsIgnoreCase(dicc[i].getOrigen())){
			return i;
		}
	}

	return -1;
	}
public int busquedaOptima(String s){
	//este método busca la cadena s en el diccionario y devuelve las comparaciones que tuvo que hacer (pero óptimamente)
	//para este caso implementaré una búsqueda binaria, consistente en dividir el array en dos para averiguar en que zona está del array
	
	int centro, max=last-1, min=0, i=0;
	//centro será el centro de donde estoy buscando
	//max es el rango máximo que estoy buscando
	//min es el rango mínimo 
	//i es el número de iteracciones que realiza el programa
	while (min<=max){
		i++;
		centro=(max+min)/2;			
		if (diccord[centro].getOrigen().compareToIgnoreCase(s)==0){ //si me devuelve 0, las palabras son las mismas, y no tengo que buscar más
			return i;
		}
		else{
			if(diccord[centro].getOrigen().compareToIgnoreCase(s)>0){//se devuelve un número mayor que uno, el objeto del diccionario es mayor que el que me pasan
				max=centro-1; //y por tanto, no estará más arriba que en el centro.
			}
			else{ //si devuelve un número menor que uno, la palabra buscada no estará más abajo del centro
				min=centro+1; // y por tanto el mínimo pasará a ser el centro
			}
		}
	}	
	return -1;

}

public String traduce1(String s, char l){
	String traduc = null;
	for (int i = 0; i<last; i++){
		if(s.equalsIgnoreCase(dicc[i].getOrigen())){
			traduc = dicc[i].getTraduccion(l);
		}
	}
	return traduc;
}
public String traduce2(String s, char l){
	//busca en el diccionario la palabra s y devuelve todas las traducciones
	//primero buscaré la palabra con el código de búsqueda óptima
	int centro, max=last-1, min=0;
	//centro será el centro de donde estoy buscando
	//max es el rango máximo que estoy buscando
	//min es el rango mínimo 
	//i es el número de iteracciones que realiza el programa
	while (min<=max){
		centro=(max+min)/2;			
		if (diccord[centro].getOrigen().compareToIgnoreCase(s)==0){ //si me devuelve 0, las palabras son las mismas, y no tengo que buscar más
			return diccord[centro].getTraducciones(l); //devuelvo todas las traducciones de la palabra
		}
		else{
			if(diccord[centro].getOrigen().compareToIgnoreCase(s)>0){//se devuelve un número mayor que uno, el objeto del diccionario es mayor que el que me pasan
				max=centro-1; //y por tanto, no estará más arriba que en el centro.
			}
			else{ //si devuelve un número menor que uno, la palabra buscada no estará más abajo del centro
				min=centro+1; // y por tanto el mínimo pasará a ser el centro
			}
		}
	}

	return null;
	
}	
public void muestraDiccA(int i){
	if ( i== 0){
		for (int j= 0 ; j<last ; j++){
			dicc[j].escribeInfo();
		}
	}
	if ( i== 1){
		for (int j= 0 ; j<last ; j++){
			if(diccord[j]!=null){
				diccord[j].escribeInfo();
			}
		}
	}

}

public void muestraDiccA(int i, int j){
	if ( i== 0){
		for (int l= 0 ; l<last && l<j ; l++){
			dicc[l].escribeInfo();
		}
	}
	if ( i== 1){
		for (int l= 0 ; l<last && l<j ; l++){
			if(diccord[l]!=null){
				diccord[l].escribeInfo();
			}
		}
	}

}

public void muestraDiccA(int i, int j, char l){
	if ( i== 0){
		for (int k= 0 ; k<last && k<j ; k++){
			System.out.print(dicc[k].getOrigen()+":");
			if(dicc[k].getTraducciones(l)!=null){
				System.out.println(dicc[k].getTraduccion(l));
			}
			else{
				System.out.println();
			}
		}
	}
	if ( i== 1){
		for (int k= 0 ; k<last && k<j ; k++){
			if(diccord[k]!=null){
				System.out.print(diccord[k].getOrigen()+":");
				if(diccord[k].getTraducciones(l)!=null){
					System.out.println(diccord[k].getTraduccion(l));
				}
				else{
					System.out.println();
				}
				
			}
		}
	}

}
public char[] devuelveLengua() {
	return lenguas;
}

}