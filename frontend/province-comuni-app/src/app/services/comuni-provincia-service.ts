import { Injectable } from '@angular/core';
import { Comune } from '../models/comune';

@Injectable({
  providedIn: 'root'
})
export class ComuniProvinciaService {

  async Lista(sigla:string):Promise<Comune[]>{
    try{
      const response = await fetch(`http://localhost:8080/comuni/provincia/${sigla}`)
      if(!response.ok){
        console.log(`Errore HTTP: ${response.status}`)
        return []
      }
      const risultato = await response.json();
      if(Array.isArray(risultato)){
        return risultato;
      }else{
        console.log("ERRORE API", risultato)
      }
    }catch(e){
      console.log("ERRORE", e)
    }
    return [];
  }


  async Inserimento(comune:Comune):Promise<boolean>{
    try{
      const response = await fetch(`http://localhost:8080/comuni/${comune.provincia?.sigla_provincia}`,
        {
          headers:{
            "Content-Type":"application/json"
          },
          method:"POST",
          body: JSON.stringify(comune)
        }
      )
      if(!response.ok){
        console.log(`Errore HTTP: ${response.status}`)
        return false
      }
      const risultato = await response.json();
      console.log("Risposta API: ", risultato)
      if(risultato === false){
        console.log("Comune già esistente")
        return false
      }
      return true;
    }catch(e){
      console.log("ERRORE: ", e)
    }
    return false;
  }


  async Eliminazione(codice_istat:number):Promise<boolean>{
    try{
      const response = await fetch(`http://localhost:8080/comuni/${codice_istat}`,
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
        console.log("Comune eliminato")
        return true;
      }
      return false;
    }catch(e){
      console.log(`Errore ${e}`)
    }
    return false;
  }


  async ListaComuni():Promise<Comune[]>{
    try{
      const response = await fetch(`http://localhost:8080/comuni`)
      if(!response.ok){
        console.log(`Errore HTTP: ${response.status}`)
        return []
      }
      const risultato = await response.json();
      if(Array.isArray(risultato)){
        return risultato;
      }else{
        console.log("ERRORE API", risultato)
      }
    }catch(e){
      console.log("ERRORE", e)
    }
    return [];
  }



  async Modifica(comune:Comune):Promise<boolean>{
      try{
        const codice = comune.codice_istat;
        comune.codice_istat = undefined;
        const response = await fetch(`http://localhost:8080/comuni/${codice}`,{
          method:"PATCH",
          headers:{
            "Content-Type": "application/json"
          },
          body:JSON.stringify(comune)
      })
      if(!response.ok){
        console.log(`Errore HTTP: ${response.status}`, response)
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
  
  
    async Dettaglio(sigla:String, codice:number):Promise<Comune | null>{
      try{
        const response = await fetch(`http://localhost:8080/comuni/${sigla}/${codice}`)
        if(!response.ok){
          console.log(`Errore HTTP: ${response.status}`, response)
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
