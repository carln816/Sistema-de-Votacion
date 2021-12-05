
package elecciones;


public class Principal {
    
    
    public static void main(String[] args) {
        //creando un objeto de laclase votacion
        Votacion ob = new Votacion();
        //llamando a las funciones o metodos de la clase votacion
        ob.Cargar_Candidatos(); //carga la inforcacion de los candidatos
        ob.votar(); //empiezan las votaciones
        ob.sumarVotosObtenidosPorCandidato();
        ob.ganador();
        
    }
}
