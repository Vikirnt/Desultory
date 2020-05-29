from django.db import models

class CustomUser (models.Model):

    username = models.CharField (max_length=128)
    password = models.CharField (max_length=128)
    email = models.CharField (max_length=128)
    first_name = models.CharField (max_length=128)
    last_name = models.CharField (max_length=128)
    uid = models.AutoField (primary_key=True)
    userhash = models.CharField (max_length=128)

    def __str__ (self):
        return f"username: {self.username};\npassword: {self.password};\nemail: {self.email};\nfirst_name: {self.first_name};\nlast_name: {self.last_name};\nuid: {self.uid};\nuserhash: {self.userhash}"

class Invoice (models.Model):

    invoiceid = models.CharField (max_length=128)

    def __str__ (self):
        return f"Invoice: {self.invoiceid}"
