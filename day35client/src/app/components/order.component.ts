import { Component, OnInit, Output } from '@angular/core';
import { Form, FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { Order } from '../models';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {


  @Output()
  onNewOrder = new Subject<Order>()

  lineItems!: FormArray
  form!: FormGroup

  // for parent component to use via @ViewChild
  get value(): Order {
    return this.form.value as Order
  }

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    // this.lineItems = this.fb.array([])
    // this.form = this.fb.group({
    //   name: this.fb.control<string>('', [Validators.required]),
    //   email: this.fb.control<string>('', [Validators.required, Validators.email]),
    //   lineItems: this.lineItems
    // })
    this.form = this.createOrderForm()
  }
  // '||' is the OR operator; 'Place Order' button is disabled if form is invalid or lineItems[] has nothing
  formInvalid(): boolean {
    return this.form.invalid || this.lineItems.controls.length <= 0
  }

  // adds to Array : createLineItem() Form Group
  addLineItem() {
    this.lineItems.push(this.createLineItem())
  }

  removeLineItem(idx: number) {
    this.lineItems.removeAt(idx)
  }

  private createOrderForm(): FormGroup {
    this.lineItems = this.fb.array([])
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required]),
      email: this.fb.control<string>('', [Validators.required, Validators.email]),
      lineItems: this.lineItems
    })
  }

  private createLineItem(): FormGroup {
    return this.fb.group({
      item: this.fb.control<string>('', [Validators.required]),
      quantity: this.fb.control<number>(1, [Validators.required, Validators.min(1)])
    })
  }


  placeOrder() {
    // sets Order object
    const order = this.form.value as Order
    // fires order to parent component
    this.onNewOrder.next(order)
    // this.form.reset()
  }

  clear() {
    // this brings back to form where 'Add Item' button has not been pressed
    this.form = this.createOrderForm()
    // this clears the fields, but still leaving the empty lineItem cells there
    // this.form.reset();
  }

}
