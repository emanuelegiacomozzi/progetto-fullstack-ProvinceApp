import { Component } from '@angular/core';
import { Provincia } from '../../models/provincia';
import { ComuniProvinciaService } from '../../services/comuni-provincia-service';
import { ActivatedRoute, Router } from '@angular/router';
import { Comune } from '../../models/comune';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ProvinciaService } from '../../services/provincia-service';


@Component({
  selector: 'app-modifica-comune',
  imports: [FormsModule, CommonModule],
  templateUrl: './modifica-comune.html',
  styleUrl: './modifica-comune.css'
})
export class ModificaComune {

  province:Provincia[]=new Array();

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

  constructor(private service:ComuniProvinciaService, private route:ActivatedRoute, private router:Router, private serviceProvincia:ProvinciaService){}

  ngOnInit(){
    this.route.params.subscribe(async (ris)=>{
      const sigla = ris['sigla'];
      const codice = ris['codice'];
      if(codice && sigla){
        const com = await this.service.Dettaglio(sigla, codice);
        if(com){
          this.prov = com.provincia ?? { sigla_provincia: '' }; // Se è undefined, inizializza con default
          this.cod = com.codice_istat;
          this.denia = com.denominazione_ita_altra;
          this.deni = com.denominazione_ita;
          this.dena = com.denominazione_altra;
          this.flag = com.flag_capoluogo;
          this.codb = com.codice_belfiore;
          this.lat = com.lat;
          this.lon = com.lon;
          this.sup = com.superficie_kmq;
          this.cods = com.codice_sovracomunale;
        }
      }
    })

    this.RefreshLista();
  }

  private RefreshLista(){
    this.serviceProvincia.Lista().then(ris=>{
      this.province=ris;
      console.log(this.province.length)
    })
  }


  Modifica():void{

    const provinciaSelezionata = this.province.find(p => p.sigla_provincia === this.prov?.sigla_provincia);
    let comune:Comune={
      provincia:provinciaSelezionata,
      codice_istat:this.cod,
      denominazione_ita_altra:this.denia,
      denominazione_ita:this.deni,
      denominazione_altra:this.dena,
      flag_capoluogo:this.flag,
      codice_belfiore:this.codb,
      lat:this.lat,
      lon:this.lon,
      superficie_kmq:this.sup,
      codice_sovracomunale:this.cods
    }

    this.service.Modifica(comune).then(ris=>{
      if(ris){
        alert(`Comune di ${comune.denominazione_ita_altra} modificato correttamente`);
        console.log("Modifica del comune di ", comune.denominazione_ita_altra, " avvenuta con successo")
        this.router.navigateByUrl(`/italia/comuni/${comune.provincia?.sigla_provincia}`)
      }else{
        alert("Errore durante la modifica");
      }
    })
  }

}
