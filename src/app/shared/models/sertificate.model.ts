export class Sertificate {
  constructor(public certificateId: number,
              public name: string,
              public start: Date,
              public end: Date,
              public lastName: string,
              public firstName: string) {
  }
}
