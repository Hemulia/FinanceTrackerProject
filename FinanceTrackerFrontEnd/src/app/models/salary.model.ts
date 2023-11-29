import { User } from "./user.model";
import { Company } from "./company.model";

export class Salary {
    id: number;
    salary: number;
    localDate: Date;
    company: Company;
    user: User;

    constructor(id:number, salary:number, localDate:Date, company:Company, user:User){
        this.id= id,
        this.salary= salary,
        this.localDate= localDate,
        this.company= company,
        this.user= user
    }
}