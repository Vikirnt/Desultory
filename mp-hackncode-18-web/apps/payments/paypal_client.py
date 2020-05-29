import paypalrestsdk
import os

# NOTE: PayPal payments.
# https://developer.paypal.com/docs/api/overview/

PAYPAL_CONFIG = {
    'mode': os.environ['PAYPAL_MODE'],
    'client_id': os.environ['PAYPAL_ID'],
    'client_secret': os.environ['PAYPAL_SECRET']
}

paypalrestsdk.configure(PAYPAL_CONFIG)


def create_payment():

    {
        "intent": "sale",
        "payer": {
            "payment_method": "paypal"},
        "redirect_urls": {
            "return_url": "http://127.0.0.1:8000/pay/general",
            "cancel_url": "http://127.0.0.1:8000/pay/demo"},
        "transactions": [ {
            "item_list": {
                "items": [{
                    "name": "item",
                    "sku": "item",
                    "price": "5.00",
                    "currency": "USD",
                    "quantity": 1}]},
            "amount": {
                "total": "5.00",
                "currency": "USD"},
            "description": "This is the payment transaction description."
        } ]
    }
