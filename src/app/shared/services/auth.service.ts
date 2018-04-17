import {User} from "../models/user.model";

export  class AuthService {

  constructor() {
    const user = new User('test@gmail.com', 'firstName', 'lastNme', 'login', 'password', 'phone', 'USER');
    window.localStorage.setItem('user', JSON.stringify(user));
  }

  //TODO def value = false
  private isAuthenticated = true;

  login() {
    this.isAuthenticated = true;
  }

  logout() {
    this.isAuthenticated = false;
    window.localStorage.clear();
  }

  isLoggedIn(): boolean {
    return this.isAuthenticated;
  }

  getRole(): string {
    let role = 'GUEST';
    if (this.isLoggedIn()) {
      const user = JSON.parse(window.localStorage.getItem('user'));
      role = user.role;
    }

    return role;
  }
}