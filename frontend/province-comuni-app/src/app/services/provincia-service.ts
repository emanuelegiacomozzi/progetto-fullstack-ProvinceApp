import { Injectable } from '@angular/core';
import { Provincia } from '../models/provincia';

@Injectable({
  providedIn: 'root'
})
export class ProvinciaService {

  constructor(){ }

  private isProvincia(obj:any): obj is Provincia{
    if(typeof obj !== "object" || obj === null) return false

    return(
      (typeof obj.codice_regione === "number" || obj.codice_regione === undefined) &&
      (typeof obj.sigla_provincia === "string" || obj.sigla_provincia === undefined) &&
      (typeof obj.denominazione_provincia === "string" || obj.denominazione_provincia === undefined) &&
      (typeof obj.tipologia_provincia === "string" || obj.tipologia_provincia === undefined) &&
      (typeof obj.numero_comuni === "number" || obj.numero_comuni === undefined) &&
      (typeof obj.superficie_kmq === "string" || obj.superficie_kmq === undefined) &&
      (typeof obj.codice_sovracomunale === "number" || obj.codice_sovracomunale === undefined)
    )
  }
  
  async Lista():Promise<Provincia[]>{
      try{
        const response = await fetch(`http://localhost:8080/province`);
        if(!response.ok){
        console.log(`Errore HTTP: ${response.status}`);
        return [];
      }
      const risultato = await response.json();
      if(Array.isArray(risultato)){
        if(risultato.every(e=>this.isProvincia(e))){
          return risultato;
        }else{
        console.log("ERRORE: dati non validi");
        }
      }else{
        console.log("ERRORE API", risultato)
      }
    }catch(e){
      console.log("ERRORE: ", e);
    }
    return [];
  }



  async Inserimento(provincia:Provincia):Promise<boolean>{
    try{
      const response = await fetch(`http://localhost:8080/province`,
        {
          headers:{
            "Content-Type":"application/json"
          },
          method:"POST",
          body: JSON.stringify(provincia)
        }
      )
      if(!response.ok){
        console.log(`ERRORE HTTP: ${response.status}`)
        return false;
      }
      const risultato = await response.json();
      console.log("Risposta API: ", risultato)
      if(risultato===false){
        console.log("Provincia già esistente")
        return false;
      }
      return true;
    }catch(e){
      console.log("ERRORE: ", e)
    }
    return false;
  }

  async Eliminazione(sigla_provincia:string):Promise<boolean>{
    try{
      const response = await fetch(`http://localhost:8080/province/${sigla_provincia}`,
        {
          method:"DELETE"
        }
      )
      if(!response.ok){
        console.log("Errore HTTP", response.status)
        return false;
      }
      const risultato = await response.json();
      console.log("Risposta API:", risultato);
      if(risultato){
        console.log("Provincia eliminata")
        return true;
      }
      return false;
    }catch(e){
      console.log(`Errore ${e}`)
    }
    return false;
  }


  async Modifica(provincia:Provincia):Promise<boolean>{
    try{
      const sigla_provincia = provincia.sigla_provincia;
      provincia.sigla_provincia = undefined;
      const response = await fetch(`http://localhost:8080/province/${sigla_provincia}`,{
        method:"PATCH",
        headers:{
          "Content-Type": "application/json"
        },
        body:JSON.stringify(provincia)
    })
    if(!response.ok){
      console.log(`Errore HTTP: ${response.status}`)
      return false;
    }
    const risultato = await response.json();
    if(risultato){
      return true;
    }
    console.log("ERRORE API", risultato);
    }catch(e){
      console.log(`Errore ${e}`)
    }
    return false;
  }


  async Dettaglio(sigla:String):Promise<Provincia | null>{
    try{
      const response = await fetch(`http://localhost:8080/province/${sigla}`)
      if(!response.ok){
        console.log(`Errore HTTP: ${response.status}`)
        return null;
    }
    const risultato = await response.json();
    if(risultato){
      return risultato;
    }
    console.log("ERRORE API", risultato);
    }catch(e){
      console.log(`Errore ${e}`)
    }
    return null;
  }

}
