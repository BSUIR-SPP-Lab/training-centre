export class Sertificate {
  constructor(public sertificateId: number,
              public studentId: number,
              public courseName: string,
              public start: Date,
              public end: Date,
              public coordinatorName: string) {
  }
}
