/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;
/**
 *
 * @author PC
 */
public class Candidatos{
    /*variables o atributos*/
    public String nombre;
    public String partido;
    public int votosObtenidos; //al final de las votaciones lleno esta variable
    public int porcentaje; //porcentaje de votos obtenidos al final de las votaciones lleno esta variable
    //constructor
    public Candidatos(){
        /*INICIALIZANDO LAS VARIABLES EN VACIO*/
        nombre = "";
        partido = "";
        votosObtenidos = 0;
        porcentaje = 0;
        
    }//fin constructor Candidatos   
}