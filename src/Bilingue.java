//DNI 48727303D PALAU ALEGRIA, JOSE MANUEL

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Bilingue {

	public static String[] leeTexto (String f){

		//definimos las variables de instancia
		FileReader fichero= null;
		BufferedReader lectura= null;
		int max=5;
		String[] texto=new String[max];
		int i=0;
		try{ 
			//inicializamos las variables de instancia
			fichero= new FileReader(f);
			lectura= new BufferedReader(fichero);
			String linea = lectura.readLine();

			while (linea!=null){
				if(i==max-1){ //si nos hemos pasado en el array lo redimensionamos
					max+=5; // aumentamos el máximo
					String[] aux = new String[max]; // y creamos un nuevo array auxiliar
					for(int j=0; j<max-5;j++){ //recorremos todo el array, pasandole a aux todo el contenido de texto
						aux[j]=texto[j]; 
					}
					texto=aux;//y finalmente, igualamos texto a auxiliar
				}


				texto[i]=linea;

				linea=lectura.readLine(); //leemos el documento línea a línea

				System.out.println(texto[i]);

				i++;

			}
			//si el try falla tiro excepcion
		}catch(IOException e){
			System.err.println("Error con el archivo " + f);
		}
		//y cerramos el fichero
		try{
			if (fichero!=null)//comprobamos que fichero no sea null
				fichero.close();
			if (lectura!=null)//ni que lectura sea null
				lectura.close();
		}catch (IOException ex){//si esto falla lanzaremos la excepci�n
			System.out.println(ex);
		}
		return texto;
	}

	public static void main (String [ ] args) {

		if(args.length>=2){
			DiccA dic=new DiccA(); //el diccionario que vamos a usar
			String separador = "[,:;?!\\.() ]+", separadorletras = "[a-zA-Z']+"; 
			String[] texto, separadores; //en este array guardaremos las palabras y los separadores
			char l=args[2].charAt(0);//parseamos args[2] a char, ya que mis métodos aceptan char, no str
			String traduc; //en esta string guardaremos la frase original
			String[] lineas;
			int fallos=0; 
			String salida = "";
			//primero vamos a  meterle las palabras al diccionario

			dic.leeDiccA(args[0]); //leemos diccionario

			//comprobamos que tengamos la lengua en el diccionario
			boolean lengua=false; //creamos esta variable para controlar si está o no
			char[] lenguas=dic.devuelveLengua(); // en este array de caracteres guardaremos las lenguas de dic
			for (int i=0;i<lenguas.length;i++){ //recorro el array de lenguas 
				if (l==lenguas[i]){ //si encuentro un char que sea igual al que busco
					lengua=true; //pongo lengua a true
					break; // y salgo del bucle
				}
			}

			if (lengua==false){ //si lengua es false
				System.out.println("No existe traduccion para esta lengua"); // doy mensaje de error
				return; //y cierro
			}

			int i=0; //declaro i fuera porque quiero saber el porcentaje de fallos
			//luego dividiremos el archivo que nos pasan
			lineas=leeTexto(args[1]);//separamos el texto con el separador
			for(int j=0; lineas[j]!=null;j++){
				traduc=lineas[j];
				texto=traduc.split(separador); //separo el texto con el separador
				separadores = traduc.split(separadorletras); //separo los separadores con un separador

				for (int k = 0;k<texto.length;k++){  
					if(dic.traduce1(texto[k],l)!=null){ // si no hay nulos
						salida = salida + separadores[k] + dic.traduce1(texto[k], l) ;
						//

					}
					else{ //si no encuentro la traducción imprimo un guión
						salida = salida + "-" + separadores[k];
						fallos++; //y aumento fallos
					}
					i++;
				}
				System.out.println(salida); //devuelvo el texto original traducido
			}
			if(i!=0)
				System.out.print((i-fallos)*100/i + "%"); // y los fallos cometidos en porcentaje -> Fallos * 100 dividido entre las veces que he hecho el bucle
			else
				System.out.println(0+"%");
		}

		else{
			System.out.println("Te faltan argumentos");
		}
	}
}
