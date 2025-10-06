import { Component } from '@angular/core';
import { ProvinciaService } from '../../services/provincia-service';
import { Provincia } from '../../models/provincia';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-lista-province',
  imports: [CommonModule,RouterLink],
  templateUrl: './lista-province.html',
  styleUrl: './lista-province.css'
})
export class ListaProvince {

  currentPage = 1;
  itemsPerPages = 10;

  province:Provincia[] = new Array();

  constructor(private service : ProvinciaService, private router:Router){}

  ngOnInit(){
    this.RefreshLista();
  }

  private RefreshLista(){
    this.service.Lista().then(ris=>{
      this.province=ris;
      console.log(this.province.length)
    })
  }

  elimina(sigla_provincia:string | undefined):void{
    const provincia = this.province.find(p => {
      return p.sigla_provincia === sigla_provincia;
    })
    if (!provincia) {
      alert(`Provincia ${sigla_provincia} non trovata`);
      return;
    }
    if(sigla_provincia){
      if(provincia.numero_comuni !== undefined && provincia.numero_comuni > 0){
        if(confirm("La provincia di " + provincia.denominazione_provincia+ " contiene dei comuni\nVuoi continuare con l'eliminazione?")){
          this.service.Eliminazione(sigla_provincia).then(ris=>{
            if(ris){
              alert(`Provincia di ${provincia.denominazione_provincia} eliminata correttamente`);
              this.RefreshLista();
            }else{
              alert("Errore durante l'eliminazione");
            }
          })
        }else{
          alert("Operazione annullata");
          return;
        }
      }
    }else{
      alert("Codice provincia non definito");
    }
  }


  modifica(sigla_provincia:string | undefined):void{
    if(sigla_provincia){
      this.router.navigateByUrl(`/italia/modifica/${sigla_provincia}`)
    }
  }

  get PagesBlock():number[]{
    const bloccoProvince = 5; 
    const totalPages = this.totalPages;

    const bloccoCorrente = Math.floor((this.currentPage-1) / bloccoProvince );
    const inizio = bloccoCorrente * bloccoProvince+1;
    const fine = Math.min(inizio + bloccoProvince - 1, totalPages);

    const pagine: number[] = [];
    for (let i = inizio; i <= fine; i++) {
      pagine.push(i);
    }
    return pagine; 
  }

  get totalPages():number{
    return Math.ceil(this.province.length / this.itemsPerPages)
  }

  get ProvincePaginate():Provincia[]{
    const start = (this.currentPage -1)* this.itemsPerPages;
    const end = (start + this.itemsPerPages);
    return this.province.slice(start,end);
  }

  cambiaPagina(pagina:number):void{
    if(pagina >=1 && pagina <= this.totalPages){
      this.currentPage = pagina;
    }
  } 
}
