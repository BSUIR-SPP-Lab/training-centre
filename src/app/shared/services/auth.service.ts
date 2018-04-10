export  class AuthService {

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
    let role ='GUEST';
    if (this.isLoggedIn()) {
      const user = JSON.parse(window.localStorage.getItem('user'));
      role = user.role;
    }

    return role;
  }
}
