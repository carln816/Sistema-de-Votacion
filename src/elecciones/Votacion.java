
package elecciones;

import java.util.ArrayList;
import java.util.Scanner;


public class Votacion {
    /*atributos o variables*/
    //scanner sirbe para leer por teclado
    Scanner leer;
    //vector de candidatos
    ArrayList<Candidatos> candidatos;
    //vector de electores
    ArrayList<Electores> electores;
    int stop = 0; //detiene una posible tercera ronda
    
    public Votacion(){
        //dando memoria a lectura por teclado
        leer = new Scanner(System.in);
        candidatos = new ArrayList<Candidatos>(); //dando memoria a los vectores
        electores = new ArrayList<Electores>();
 
    }
    
    
    public void Cargar_Candidatos(){
        /*este metodo me permite colocar todos los candidatos que quiero*/
        //variable de la clase candidatos
        Candidatos ob1 = new Candidatos();
        ob1.nombre = "juan perez";
        ob1.partido = "patria nueva";
        candidatos.add(ob1); //agregando el candidato al vector de candidatos
        
        Candidatos ob2 = new Candidatos();
        ob2.nombre = "maria suares";
        ob2.partido = "la paz";
        candidatos.add(ob2); //agregando el candidato al vector de candidatos
        
        Candidatos ob3 = new Candidatos();
        ob3.nombre = "daniel ortiz";
        ob3.partido = "nuevo mundo";
        candidatos.add(ob3); //agregando el candidato al vector de candidatos
        
        Candidatos ob4 = new Candidatos();
        ob4.nombre = "maria rodriguez";
        ob4.partido = "esperanza del pais";
        candidatos.add(ob4); //agregando el candidato al vector de candidatos
        
        
        if( candidatos.size() <4 ){
            System.out.println("debes agregar mas candidatos a las elecciones");
        }
    }
    
    public void Mostrar_candidatos(){
        /*este metodo me permite mostrar todos los candidatos que tengo agregados en el metodo
         Cargar_Candidatos
        */
            //mostramos el titulo
            System.out.println("##########################################");
            System.out.println("****** CANDIDATOS A LA PRESIDENCIA ******");
            System.out.println("#       nombre     partido");
            //carga cada uno de los candidatos
            for (int i = 0; i < candidatos.size(); i++) {
                System.out.println(i+") "+candidatos.get(i).nombre+" => "+candidatos.get(i).partido);
            }
            System.out.println("##########################################");
    }
    
    
    public void votar(){
        /*este metodo o funcion permite votar a los electores*/
        int minimoVotantes = 20; //coloca el numero de votantes q quieras como minimo q participen en el caso del enunciado son 20 puedes colocar 2 como minimo para probar el sistema y no introduzcas tantos datos
        int i = 0;
        String continuarVotando = "";
        int votoCorrecto = 1; //si el voto es correcto en el numero de seleccion es 1 sino sera cero
        do{
            votoCorrecto = 1;
            Electores ob = new Electores(); //cree una variable de tipo electores para guardar los datos
            Mostrar_candidatos(); //muestra la lista de candidatos antes de votar
            System.out.println("*****BIENVENIDO ELECTOR "+(i+1)+" *****");
            int encontrado = 0;
            do{
                System.out.print("Ingrese Su Cedula: ");
                ob.cedula = leer.next();
                //reisando si el elector ya ha votado
                encontrado = 0;
                for (int j = 0; j < electores.size(); j++) {
                    if( electores.get(j).cedula.compareTo( ob.cedula )==0 ){ //el compareto compara dos string
                        encontrado = 1;
                        System.out.println("**** la cedula del elector ya voto ya su voto fue guardado ****");
                    }
                }
            }while( encontrado == 1); //repite la introduccion de la cedula hasta q la cedula no se repita
            System.out.println("Ingrese el numero por el candidato que quiere votar");
            
            do{
                votoCorrecto = 1;
                System.out.print("Ingrese Su voto: ");
                ob.opcion = leer.nextInt();
                if( ob.opcion < -1 || ob.opcion > candidatos.size()-1 ){ // verifica q el numero introducia este en el rango de los de la lista de candidatos
                    votoCorrecto = 0;
                    System.out.println("error de candidato");
                }
            }while( votoCorrecto == 0 ); // repite el introduccion de voto hasta q sea valido respecto al numero de candidatos
            electores.add(ob);
            System.out.println("Gracias por participar en las votaciones ( su voto fue almacenado )");
            //aqui si han votado menos de 20 siguen las elecciones si la opcion es n
            System.out.println("\n\nDesea Votar presione s para votar o n para no votar");
            continuarVotando = leer.next();
            i++;
            if( i < minimoVotantes && continuarVotando.compareTo("n")==0 ){ //condicion q no permite salir si no han pasado los 20 electores a votar
                System.out.println("ERROR NO HAN PASADO 20 ELECTORES"); 
               continuarVotando = "s";  //forza a seguir votando
            }
         
        }while( continuarVotando.compareTo("s") == 0 ); //se va a repetir mientras se diga que quieren votar mas
  
    }
    
    public void sumarVotosObtenidosPorCandidato(){
        
        for (int i = 0; i < electores.size(); i++) {
            int temporal = (int) candidatos.get(electores.get(i).opcion).votosObtenidos+1; //guarda en tempral un voto mas para el candidato de la posicion del get
            candidatos.get(electores.get(i).opcion).votosObtenidos = temporal;  // guarda en el vector el nuevo voto         
        }
        System.out.println("####################  VOTOS ###################");
        System.out.println("#    nombre     partido      votos-obtenidos     porcentaje");
        for (int i = 0; i < candidatos.size(); i++) {
            candidatos.get(i).porcentaje = ((candidatos.get(i).votosObtenidos*100)/electores.size()); //calcula y guarda el porcentaje en el vector de candidatos para luego mostrar los candidatos con su informacion
            System.out.println(i+") "+candidatos.get(i).nombre+" => "+candidatos.get(i).partido+" => "+candidatos.get(i).votosObtenidos+" => "+candidatos.get(i).porcentaje+"%");          
        }
        System.out.println("###############################################");
        
    }
    
    public void ganador(){
        int mayor = -1; //variable para calcular quien tiene mas votos
        int indice =-1; //variable para saber que posicion es la de mayor votos
        // buscando quien es el de mayor cantidad de votos
        for (int i = 0; i < candidatos.size(); i++) {
            if( mayor < candidatos.get(i).votosObtenidos ){
                mayor = candidatos.get(i).votosObtenidos;
                indice = i;
            }          
        }
       
        int numeroMismaCantVotos = 0;
        for (int i = 0; i < candidatos.size(); i++) {
            if(candidatos.get(i).votosObtenidos == mayor ){
                numeroMismaCantVotos++; //de esta manera se si hay empates en el primer lugar
            }
        }
        int segundaRonda = 0; //activa la segunda ronda
        int contador = 0; //cuenta los dos primeros en el vector
        int primerCandidato=-1; //para saber la posicion en el vector del candidato empato
        int segundoCandidato=-1; //para saber la posicion en el vector del candidato empato
        
        if( numeroMismaCantVotos > 1 ){ //mostrando todos los empates en caso de que existan con la misma cantida de votos
            segundaRonda = 1; //se activo la segund ronda
            System.out.println("################### EMPATE #############");
            System.out.println("nombre     partido      votos-obtenidos     porcentaje");
            for (int i = 0; i < candidatos.size() && contador !=2 ; i++) { //solo mostramos los dos primeros
                //busco todos los q tengan mayor votos al maximo de votos obtenidos por un candidato y reviso si es el mismo porcentaje
                if(candidatos.get(i).votosObtenidos == mayor && candidatos.get(i).porcentaje == candidatos.get(indice).porcentaje ){
                    System.out.println(candidatos.get(i).nombre+" => "+candidatos.get(i).partido+" => "+candidatos.get(i).votosObtenidos+" => "+candidatos.get(i).porcentaje+"%");                             
                    contador++;
                    if(contador==1){
                        primerCandidato = i; //primer candidato con mayor votos
                    }
                    if(contador==2){
                       segundoCandidato = i; //segundo candidato con mayor votos igual de votos que el primero
                    }
                }    
            }
            System.out.println("###############################################");
            //elimino a todos los candidatos menos el empate en el vector de candidatos
            if(segundaRonda==1){
                Candidatos temp1 = new Candidatos(); //variable temporal para mantener los candidatos mientras limpio el vector
                Candidatos temp2 = new Candidatos();
                temp1 = candidatos.get(primerCandidato);
                temp2 = candidatos.get(segundoCandidato);           
                candidatos.clear(); //eliminamos todos los candidatos
                //agregamos los dos candidatos al al vector de candidatos
                candidatos.add(temp1);
                candidatos.add(temp2);
            }
        }else if(numeroMismaCantVotos == 1 && candidatos.get(indice).porcentaje >= 40){ //si el ganador tiene un porcentaje mayor a 40 se muestra como unico ganador
            System.out.println("################ GANADOR ################");
            System.out.println("Nombre: "+candidatos.get(indice).nombre);
            System.out.println("Partido: "+candidatos.get(indice).partido);
            System.out.println("Votos Obtenido: "+candidatos.get(indice).votosObtenidos);
            System.out.println("Porcentaje: "+candidatos.get(indice).porcentaje+"%");
            System.out.println("#########################################");     
        }else{           
            System.out.println("#### CANDIDATOS CON MAS VOTOS CON % MENOR 40 ####");
            System.out.println("nombre     partido      votos-obtenidos     porcentaje");
            segundaRonda = 1; //se activo la segund ronda
            //muestra el que tiene mas votos
            primerCandidato = indice; //posicion del primer candidato a la segunda ronda
            System.out.println(candidatos.get(indice).nombre+" => "+candidatos.get(indice).partido+" => "+candidatos.get(indice).votosObtenidos+" => "+candidatos.get(indice).porcentaje+"%");          
            mayor = -1;
            candidatos.get(indice).votosObtenidos = -1; //eliminando al que tiene mas votos para buscar quien es el segundo con mas votos         
            for (int i = 0; i < candidatos.size(); i++) {
                if( mayor < candidatos.get(i).votosObtenidos ){
                    mayor = candidatos.get(i).votosObtenidos;
                    indice = i;
                } 
            }
            segundoCandidato = indice;//segunda posicion de candidato a la segunda ronda
            System.out.println(candidatos.get(indice).nombre+" => "+candidatos.get(indice).partido+" => "+candidatos.get(indice).votosObtenidos+" => "+candidatos.get(indice).porcentaje+"%");          
            System.out.println("###############################################"); 
            
            //elimino a todos los candidatos menos los dos mayores en el vector de candidatos
            if(segundaRonda==1){
                Candidatos temp1 = new Candidatos();
                Candidatos temp2 = new Candidatos();
                temp1 = candidatos.get(primerCandidato);
                temp2 = candidatos.get(segundoCandidato);           
                candidatos.clear(); //eliminamos todos los candidatos
                //agregamos los dos candidatos al al vector de candidatos
                candidatos.add(temp1);
                candidatos.add(temp2);
            }
        }
        
        if( segundaRonda == 1 && stop == 0){
                stop = 1;
                //creo la segundaronda de votaciones
                System.out.println("\n\n\n\n\n###############################################");
                System.out.println("############### SEGUNDA RONDA ###############");  
                electores.clear(); //debemos limpiar el vector de electores para las nuevas votaciones
                candidatos.get(0).votosObtenidos = 0; //limpiamos los votos anteriores y el porcentaje anterior
                candidatos.get(1).votosObtenidos = 0;
                candidatos.get(0).porcentaje = 0;
                candidatos.get(1).porcentaje = 0;
                votar(); //empiezan las votaciones
                sumarVotosObtenidosPorCandidato();
                ganador();
        }
        if( stop == 1 ){
            System.out.println("*** NO PODEMOS GENERAR UNA TERCERA RONDA ***");
        }
        
        
    }
    
    
}
