import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { OrderComponent } from './components/order.component';
import { Order } from './models';
import { OrderService } from './order.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit{
  title = 'day35client';

  @ViewChild(OrderComponent)
	orderComp!: OrderComponent

  constructor(private orderSvc: OrderService){}
  
  ngOnInit(): void {
	}

	ngAfterViewInit(): void {
    // gets nothing
    console.log(">>> child's form value", this.orderComp.value)
	}

   newOrder(order: Order){
    console.info(">>> new order: ", order)
    this.orderSvc.placeOrder(order)
    .then(result => {
      //shows pop-up of orderId from Spring-boot on localhost:4200, if successful
      alert(`Order placed, Order id is ${result.orderId}`)
      this.orderComp.clear()
    })
    .catch(error => {
      // converts error into JSON String
      alert(`ERROR! ${JSON.stringify(error)}`)
    })
   }
}
