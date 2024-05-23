import { transition } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { AdminService } from '../../services/admin.service';
import { NzAlign } from 'ng-zorro-antd/grid';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';

@Component({
  selector: 'app-post-car',
  templateUrl: './post-car.component.html',
  styleUrls: ['./post-car.component.scss']
})
export class PostCarComponent implements OnInit{

  postCarForm! : FormGroup;
  isSpinning: boolean = false;
  selectedFile: File | null =null;
  imagePreview:string | ArrayBuffer | null =null;
  listOfOption: Array<{ label: string; value: string }> =[];
  listOfBrands = ["BMW", "AUDI", "FERRARI", "TESLA", "VOLVO", "TOYOTA", "HONDA", "FORD", "NISSAN", "HYUNDAI", "LEXUS", "KIA", "DACIA", "SKODA"]; 
  listOfType = ["Petrol", "Hybrid", "Diesel", "Electric", "CNG", "SUV"];
  listofColor = ["Red", "White", "Blue", "Black", "Orange", "Grey", "Silver"];
  listOfTransmission = ["Manual", "Automatic"];

  constructor(private fb: FormBuilder,
    private adminService:AdminService,
    private message:NzMessageService,
    private router :Router
  ){}

  ngOnInit(){
    this.postCarForm = this.fb.group({
      name: [null, Validators.required],
      matricule: [null, Validators.required],
      Distance: [null, Validators.required],
      brand: [null, Validators.required],
      type: [null, Validators.required],
      color: [null, Validators.required],
      transmission: [null, Validators.required],
      price: [null, Validators.required],
      description: [null, Validators.required],
      year: [null, Validators.required]
    })

  }

  // postCar(){
  //   console.log(this.postCarForm.value);
  //   // if (this.postCarForm.invalid) {
  //   // console.error('Form is invalid.');
  //   // return;
    
  //   // }
  //   this.isSpinning =true ;
    
   
  //   // // if (this.selectedFile instanceof File) {
  //   // //   formData.append('img', this.selectedFile);
  //   // // } else {
  //   // //   formData.append('img', "null");
  //   // //   console.error('No file selected.');
      
  //   // // }
  //   if (this.selectedFile !== null) {
  //     const formData: FormData = new FormData();
  //     formData.append('img', this.selectedFile);
  //     formData.append('brand', this.postCarForm.get('brand')?.value);
  //     formData.append('name', this.postCarForm.get('name')?.value); 
  //     formData.append('type', this.postCarForm.get('type')?.value); 
  //     formData.append('color', this.postCarForm.get('color')?.value);
  //     formData.append('year', this.postCarForm.get('year')?.value);
  //     formData.append('transmission', this.postCarForm.get('transmission')?.value);
  //     formData.append('description', this.postCarForm.get('description')?.value); 
  //     formData.append('price', this.postCarForm.get('price')?.value);
  //     console.log(formData);
  //     this.adminService.postCar(formData).subscribe((res) => {
     
  //       this.isSpinning = false;
  //       this.message.success("Car posted successfully", { nzDuration: 5000 });
  //       this.router.navigateByUrl("/admin/dashboard");
  //       console.log(res);
  //     },error => {
  //       this.isSpinning = false; // Reset spinner in case of error
  //       this.message.error("Error while posting car", { nzDuration: 5000 });
  //       console.error("Error:", error); // Log the error
  //     });
  //   } else {
  //     // Handle the case when `this.selectedFile` is null
  //     console.error('No file selected.');
  //   }
    
    
  // }
  postCar() {
    if (this.postCarForm.invalid) {
        console.error('Form is invalid.');
        return;
    }
    this.isSpinning = true;

    const formData: FormData = new FormData();
    if (this.selectedFile) {
        formData.append('image', this.selectedFile);
    }
    formData.append('brand', this.postCarForm.get('brand')?.value);
    formData.append('name', this.postCarForm.get('name')?.value);
    formData.append('matricule', this.postCarForm.get('matricule')?.value);
    formData.append('Distance', this.postCarForm.get('Distance')?.value);
    formData.append('type', this.postCarForm.get('type')?.value);
    formData.append('color', this.postCarForm.get('color')?.value);
    formData.append('year', this.postCarForm.get('year')?.value);
    formData.append('transmission', this.postCarForm.get('transmission')?.value);
    formData.append('description', this.postCarForm.get('description')?.value);
    formData.append('price', this.postCarForm.get('price')?.value);

    this.adminService.postCar(formData).subscribe(
        (res) => {
            this.isSpinning = false;
            this.message.success("Car posted successfully", { nzDuration: 5000 });
            this.router.navigateByUrl("/admin/dashboard");
        },
        (error) => {
            this.isSpinning = false;
            this.message.error("Error while posting car", { nzDuration: 5000 });
            console.error("Error:", error);
        }
    );
}





  // onFileSelected(event: any){
  //   if (event.target.files && event.target.files.length > 0) {
  //     this.selectedFile = event.target.files[0];
  //     this.previewImage();
  //   }else{
  //     console.log("no file selected");
  //   }
    
  // }

  // previewImage(){
  //   if (this.selectedFile instanceof File) {
  //     const reader = new FileReader();
  //     reader.onload = () => {
  //       this.imagePreview = reader.result;
  //     };
  //     reader.readAsDataURL(this.selectedFile);
  //   }
  // }

  onFileSelected(event: any) {
    if (event.target.files && event.target.files.length > 0) {
        this.selectedFile = event.target.files[0];
        this.previewImage();
    } else {
        console.log("No file selected");
    }
 }

    previewImage() {
        if (this.selectedFile) {
            const reader = new FileReader();
            reader.onload = () => {
                this.imagePreview = reader.result;
            };
            reader.readAsDataURL(this.selectedFile);
        }
    }


}
