export class TaskWithoutInfo {
  constructor(
              public groupId: number,
              public taskInfoId: number,
              public teacherId: number,
              public uploadTime: string,
              public taskId?: number) {
  }
}
