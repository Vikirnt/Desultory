from django.db import models
from apps.dbapp.models import User

class PayPalAccount (models.Model):
    parent_user = models.ForeignKey (User, on_delete=models.CASCADE)
    payerID = models.CharField (max_length=32)

class Payments (models.Model):
    payer = models.ForeignKey (PayPalAccount, on_delete=models.CASCADE)
    paymentID = models.CharField (max_length=32)
