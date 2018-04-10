export class Course {
  constructor(
              public name: string,
              public description: string,
              public coordinatorId: number,
              public courseId?: number) {}
}
