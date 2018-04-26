import {User} from "../models/user.model";

export  class AuthService {

  constructor() {
    // user = new User('test@gmail.com', 'firstName', 'lastNme', 'login', 'password', 'phone', 'ADMIN', 41);
    // window.localStorage.setItem('user', JSON.stringify(user));
  }

  //TODO def value = false
  private isAuthenticated = false;

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

  getId(): number {
    let id = 0;
    if (this.isLoggedIn()) {
      const user = JSON.parse(window.localStorage.getItem('user'));
      id = user.id;
    }

    return id;
  }
}
