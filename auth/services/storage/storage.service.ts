import { Injectable } from '@angular/core';

const TOKEN = "token";
const USER = "user";

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  

  constructor() { }

  static saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);
  }

  static saveUser(user: any): void {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }
 
  static getUserId(): string {
    const user = this.getUser();
    if (user == null){
      return '';
    }
    return user.id;
  }
  
  static getToken(){
    return window.localStorage.getItem(TOKEN);
  }  

  static getUser(){
    const userJson = localStorage.getItem(USER);
    return userJson ? JSON.parse(userJson) : null;
    //return JSON.parse(localStorage.getItem(USER));
  }

  static getUserRole():string{
    const user = this.getUser();
    if(user == null) return "";
    return user.role;
  }

  static isAdminLoggedIn(){
    if(this.getToken() == null)
      return false;
    const role :string = this.getUserRole();
    return role == "ADMIN";
  }

  
  static isCustomerLoggedIn(){
    if(this.getToken() == null)
      return false;
    const role :string = this.getUserRole();
    return role == "CUSTOMER";
  }

  static logout(): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }


}
