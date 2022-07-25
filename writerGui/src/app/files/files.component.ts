import { Component, OnInit } from '@angular/core';
import { FilesService } from '../files.service';
import { MessageService } from '../message.service';
export interface Files {
  id: number;
  user: string;
  name: string;
  numFrames: number;
  frames: string[];
  approved: boolean;
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
  
  ngOnInit(): void {
      
  }
  }
