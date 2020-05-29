from django.shortcuts import render
from django.http import JsonResponse, HttpResponse
from django.views import View

import paypalrestsdk

def demo (request):
    return render (request, 'demopay.html', status=200)

def pay(request):
    conf = {
        'intent': 'sale',

        # Set payment method
        'payer': {
            'payment_method': 'paypal'
        },

        # Set redirect urls
        'redirect_urls': {
            'return_url': 'http://127.0.0.1:8000/pay/general',
            'cancel_url': 'http://127.0.0.1:8000/pay/cancel'
        },

        # Set transaction object
        'transactions': [{
            'amount': {
                'total': '10.00',
                'currency': 'USD'
            },
            'description': 'payment description'
        }]
    }

    payment = paypalrestsdk.Payment (conf)

    if payment.create ():
        return JsonResponse ({
            'links': payment.links,
        }, status=200)
    else:
        return HttpResponse (payment.error, status=500)

def confirm (request):
    payer = request.GET ['PayerID']
    paymentid = request.GET ['paymentId']

    return HttpResponse (f"{payer} : {paymentid}", status=200)
