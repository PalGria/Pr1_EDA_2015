//DNI 48727303D PALAU ALEGRIA, JOSE MANUEL

public class Traduccion {
	private String[] traduccion;
	//constructor por defecto
	public Traduccion (){
		traduccion = new String[5];
	}
	//constructor con string
	public Traduccion (String t){
		traduccion = new String[5];
		if(t!=null){
			traduccion[0] = t;
		}
	}

	//devuelvo traduccion
	public String getAcepcion(){
		String traduccionActual = null;
		if(traduccion[0]!=null){
			traduccionActual = traduccion[0];
		}
		return traduccionActual;
	}
	//fin de getAcepcion
	public String getAcepciones() {
		String resultado="";
		for (int i = 0; i<5 && traduccion[i] != null ; i++){
			resultado = resultado + traduccion[i];
			//si hay acepcion siguiente, pongo una barra
			//compruebo si hay acepcion siguiente solo si i<4 para evitar consultar la posicion 5
			//y que de un ArrayIndexOutOfBounds
			if(i<4){
				if(traduccion[i+1]!=null && !traduccion[i].isEmpty()){
					if(!traduccion[i+1].isEmpty())
						resultado=resultado+"/";
				}
			}
		}
		//si resultado al final queda vacio, lo pongo a null
		if(resultado == ""){
			resultado = null;
		}
		
		return resultado;
	}
	//fin de metodo getAcepciones
	//meto acepciones
	public boolean setAcepcion(String s){
		boolean ret= false;
		if(s!=null){
			for (int i = 0; i<5; i++)
			{
				//compruebo si tengo la acepcion ya
				//en ese caso devuelvo false, ya que no hago nada
				if(traduccion[i]==null || traduccion[i].isEmpty()){
					traduccion[i]=s;
					ret = true;
					break;
				}
				
				if(traduccion[i].equalsIgnoreCase(s)){
					ret = false;
					break;
				}
				


				//si encuentro un hueco, meto la traduccion
			}
		}
		
		return ret;
	}
	//metodo para combinar traducciones
	public boolean combinaTraduccion(Traduccion trad2) {
		//compruebo donde tengo hueco, si lo tengo
		//si hueco es 5, no cabe nada mas, y por tanto no se entrara en el bucle
		//devuelve true si combina algo
		boolean ret = false;
		int hueco = 5;
		for(int i = 0; i<5 ; i++){
			if(traduccion[i]==null){
				hueco = i;
				break;
			}
		}
		//bucle que recorre todas las traducciones del argumento
		for(int i = 0; i<5 && trad2.traduccion[i]!=null && hueco <5; i++){
			//defino una variable para comprobar si estan repetidas
			boolean repe = false;
			//bucle que recorre todas las traducciones del objeto principal
			for(int j = 0; j<hueco && repe==false; j++){
				if(traduccion[j].equalsIgnoreCase(trad2.traduccion[i])){
					repe = true;
				}
			}
			//si no la encuentro repetida
			if(repe != true){
				traduccion[hueco] = trad2.traduccion[i];
				ret = true;
				if(hueco<5){
					hueco++;
				}
			}
		
		}
		return ret;
	}
	//fin de combinar acepciones 
}
