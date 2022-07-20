import { Component, NgModule } from '@angular/core';
import { FilesService } from './files.service';
import { Files } from './files/files.component';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit{
  title = 'writerGui';
  name: string = '';
  fileId: number = 0;
  blankFrame: string[][] = [];
  files: Files[] = [];
  numFrames: number = 0;
  frames: string[][][] = [];
  currFrame: number = 0;
  clicked: string = "";
  finalFile: Files | undefined;
  constructor(private filesService: FilesService,
      private router: Router){};
  ngOnInit(): void{
    this.createBlank;
    //this.getFiles;
  }


  createBlank(): void {
    this.currFrame ++;
    this.numFrames ++;
    for(let i = 0; i < 48; i++){
        this.frames[this.numFrames - 1][i] = [];
      for(let j = 0; j < 56; j++){
        this.frames[this.numFrames - 1][i][j] = "0";
      }
    }
  }

  newFrame(): void{
    this.numFrames++;
    this.frames[this.numFrames - 1] = [];
    for(let i = 0; i < 48; i++){
      this.frames[this.numFrames - 1][i] = [];
      for(let j = 0; j < 56; j++){
        this.frames[this.numFrames - 1 ][i][j] = this.frames[this.numFrames - 2][i][j];
      }
    }
    
  }

  getFiles(): void{
    this.filesService.getFiles().subscribe(files => this.files = files)
  }

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
      this.frames[this.currFrame - 1][i][j] = 'P';
    }
    else if (clicked == 'P'){
      this.frames[this.currFrame - 1][i][j] = 'Y';
    }else if (clicked == 'Y'){
      this.frames[this.currFrame - 1][i][j] = '0';
    } 
  }
  clickAndDrag(clicked: string, i: number, j: number){
    this.blankFrame[i][j] = clicked;
  }

  postForm(f: NgForm){
    return f;

  }
}
