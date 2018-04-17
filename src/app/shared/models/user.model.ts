export class User {
  constructor(public email: string,
              public firstName: string,
              public lastName: string,
              public login: string,
              public password: string,
              public phone: string,
              public role?: string,
              public id?: number) {}
}
