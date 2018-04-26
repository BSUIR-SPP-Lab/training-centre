export class CourseWithoutInfo {
  constructor(
    public coordinatorId: number,
    public courseInfoId: number,
    public end: string,
    public start: string,
    public courseId?: number) {}
}

