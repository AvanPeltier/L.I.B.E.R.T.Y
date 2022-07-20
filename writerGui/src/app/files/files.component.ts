import { Component, OnInit } from '@angular/core';
import { FilesService } from '../files.service';
import { MessageService } from '../message.service';
export interface Files {
  id: number;
  name: string;
  numFrames: number;
  frames: string[];
}

@Component({
  selector: 'app-files',
  templateUrl: './files.component.html',
  styleUrls: ['./files.component.css']
})

export class FilesComponent implements OnInit {
  name: string = '';
  fileId: number = 0;
  blankFrame: string[][] = [];
  files: Files[] = [];
  frames: string[][][] = [];
  tmpFrame: string[][] = [];
  numFrames: number = 1;
  currentFrame: number = 1;
  finalFile: Files | undefined;
  constructor(private filesService: FilesService,
    private messageService: MessageService){};

  ngOnInit(): void{
    this.createBlank();
    //this.getFiles;
  }


  createBlank(): void {
    for(let i = 0; i < 48; i++){
      for(let j = 0; j < 56; j++){
        this.frames[this.numFrames - 1][i][j] = '0';
      }
    }
  }

  getFiles(): void{
    this.filesService.getFiles().subscribe(files => this.files = files)
  }
}
