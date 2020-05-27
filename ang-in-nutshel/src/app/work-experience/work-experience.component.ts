import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-work-experience',
  templateUrl: './work-experience.component.html',
  styleUrls: ['./work-experience.component.css']
})
export class WorkExperienceComponent implements OnInit {

  @Input() jobInfo: any[];
  @Input() fullname: string;
  constructor() { }

  ngOnInit() {
  }

}
