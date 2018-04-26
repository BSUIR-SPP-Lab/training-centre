export class Solution {
  constructor(public filePath: string,
              public notes: string,
              public taskId: number,
              public userId: number,
              public teacherNotes: string,
              public uploadTime?: string,
              public mark?: number) {
  }
}
