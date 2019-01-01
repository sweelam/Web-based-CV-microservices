import {ErrorObject} from './error-object';

export interface ErrorHandler {
    error : ErrorObject;
    ok : boolean,
    status : number,
    statusText : string,
    url : string
}
