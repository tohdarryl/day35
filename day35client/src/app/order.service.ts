import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom } from "rxjs";
import { Order, PlaceOrderResponse } from "./models";

// declare const var here (Spring-boot Controller Path)
const URL = "http://localhost:8080/order"

@Injectable ()
export class OrderService{

    constructor(private http: HttpClient) {}

    placeOrder(order:Order): Promise<PlaceOrderResponse> {
        // firstValueForm returns a promise
        return firstValueFrom(
            // send order infomation to URL (Spring-boot)
            this.http.post<PlaceOrderResponse>(URL, order)
        )
    }

}