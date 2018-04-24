export class Task {
  constructor(public name: string,
              public body: string,
              public firstName: string,
              public lastName: string,
              public uploadTime: string,
              public taskId?: number) {
  }
}
