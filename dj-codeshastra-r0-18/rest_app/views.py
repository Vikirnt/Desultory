from django.shortcuts import render
from django.http import HttpResponse, JsonResponse, FileResponse
from .models import CustomUser, Invoice
import hashlib, os, json
import qrcode
from random import randint

def signup (request):
    if request.method == 'GET':
        username = request.GET     ['username']
        password = request.GET     ['password']
        first_name = request.GET   ['first_name']
        last_name = request.GET    ['last_name']
        email = request.GET        ['email']

        user = CustomUser (username=username.lower (),
                           email=email.lower (),
                           password=password,
                           first_name=first_name,
                           last_name=last_name)

        hasher = hashlib.sha512 ()
        salt = "xxxsaltyxxx"
        string = f"{username}|{password}|{first_name}|{last_name}|{email}|{user.uid}"
        string += str (user.uid)
        hasher.update (salt.encode ('utf-8'))
        hasher.update (string.encode ('utf-8'))
        user.userhash = hasher.hexdigest ()

        if len (CustomUser.objects.all ().filter (email=email)) == 0:
            user.save ()
            return HttpResponse (user.uid)
        else:
            return HttpResponse ("Duplicate")

    return HttpResponse ("False")

def login (request):
    if request.method == 'GET':
        username = (request.GET ['username']).lower ()
        password = request.GET ['password']

        for obj in CustomUser.objects.all ():
            if obj.username.lower () == username and obj.password == password:
                dick = {
                "username": obj.username,
                "password": obj.password,
                "email": obj.email,
                "first_name": obj.first_name,
                "last_name": obj.last_name,
                "uid": obj.uid,
                "userhash": obj.userhash
                }
                return JsonResponse (obj.uid)

        return HttpResponse ("False")

def locations (request):
    if request.method == 'GET':
        with open ("/Users/vikirnt/Programming/Projects/CodeshastraRound0/tollmainframe/rest_app/locs.json", 'r') as outfile:
            x = json.load (outfile)
            return JsonResponse (x)
    return HttpResponse ("False")

def qrgen (request):
    qr = qrcode.make ("Vikrant")
    qr.save (f'/Users/vikirnt/Programming/Projects/CodeshastraRound0/tollmainframe/rest_app/QR/qr_{request}.png')
    with open (f'/Users/vikirnt/Programming/Projects/CodeshastraRound0/tollmainframe/rest_app/QR/qr_{request}.png', 'rb') as f:
        return HttpResponse (f.read (), content_type="images/png")

def userdetails (request):
    uid = int (request.GET ['uid'])
    for obj in CustomUser.objects.all ():
        if obj.uid == uid:
            dick = {
            "username": obj.username,
            "password": obj.password,
            "email": obj.email,
            "first_name": obj.first_name,
            "last_name": obj.last_name,
            "uid": obj.uid,
            "userhash": obj.userhash
            }
            return JsonResponse (dick)

def payment (request):
    if request.method == 'GET':

        hasher = hashlib.sha512 ()
        hasher.update (request.GET ['uid'].encode ('utf-8'))
        hasher.update ("thisisinvoice".encode ('utf-8'))
        invoiceid = hasher.hexdigest ()

        x = 0
        for c in invoiceid:
            y = ord (c) - ord ('a')
            if y < 0:
                x -= y
            else:
                x += y
        x *= randint (1, 93)

        i = Invoice (invoiceid=x)
        i.save ()

        return HttpResponse (x)
    else:
        return HttpResponse ("False")


def pay2 (request):
    if request.method == 'GET':

        hasher = hashlib.sha512 ()
        hasher.update (request.GET ['uid'].encode ('utf-8'))
        hasher.update ("thisisinvoice".encode ('utf-8'))
        invoiceid = hasher.hexdigest ()

        x = 0
        for c in invoiceid:
            y = ord (c) - ord ('a')
            if y < 0:
                x -= y
            else:
                x += y
        x *= randint (1, 93

        )

        i = Invoice (invoiceid=x)
        i.save ()

        return qrgen (x)
    else:
        return HttpResponse ("False")


def auth (request):
    invoiceid = request.GET ['uid']
    for obj in Invoice.objects.all ():
        if obj.invoiceid == invoiceid:
            obj.delete ()
            return HttpResponse ("True")
    return HttpResponse ("False")
