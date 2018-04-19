import {Pipe, PipeTransform} from '@angular/core';
import * as moment from 'moment';

@Pipe({
  name: 'tcMoment'
})

export  class MomentPipe implements  PipeTransform {

  // transform(value: string, formatFrom: string, formatTo: string = 'DD.MM.YYYY'): string {
  //   return moment(value, formatFrom).format(formatTo);
  // }
  transform(value: Date, formatTo: string = 'DD.MM.YYYY'): string {
    return moment(value).format(formatTo);
  }
}
