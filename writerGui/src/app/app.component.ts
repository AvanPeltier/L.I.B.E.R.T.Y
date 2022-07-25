import { Component, NgModule } from '@angular/core';
import { FilesService } from './files.service';
import { Files } from './files/files.component';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { getLocaleCurrencySymbol } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit{
  title = 'writerGui';
  user: string = 'default'
  name: string = '';
  fileId: number = 0;
  blankFrame: string[][] = [];
  files: Files[] = [];
  numFrames: number = 0;
  frames: string[][][] = [];
  currFrame: number = 0;
  clicked: string = "";
  finalFile: Files | undefined;
  approved: boolean = false;
  constructor(private filesService: FilesService,
      private router: Router){};
  
    ngOnInit(): void{
    this.createBlank();
    //this.getUserFiles(this.user);
  };

  createBlank(): void {    
    console.log("create")
    this.currFrame ++;
    this.numFrames ++;
    this.frames[this.currFrame - 1] = []
    for(let i = 0; i < 48; i++){
      this.frames[this.currFrame - 1][i] = []
      this.frames[this.currFrame - 1][i].fill("0", 0, 56)
  };
  for(let i = 0; i < 48; i++){
    for(let j = 0; j < 56; j++){
      this.frames[this.currFrame - 1][i][j] = "0";
    }
  }
}

addFrame(): void{
    this.numFrames++;
    this.currFrame ++;
    this.frames[this.numFrames - 1] = [];
    for(let i = 0; i < 48; i++){
      this.frames[this.currFrame - 1][i] = []
      this.frames[this.currFrame - 1][i].fill("0", 0, 56)
  };
  for(let i = 0; i < 48; i++){
    for(let j = 0; j < 56; j++){
      this.frames[this.numFrames - 1][i][j] = this.frames[this.numFrames - 2][i][j];
    }
  }    
};

  delFrame(){
    this.numFrames --;
    this.currFrame --;
  }

  getFiles(): void{
    this.filesService.getFiles().subscribe(files => this.files = files)
  };

  buttonClick(clicked: string, i: number, j: number){
    console.log(i, j, clicked)
    if(clicked == '0'){
      this.frames[this.currFrame - 1][i][j] = 'W';
    }
    else if (clicked == 'W'){
      this.frames[this.currFrame - 1][i][j]= 'R';
    }
    else if (clicked == 'R'){
      this.frames[this.currFrame - 1][i][j] = 'B';
    }
    else if (clicked == 'B'){
      this.frames[this.currFrame - 1][i][j] = 'G';
    }
    else if (clicked == 'G'){
      this.frames[this.currFrame - 1][i][j]= 'P';
    }
    else if (clicked == 'P'){
      this.frames[this.currFrame - 1][i][j] = 'Y';
    }else if (clicked == 'Y'){
      this.frames[this.currFrame - 1][i][j] = '0';
    } 
  };

  //clickAndDrag(clicked: string, i: number, j: number){
  //  this.blankFrame[i][j] = clicked;
  //};

  postForm(f: NgForm){
    var name: string = f.value.name;
    var frames: string[] = [];
    var frame: string = "";
    for (let i = 0; i < this.numFrames; i++){
      for(let row = 0; row < 48; row++){
        for(let coloumn = 0; coloumn < 56; coloumn++){
          frame.concat(this.frames[i][row][coloumn])
        }
      }
      frames[i] = frame;
      frame = "";
    }
    this.addFile(name, this.user, this.numFrames, frames);
  }

  addFile(name: string, user:string, numFrames: number, frames: string[]){
    name = name.trim();
    var approved = this.approved;
    this.filesService.addFile({name, user, numFrames, frames, approved} as Files).subscribe(files => {
      this.files.push(files);
    })
  }

  getUserFiles(user:string){
    this.filesService.getUserFile(user).subscribe(files => this.files = files)
  }
}
