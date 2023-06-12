import { Subjob } from "./subjob";

export class Job {

    id:Number;
    title:String;
    description:String;
    totalAmount:Number;
    date:Date;
    subjobs: Subjob[];

    constructor(){}

}
