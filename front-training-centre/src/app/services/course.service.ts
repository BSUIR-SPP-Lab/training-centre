import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Data } from '@angular/router';

interface Course {
  courseId: number;
  courseInfoId: number;
  coordinatorId: number;
  end: Data;
  start: Data;
}



@Injectable()

export class CourseService {

  constructor (private http: Http) {}

  getCourses() {
    // console.log('get func');
    return this.http.get('http://localhost:8080/course/all')
      .map((response: Response ) =>  response.json())
      .catch((error: Response ) => {
        return Observable.throw('Problems when loading course ');
      });
  }

  addCourse(course: Course): Promise<any> {
    // console.log(task);
    return this.http.post('http://localhost:8080/course/add', course )
      .toPromise()
      .then((response: Response) => response);
  }

  getCourse(id: number) {
    return this.http.get(`http://localhost:8080/course/${id}`)
      .map((response: Response) =>  response.json())
      .catch((error: Response) => {
        return Observable.throw('Problems when loading course ');
      });
  }

  deleteCourse(id: number): Promise<any> {
    return this.http.delete(`http://localhost:8080/course/delete/${id}`)
      .toPromise()
      .then((response: Response) => response);
  }

  updateCourse (course: Course): Promise<any>  {
    return this.http.post(`http://localhost:8080/course/update/${course.courseId}`, course )
      .toPromise()
      .then((response: Response) => response);
  }

}
