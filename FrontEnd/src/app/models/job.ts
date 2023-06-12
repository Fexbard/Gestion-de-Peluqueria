import { Subjob } from "./subjob";

export class Job {

    idJob:Number;
    jobTitle:String;
    jobDescription:String;
    totalAmount:Number;
    date:Date;
    subJobs: Subjob[];

    constructor(){}

}
