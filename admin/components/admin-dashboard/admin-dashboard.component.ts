import { Component } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { NzMessageRef, NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent {

  cars: any =[];

  constructor(private adminService: AdminService,
    private message : NzMessageService,
    private router :Router

  ){}

  ngOnInit(){
    this.getAllCars();
  }

  getAllCars(){
    this.adminService.getAllCars().subscribe((res)=> {
      console.log(res);
      res.forEach((element : any) => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImage;
        this.cars.push(element);
      })
    })
  }

  deleteCar(id: number){
    console.log(id);
    this.adminService.deleteCar(id).subscribe((res)=>{
      this.getAllCars();
      this.message.success("Car deleted successfully", { nzDuration : 5000 });
      this.router.navigateByUrl("/admin/dashboard");

    })

  }
}
