/**
* @author Jose Manuel Palau Alegria
*Se crea un objeto de tipo Palabra, se le agregan diferentes acepciones para cada
idioma y se invoca su método getTraduccion para cada idioma, mostrando por
pantalla lo que devuelve
*/
public class p07 {
	public static void main(String[] args){
	
		Palabra p =new Palabra("Hello", 3);
		p.agregaAcepcion("Hola", 'E');
		p.agregaAcepcion("Lok'Tar camarada", 'E');
		p.agregaAcepcion("Heh, Saludos", 'E');
		p.agregaAcepcion("", 'E');
		p.agregaAcepcion("Saludos viajero", 'E');
		p.agregaAcepcion("El placer es mío", 'E');
		p.agregaAcepcion("hola bebés", 'E');
		
		System.out.println(p.getTraducciones('E'));

		p.agregaAcepcion("Lok'tar hamijo", 'P');
		p.agregaAcepcion("Heh, nas", 'P');
		p.agregaAcepcion("", 'P');
		p.agregaAcepcion("Saludos viajerador", 'P');
		p.agregaAcepcion("WELL MET", 'P');
		p.agregaAcepcion("hola bebés", 'P');
		
		System.out.println(p.getTraducciones('P'));
		
		p.agregaAcepcion("Pues no se frances", 'F');
		p.agregaAcepcion("Nope, ni idea", 'F');
		p.agregaAcepcion("En verdad es triste", 'F');
		p.agregaAcepcion("Deberia saber algun idioma mas", 'F');
		p.agregaAcepcion(null, 'F');
		p.agregaAcepcion("hola bebés", 'F');
		
		System.out.println(p.getTraducciones('F'));

	}
}
