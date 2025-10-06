import { Component } from '@angular/core';
import { ProvinciaService } from '../../services/provincia-service';
import { ActivatedRoute, Router } from '@angular/router';
import { Provincia } from '../../models/provincia';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-modifica-provincia',
  imports: [FormsModule],
  templateUrl: './modifica-provincia.html',
  styleUrl: './modifica-provincia.css'
})
export class ModificaProvincia {

  province:Provincia[]=new Array()

  cod_reg?:number;
  sigla?:string;
  denom?:string;
  tip?:string;
  num_com?:number;
  sup_kmq?:string;
  cod_sov?:number;

  constructor(private service:ProvinciaService, private route:ActivatedRoute, private router:Router){}

  ngOnInit(){
    this.route.params.subscribe(async (ris)=>{
      const sigla = ris['sigla'];
      if(sigla && sigla.trim() != ""){
        const prov = await this.service.Dettaglio(sigla);
        if(prov){
          this.cod_reg = prov.codice_regione;
          this.sigla = prov.sigla_provincia;
          this.denom = prov.denominazione_provincia;
          this.tip = prov.tipologia_provincia;
          this.num_com = prov.numero_comuni;
          this.sup_kmq = prov.superficie_kmq
          this.cod_sov = prov.codice_sovracomunale;
        }
      }
    })
  }


  Modifica():void{
    let provincia:Provincia={
      codice_regione:this.cod_reg,
      sigla_provincia:this.sigla,
      denominazione_provincia:this.denom,
      tipologia_provincia:this.tip,
      numero_comuni:this.num_com,
      superficie_kmq:this.sup_kmq,
      codice_sovracomunale:this.cod_sov
    }

    this.service.Modifica(provincia).then(ris=>{
      if(ris){
        alert(`Provincia di ${provincia.denominazione_provincia} modificata correttamente`);
        console.log("Modifica della provincia di ", provincia.denominazione_provincia, " avvenuta con successo")
        this.router.navigateByUrl(`italia/province`)
      }else{
        alert("Errore durante la modifica");
      }
    })
  }

}