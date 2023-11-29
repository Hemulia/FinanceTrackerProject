import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BudgetComponent } from './components/budget/budget.component';
import { UserComponent } from './components/user/user.component';
import { SalaryComponent } from './components/salary/salary.component';
import { TransactionComponent } from './components/transaction/transaction.component';
import { CompanyComponent } from './components/company/company.component';
import { CategoryComponent } from './components/category/category.component';
import { LoginComponent } from './components/login/login.component';
import { SigninComponent } from './components/signin/signin.component';
import { HomeComponent } from './components/home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {FormsModule} from '@angular/forms';
import { CustomMaterialModule } from './core/material.module';


@NgModule({
  declarations: [
    AppComponent,
    BudgetComponent,
    UserComponent,
    SalaryComponent,
    TransactionComponent,
    CompanyComponent,
    CategoryComponent,
    LoginComponent,
    SigninComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    CustomMaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
