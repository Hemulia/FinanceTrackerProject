import { Category } from "./category.model";
import { User } from "./user.model";

export class Transaction {
    id: number;
    description: string;
    amount: number;
    date: Date;
    category: Category;
    user: User;

    constructor(id:number, description:string, amount:number,date:Date, category:Category, user:User){
        this.id= id,
        this.description= description,
        this.amount= amount;
        this.date= date,
        this.category=  category,
        this.user= user
    }
}