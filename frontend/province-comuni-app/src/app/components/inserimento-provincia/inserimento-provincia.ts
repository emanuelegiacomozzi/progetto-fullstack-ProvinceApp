import { Component } from '@angular/core';
import { ProvinciaService } from '../../services/provincia-service';
import { Provincia } from '../../models/provincia';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-inserimento-provincia',
  imports: [FormsModule],
  templateUrl: './inserimento-provincia.html',
  styleUrl: './inserimento-provincia.css'
})
export class InserimentoProvincia {

  province:Provincia[] = new Array();

  cod_reg?:number;
  sigla?:string;
  denom?:string;
  tip?:string;
  num_com?:number=0;
  sup_kmq?:string;
  cod_sov?:number;

  constructor(private service:ProvinciaService){}

  ngOnInit(){
    this.service.Lista().then(ris=>{
      this.province = ris;
    })
  }

  Inserisci():void{
    let provincia:Provincia={
      codice_regione:this.cod_reg,
      sigla_provincia:this.sigla,
      denominazione_provincia:this.denom,
      tipologia_provincia:this.tip,
      numero_comuni:this.num_com,
      superficie_kmq:this.sup_kmq,
      codice_sovracomunale:this.cod_sov
    }

    this.service.Inserimento(provincia).then(ris=>{
      if(ris){
        alert("Provincia di "+ provincia.denominazione_provincia + " inserita correttamente")
        console.log("Provincia inserita: ", provincia)
      }else{
        alert("Errore nell'inserimento della provincia di " + provincia.denominazione_provincia)
      }
    })
  }

  Reset():void{
    this.cod_reg=undefined,
    this.sigla=undefined,
    this.denom=undefined,
    this.tip=undefined,
    this.num_com=undefined,
    this.sup_kmq=undefined,
    this.cod_sov=undefined
  }
}
