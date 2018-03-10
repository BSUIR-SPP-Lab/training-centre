import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';

interface CourseTemplate {
  courseInfoId: number;
  description: string;
  name: string;
}

@Injectable()

export class CourseTemplateService {

  constructor (private http: Http) {}

  getCourseTemplates() {
    // console.log('get func');
    return this.http.get('http://localhost:8080/courseInfo/all')
      .map((response: Response ) =>  response.json())
      .catch((error: Response ) => {
        return Observable.throw('Problems when loading course templates');
      });
  }

  addCourseTemplate(course: CourseTemplate): Promise<any> {
    // console.log(task);
    return this.http.post('http://localhost:8080/courseInfo/add', course )
      .toPromise()
      .then((response: Response) => response);
  }

  getCourseTemplate(id: number) {
    return this.http.get(`http://localhost:8080/courseInfo/${id}`)
      .map((response: Response) =>  response.json())
      .catch((error: Response) => {
        return Observable.throw('Problems when loading course templates');
      });
  }

  deleteCourseTemplate(id: number): Promise<any> {
    return this.http.delete(`http://localhost:8080/courseInfo/delete/${id}`)
      .toPromise()
      .then((response: Response) => response);
  }

  updateCourseTemplate (course: CourseTemplate): Promise<any>  {
    return this.http.post(`http://localhost:8080/courseInfo/update/${course.courseInfoId}`, course )
      .toPromise()
      .then((response: Response) => response);
  }

}
