import { Component } from '@angular/core';
import { ComuniProvinciaService } from '../../services/comuni-provincia-service';
import { Provincia } from '../../models/provincia';
import { Comune } from '../../models/comune';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-inserimento-comuni',
  imports: [CommonModule, FormsModule],
  templateUrl: './inserimento-comuni.html',
  styleUrl: './inserimento-comuni.css'
})
export class InserimentoComuni {
  prov:Provincia={ sigla_provincia: '' };
  cod?:number;
  denia?:string;
  deni?:string
  dena?:string;
  flag?:string;
  codb?:string;
  lat?:string;
  lon?:string;
  sup?:string;
  cods?:number;

  constructor(private serviceComuni: ComuniProvinciaService){}

  InserisciComune():void{
      let comune: Comune ={
        provincia: this.prov,
        codice_istat: this.cod,
        denominazione_ita_altra: this.denia,
        denominazione_ita: this.deni,
        denominazione_altra: this.dena,
        flag_capoluogo: this.flag,
        codice_belfiore: this.codb,
        lat: this.lat,
        lon: this.lon,
        superficie_kmq: this.sup,
        codice_sovracomunale: this.cods,
    }

    this.serviceComuni.Inserimento(comune).then(ris=>{
      if(ris){
        alert("Comune di " + comune.denominazione_ita_altra + " inserito correttamente nella provincia di " + comune.provincia?.sigla_provincia);
        console.log("Dati inseriti: ", comune);
      }
      else{
        alert("Errore nell'inserimento del comune di "+ comune.denominazione_ita_altra);
      }
    })

  }

  Reset():void{
    this.prov={ sigla_provincia: '' };
    this.cod=undefined;
    this.denia=undefined;
    this.deni=undefined;
    this.dena=undefined;
    this.flag=undefined;
    this.codb=undefined;
    this.lat=undefined;
    this.lon=undefined;
    this.sup=undefined;
    this.cods=undefined;
  }
}
