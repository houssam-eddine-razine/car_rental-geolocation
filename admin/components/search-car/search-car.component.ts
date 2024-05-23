import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AdminService } from '../../services/admin.service';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-search-car',
  templateUrl: './search-car.component.html',
  styleUrls: ['./search-car.component.scss']
})
export class SearchCarComponent {

  searchCarForm!: FormGroup;
  listOfOption: Array<{ label: string; value: string }> =[];
  listOfBrands = ["BMW", "AUDI", "FERRARI", "TESLA", "VOLVO", "TOYOTA", "HONDA", "FORD", "NISSAN", "HYUNDAI", "LEXUS", "KIA", "DACIA", "SKODA"];  
  listOfType = ["Petrol", "Hybrid", "Diesel", "Electric", "CNG", "SUV"];
  listOfColor = ["Red", "White", "Blue", "Black", "Orange", "Grey", "Silver"];
  listOfTransmission = ["Manual", "Automatic"];
  isSpinning =false
  cars: any =[];

  constructor(private fb: FormBuilder, private service: AdminService,private message : NzMessageService,
    private adminService: AdminService
  ) {
    this.searchCarForm = this.fb.group({
      brand: [null],
      type: [null],
      transmission: [null],
      color: [null],

    })
  }




  // searchCar() {
  //   this.isSpinning = true;
  //   this.cars = []; // Clear previous results
  //   console.log(this.searchCarForm.value);
  //   this.service.searchCar(this.searchCarForm.value).subscribe(
  //       res => {
  //           console.log(res);
  //           res.carDtoList.forEach((element: any) => {
  //               element.processedImg = 'data:image/jpeg;base64,' + element.returnedImage;
  //               this.cars.push(element);
  //           });
  //           this.isSpinning = false;
  //       },
  //       error => {
  //           this.isSpinning = false;
  //           console.error("Error:", error);
  //       }
  //   );
  // }

  searchCar() {
    this.isSpinning = true;
    this.cars = []; // Clear previous results
    console.log(this.searchCarForm.value);
    this.service.searchCar(this.searchCarForm.value).subscribe(
      res => {
        console.log(res);
        if (res.carDtoList.length === 0) {
          // Display message if no cars found
          // You can replace 'errorMessage' with an appropriate variable to display the message
          this.message.error("no cars found", { nzDuration : 5000 });
        } else {

          res.carDtoList.forEach((element: any) => {
            element.processedImg = 'data:image/jpeg;base64,' + element.returnedImage;
            this.cars.push(element);
          });
        }
        // Reset form fields
        this.searchCarForm.reset();
        this.isSpinning = false;
      },
      error => {
        this.isSpinning = false;
        console.error("Error:", error);
      }
    );
  }


  deleteCar(id: number){
    console.log(id);
    this.adminService.deleteCar(id).subscribe((res)=>{
      this.cars = []; 
      this.message.success("Car deleted successfully", { nzDuration : 5000 });

    })

  }


 

  // searchCar(){
  //   this.isSpinning = true;
  //   console.log(this.searchCarForm.value);
  //   this.service.searchCar(this.searchCarForm.value).subscribe((res) => {
  //     console.log(res);
  //     res.carDtoList.forEach((element : any) => {
  //       element.processedImg = 'data:image/jpeg;base64,' + element.returnedImage;
  //       this.cars.push(element);
  //     })
  //     this.isSpinning = false;

  //   })
  // }

}
