import { Component } from '@angular/core';
import { Comune } from '../../models/comune';
import { ComuniProvinciaService } from '../../services/comuni-provincia-service';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-lista-comuni',
  imports: [CommonModule],
  templateUrl: './lista-comuni.html',
  styleUrl: './lista-comuni.css'
})
export class ListaComuni {


  comuni:Comune[] = new Array();

  currentPage=1;
  itemsPerPage=20;
  /*pagine: number[] = [];*/

  constructor(private service: ComuniProvinciaService, private router:Router){}

  ngOnInit(){
    this.RefreshLista();
  }

  private RefreshLista(){
    this.service.ListaComuni().then(ris=>{
      this.comuni = ris;
      console.log(this.comuni.length)

      /*this.pagine = Array.from({length:this.totalPages}, (_, i)=>i+1); //torna tutte le pagine*/
    })
  }

  elimina(codice_istat:number | undefined):void{
    const comune = this.comuni.find(c => {
      return c.codice_istat === codice_istat;
    })
    if (!comune) {
      alert("Comune non trovato");
      return;
    }
    if(codice_istat){
      this.service.Eliminazione(codice_istat).then(ris=>{
        if(ris){
          alert(`Comune di ${comune.denominazione_ita_altra} eliminato correttamente`);
          this.RefreshLista();
        }else{
          alert("Errore durante l'eliminazione del comune di "+ comune.denominazione_ita_altra);
        }
      })
    }else{
      alert("Codice comune non definito");
    }

  }

  modifica(sigla_provincia:string | undefined, codice_istat:number | undefined):void{
    if(sigla_provincia && codice_istat){
      this.router.navigateByUrl(`/italia/modifica/comune/${sigla_provincia}/${codice_istat}`)
    }
  }


  get PagesBlock():number[]{  //torna le pagine in blocchi di 5
    const bloccoComuni = 5;
    const totalPages = this.totalPages;

    const bloccoCorrente = Math.floor((this.currentPage - 1) / bloccoComuni);
    const inizio = bloccoCorrente * bloccoComuni + 1;
    const fine = Math.min(inizio + bloccoComuni - 1, totalPages);

    const pagine: number[] = [];
    for (let i = inizio; i <= fine; i++) {
      pagine.push(i);
    }
    return pagine;
  }

  get totalPages():number{ //pagine totali
    return Math.ceil(this.comuni.length/this.itemsPerPage);
  }

  get comuniPaginati():Comune[]{  //comuni per pagina
    const start = (this.currentPage -1)*this.itemsPerPage;
    const end = (start + this.itemsPerPage);
    return this.comuni.slice(start, end);
  }

  cambiaPagina(pagina: number): void {
    if (pagina >= 1 && pagina <= this.totalPages) {
      this.currentPage = pagina;
    }
  }


  
}
