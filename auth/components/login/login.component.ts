import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import {NzMessageService} from "ng-zorro-antd/message"
import {Router} from "@angular/router"
import { StorageService } from '../../services/storage/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{

  isSpinning: boolean =false;
  loginForm!: FormGroup;

  constructor(private fb: FormBuilder,
    private authService: AuthService,
    private router:Router,
    private message:NzMessageService){}

  
  ngOnInit(){
    this.loginForm = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]]
    })
  }

  // login(){
  //   this.authService.login(this.loginForm.value).subscribe((res) => {
  //     console.log(res);
  //     if(res.userId != null){
  //       const user ={
  //         id: res.userId,
  //         role: res.userRole
  //       }
  //       StorageService.saveUser(user);
  //       StorageService.saveToken(res.jwt);
  //       if(StorageService.isAdminLoggedIn()){
  //         this.router.navigateByUrl("/admin/dashboard");
  //       }else if(StorageService.isCustomerLoggedIn()){
  //         this.router.navigateByUrl("/customer/dashboard")
  //       }else{
  //         this.message.error("Bad credentials", { nzDuration: 50000});
  //       }
        
  //     }
  //   })

  // }
  login() {
    this.authService.login(this.loginForm.value).subscribe(
      (res) => {
        console.log(res);
        if (res.userId != null) {
          const user = {
            id: res.userId,
            role: res.userRole
          };
          StorageService.saveUser(user);
          StorageService.saveToken(res.jwt);
          if (StorageService.isAdminLoggedIn()) {
            this.router.navigateByUrl("/admin/dashboard");
          } else if (StorageService.isCustomerLoggedIn()) {
            this.router.navigateByUrl("/customer/dashboard");
          } else {
            this.message.error("Bad credentials", { nzDuration: 5000 });
          }
        }
      },
      (error) => {
        // Handle error response
        if (error.status === 401) {
          this.message.error("Invalid username or password", { nzDuration: 5000 });
        } else if (error.status === 400) {
          this.message.error("Bad request. Please check your input.", { nzDuration: 5000 });
        } else {
          this.message.error("Invalid username or password", { nzDuration: 5000 });
          this.loginForm.reset();
        }
      }
    );
  }
}
