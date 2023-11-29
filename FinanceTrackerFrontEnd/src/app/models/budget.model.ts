import { User } from "./user.model";

export class Budget {
    id: number;
    name: string;
    amount: number;
    user: User;

    constructor(id:number, name:string, amount:number, user:User){
        this.id= id,
        this.name= name,
        this.amount= amount,
        this.user= user
    }
}