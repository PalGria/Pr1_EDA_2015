//DNI 48727303D PALAU ALEGRIA, JOSE MANUEL

public class Palabra{
	private String origen;
	private Traduccion[] trad;
	//numero de lenguas para realizar algunas operaciones
	private int numLenguas;
	//vector de lenguas
	private char [] lenguas;
	
	public Palabra (String p, int nl){
		//constructor de palabra

		origen = p;
		//si el numero de lenguas no es mayor que 0, ser� 3
		if(nl>0){
			numLenguas = nl;
		}
		else{
			numLenguas = 3;
		}
		//reservo memoria para el numero de lenguas dado
		trad = new Traduccion[numLenguas];
		lenguas = new char [numLenguas];
		
	}
	public void setLenguas(char[] ls){
		lenguas = ls;
	}
	public int setTrad(Traduccion t, char l){
		//poner traduccion
		
		//boolean para comprobar si existe o no al final del bucle
		boolean existe = false, hueco = false;
		//usare la variable actual para almacenar la posicion donde hare cosas
		int i, actual = 0, ret = -1;
		//compruebo que no me llegue un valor nulo
		if (t!=null){
			for(i=0; i<numLenguas && existe==false && hueco==false; i++){
				//si tengo la lengua, meto la traduccion en esa posicion del array
				if(lenguas[i]==l){
					trad[i]= t;
					existe=true;
					actual = i;
					ret = 1;
					break;
				}
				else{
					if(trad[i]==null){
						hueco = true;
						actual=i;
						break;
					}
				}
			}
			//si no existe la lengua y tengo sitio meto la traduccion
			if(existe == false && hueco == true){
				
				trad[actual]=t;
				lenguas[actual]=l;
				ret = actual;
				
				
			}

		}
		
		return ret;
	}
	//fin de metodo setTrad
	
	public String getOrigen(){
		return origen;
	}
	//fin de metodo getOrigen
	
	public String getTraduccion(char l){
		//devuelvo la primera acepcion de la primera traduccion
		String ret = null;
		int lengua = -1;
		//busco la lengua
		for (int i =0; i<numLenguas ; i++){
			if(l == lenguas[i]){
				lengua = i;
				break;
			}
		}
		//si la encuentro, saco la traduccion
		if (lengua != -1 && trad[lengua]!=null){
			ret = trad[lengua].getAcepcion();
		}

		return ret;
	}
	//fin de metodo getTraduccion
	
	public String getTraducciones(char l){
		//devuelvo la primera acepcion de la primera traduccion
		String ret = null;
		int lengua = -1;
		//busco la lengua
		for (int i =0; i<numLenguas ; i++){
			if(l == lenguas[i]){
				lengua = i;
				break;
			}
		}
		//si la encuentro, saco las traducciones
		if (lengua != -1 && trad[lengua] != null){
			ret = trad[lengua].getAcepciones();
		}

		return ret;
	}
	//fin del metodo agrega acepcion
	//devuelvo numLenguas
	public int getNumLenguas(){
		return numLenguas;
	}
	//fin de num lenguas
	
	public boolean agregaAcepcion(String s, char l){
		
		boolean ret = false, nueva = true;
		int lengua=-1;
		//busco la lengua
		for (int i =0; i<numLenguas ; i++){
			if(l == lenguas[i] && trad[i]!=null){
				lengua = i;
				nueva = false;
				break;
			}
			if(trad[i]==null){
				lengua = i;
				break;
			}
		}
		
		//una vez encontrada la lengua, intento meter la acepcion
		
		if(lengua != -1 && nueva ==false){
			if(trad[lengua].setAcepcion(s)){
				ret=true;
			}
		}
		if(lengua != -1 && nueva ==true ){
			Traduccion nuevatraduccion = new Traduccion(s);
			trad[lengua] = nuevatraduccion;
			lenguas[lengua] =l;
			ret=true;

			
		}
		return ret;
	}
	//fin de metodo agregaAcepcion
	public void escribeInfo(){
		String ret = origen;
		String aux; 
		for(int i = 0; i<numLenguas ; i++){
			if(trad[i]!=null){
				aux =trad[i].getAcepciones();
				if(aux != null){
					ret = ret + ":" + aux;
				}
			}
			else{
				ret = ret + ":";
			}
		}
		System.out.println(ret);
	}
	//comparador de palabras para ordenar
	public int comparaPalabra(Palabra p){
		
		return  p.getOrigen().compareToIgnoreCase(this.getOrigen());
		//devolvera 0 si las strings son iguales, menos de 0 si el argumento es mayor
		//y mas de 0 si el argumento es menor
	}
	//combinador de palabras
	public boolean combinaPalabra(Palabra p){
		boolean ret = false;
		//compruebo que las palabras origen sean iguales
		if(comparaPalabra(p)==0 && numLenguas == p.getNumLenguas()){
			for (int i = 0 ; i < numLenguas; i++){
				for(int j = 0; j < p.numLenguas ; j++){
					//si las lenguas son iguales, y la traduccion que me llega no es nula
					//pero la de this si lo es, la traduccion de this sera la del argumento
					if(trad[i]==null && p.trad[j]!=null && lenguas[i]==p.lenguas[j]){
						trad[i] = p.trad[i];
						ret = true;
					} 
					//si ninguna es nula habra que combinar las traducciones
					if(trad[i]!=null && p.trad[i]!=null  && lenguas[i]==p.lenguas[j]){
						if(trad[i].combinaTraduccion(p.trad[i])){
							ret = true;

						}
					}
					//si la traduccion del argumento fuese nula, paso a la siguiente traduccion
					//del argumento
				
				}
			}
		}
		return ret;
	}
}
