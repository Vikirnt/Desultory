from django.contrib import admin
from .models import CustomUser, Invoice

admin.site.register (CustomUser)
admin.site.register (Invoice)
